package com.example.aprocessor

import androidx.paging.PagedList
import com.example.annotations.simpledatasourcegenerator.GenDataSource
import com.example.annotations.simpledatasourcegenerator.KeyItem
import com.example.annotations.simpledatasourcegenerator.PageConfig
import com.example.annotations.simpledatasourcegenerator.WithDataSource
import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.*
import java.io.File
import java.lang.reflect.Type
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind
import javax.lang.model.element.TypeElement


@AutoService(Processor::class)
class DataSourceAnnotationProcessor: AbstractProcessor() {
    companion object {
        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
        const val PATH = "com.fovlax.datasourcelibrary."
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {

        return mutableSetOf(
            WithDataSource::class.java.name,
            GenDataSource::class.java.name,
            PageConfig::class.java.name,
            KeyItem::class.java.name
        )
    }

    override fun getSupportedSourceVersion(): SourceVersion = SourceVersion.RELEASE_8

    override fun process(p0: MutableSet<out TypeElement>?, p1: RoundEnvironment?): Boolean {

        p1?.getElementsAnnotatedWith(WithDataSource::class.java)
            ?.forEach {
                if (it.kind != ElementKind.CLASS) {

                    return false
                }
                processAnnotation(it)
            }
        return true
    }




    private fun processAnnotation(element: Element) {
        val className = element.simpleName.toString()
        val pack = processingEnv.elementUtils.getPackageOf(element).toString()
        val fileName = "${className}_Impl"
        val fileBuilder = FileSpec.builder(pack, fileName)
        val classBuilder = TypeSpec.classBuilder(fileName)


        val ctor = FunSpec.constructorBuilder()
        ctor.addParameter("repository", element.asType().asTypeName())
        classBuilder.primaryConstructor(ctor.build())
        classBuilder.addProperty(PropertySpec.builder("repository",element.asType().asTypeName())
                .initializer( "repository" )
                .build())


        for (enclosed in element.enclosedElements) {
            if (enclosed.kind == ElementKind.METHOD) {

                val genDataSource: GenDataSource? = try{
                    enclosed.getAnnotation(GenDataSource::class.java)
                }catch(ex: Exception){
                    null
                }

                val pageConfig: PageConfig? = try {
                    enclosed.getAnnotation(PageConfig::class.java)
                }catch(ex: Exception){
                    null
                }
                var pConfig: String? = null
                if (pageConfig!=null){
                    pConfig = "androidx.paging.PagedList.Config.Builder()\n"+
                        ".setEnablePlaceholders(${pageConfig!!.enablePlaceholders})\n"+
                        ".setInitialLoadSizeHint(${pageConfig!!.initialLoadSizeHint})\n"+
                        ".setPageSize(${pageConfig!!.pageSize})\n"+
                       " .setMaxSize(${pageConfig!!.maxSize})\n"+
                        ".setPrefetchDistance(${pageConfig!!.prefetchDistance})\n"+
                       " .build()"
                }

                if (genDataSource!=null){

                    val typeStr = enclosed.asType().toString()
                    val type = typeStr.substringAfter("<").substringBefore(">")

                    if (genDataSource.type == GenDataSource.Type.Positional) {

                        classBuilder.addFunction(
                            FunSpec.builder("${enclosed.simpleName}LivePagedList")
                                .addParameter("initialKey", Int::class)
                                .addCode("return ${PATH}SimpleDataSourceGenerator().getLiveDataMapped<Int,${type}>(")
                                .addCode(" com.fovlax.datasourcelibrary.datasource.Functions(")
                                .addCode("loadDataPos = { offset, count ->")
                                .addCode("repository.getWorks(offset,count) }),")
                                .addCode("${pConfig},initialKey)")
                                .build()
                        )
                    }

                }



            }
        }
        val file = fileBuilder.addType(classBuilder.build()).build()
        val kaptKotlinGeneratedDir = processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME]
        file.writeTo(File(kaptKotlinGeneratedDir, "${className}.kt"))
    }
}