package com.example.aprocessor

import androidx.annotation.NonNull
import com.example.annotations.simpledatasourcegenerator.GenDataSource
import com.example.annotations.simpledatasourcegenerator.KeyItem
import com.example.annotations.simpledatasourcegenerator.PageConfig
import com.example.annotations.simpledatasourcegenerator.WithDataSource
import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.*
import org.jetbrains.annotations.Nullable
import java.io.File
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.ExecutableElement
import javax.lang.model.element.ElementKind
import javax.lang.model.element.TypeElement
import javax.lang.model.type.TypeKind
import javax.tools.Diagnostic
import kotlin.reflect.KClass


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
        classBuilder.addProperty(
            PropertySpec.builder("repository", element.asType().asTypeName())
                .initializer("repository")
                .build()
        )

        val afterFunMapType = HashMap<String, String>()
        val beforeFunMapType = HashMap<String, String>()

        val afterFunMapName = HashMap<String, String>()
        val beforeFunMapName = HashMap<String, String>()

        val afterFunMapConfig = HashMap<String, String?>()
        val beforeFunMapConfig = HashMap<String, String?>()

        val keyFunMap = HashMap<String, KClass<*>>()
        val keyFunNullable = HashMap<String, Boolean>()
        val keyFunMapName = HashMap<String, String>()

        for (enclosed in element.enclosedElements) {
            if (enclosed.kind == ElementKind.METHOD) {

                val genDataSource: GenDataSource? = try{
                    enclosed.getAnnotation(GenDataSource::class.java)
                }catch (ex: Exception){
                    null
                }

                val pageConfig: PageConfig? = try {
                    enclosed.getAnnotation(PageConfig::class.java)
                }catch (ex: Exception){
                    null
                }

                val keyItem: KeyItem? = try {
                    enclosed.getAnnotation(KeyItem::class.java)
                }catch (ex: Exception){
                    null
                }

                if (keyItem!=null){
                    keyFunMap[keyItem.name] = getClass(enclosed as ExecutableElement)
                    keyFunNullable[keyItem.name] = enclosed.getAnnotation(Nullable::class.java) != null
                    keyFunMapName[keyItem.name] = enclosed.simpleName.toString()
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
                    println(enclosed.asType())
                    val type = typeStr.substringAfter("<").substringBefore(">")
                    val name = genDataSource.sourceName

                    when (genDataSource.type) {
                        GenDataSource.Type.Positional -> {
                            classBuilder.addFunction(
                                FunSpec.builder("${name}LivePagedList")
                                    .addParameter("initialKey", Int::class)
                                    .addCode("return ${PATH}SimpleDataSourceGenerator().getLiveDataMapped<Int,${type}>(")
                                    .addCode(" com.fovlax.datasourcelibrary.datasource.Functions(")
                                    .addCode("loadDataPos = { offset, count ->")
                                    .addCode("repository.${enclosed.simpleName}(offset,count) }),")
                                    .addCode("${pConfig},initialKey)")
                                    .build()
                            )
                        }
                        GenDataSource.Type.ItemKeyedAfter -> {
                            afterFunMapType[genDataSource.sourceName] = type
                            afterFunMapName[genDataSource.sourceName] =
                                enclosed.simpleName.toString()
                            afterFunMapConfig[genDataSource.sourceName] = pConfig
                        }
                        GenDataSource.Type.ItemKeyedBefore -> {
                            beforeFunMapType[genDataSource.sourceName] = type
                            beforeFunMapName[genDataSource.sourceName] =
                                enclosed.simpleName.toString()
                            beforeFunMapConfig[genDataSource.sourceName] = pConfig
                        }
                    }

                }



            }
        }

        for (after in afterFunMapType){
            if (!keyFunMap.contains(after.key)){
                processingEnv.messager.printMessage(
                    Diagnostic.Kind.ERROR,
                    "Key function for $after ItemKeyedDataSource not found"
                )
            }

            val nullable = if (keyFunNullable[after.key]!!)  "?" else ""

            val beginFunc = "return ${PATH}SimpleDataSourceGenerator().getLiveDataMapped<${keyFunMap[after.key]?.simpleName}$nullable,${after.value}>("
            val loadAfter = "loadDataItemAfter = { key, count -> repository.${afterFunMapName[after.key]}(key,count) }"
            val getKeyFun = "getKeyFunction = { item -> repository.${keyFunMapName[after.key]}(item) }"
            var loadBefore: String? = "loadDataItemBefore = null"

            if (beforeFunMapType.contains(after.key)) {
                loadBefore = "loadDataItemBefore = { key, count -> repository.${beforeFunMapName[after.key]}(key,count) }"
            }


            val nullSafety = (keyFunNullable[after.key]!!)
            val typeName =  (keyFunMap[after.key] as KClass<*>).asTypeName().copy(nullable = nullSafety)



            classBuilder.addFunction(
                FunSpec.builder("${after.key}LivePagedList")
                    .addParameter(  ParameterSpec.builder("initialKey",typeName )
                        .build())
                    .addCode(beginFunc)
                    .addCode(" com.fovlax.datasourcelibrary.datasource.Functions(")
                    .addCode("${loadAfter},")
                    .addCode("${loadBefore},")
                    .addCode(getKeyFun)
                    .addCode("),")
                    .addCode("${afterFunMapConfig[after.key]},initialKey)")
                    .build()
            )

        }

        for (before in beforeFunMapType){

            if (afterFunMapType.contains(before.key)){
                continue
            }
            if (!keyFunMap.contains(before.key)){
                processingEnv.messager.printMessage(
                    Diagnostic.Kind.ERROR,
                    "Key function for $before ItemKeyedDataSource not found"
                )
            }

            val nullable = if (keyFunNullable[before.key]!!)  "?" else ""

            val beginFunc = "return ${PATH}SimpleDataSourceGenerator().getLiveDataMapped<${keyFunMap[before.key]?.simpleName}$nullable,${before.value}>("
            val loadAfter = "loadDataItemAfter = null"
            val getKeyFun = "getKeyFunction = { item -> repository.${keyFunMapName[before.key]}(item) }"
            var loadBefore = "loadDataItemBefore = { key, count -> repository.${beforeFunMapName[before.key]}(key,count) }"

            val nullSafety = (keyFunNullable[before.key]!!)
            val typeName =  (keyFunMap[before.key] as KClass<*>).asTypeName().copy(nullable = nullSafety)

            classBuilder.addFunction(
                FunSpec.builder("${before.key}LivePagedList")
                    .addParameter(  ParameterSpec.builder("initialKey",typeName )
                        .build())
                    .addCode(beginFunc)
                    .addCode(" com.fovlax.datasourcelibrary.datasource.Functions(")
                    .addCode("${loadAfter},")
                    .addCode("${loadBefore},")
                    .addCode(getKeyFun)
                    .addCode("),")
                    .addCode("${afterFunMapConfig[before.key]},initialKey)")
                    .build()
            )

        }



        val file = fileBuilder.addType(classBuilder.build()).build()
        val kaptKotlinGeneratedDir = processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME]
        file.writeTo(File(kaptKotlinGeneratedDir, "${className}.kt"))
    }


    private fun getClass(it: ExecutableElement): KClass<*> {
        val type = it.returnType
        return when (type.kind) {
            TypeKind.DECLARED -> Class.forName(type.toString()).kotlin
            TypeKind.BOOLEAN -> Boolean::class
            TypeKind.BYTE -> Byte::class
            TypeKind.SHORT -> Short::class
            TypeKind.INT -> Int::class
            TypeKind.LONG -> Long::class
            TypeKind.CHAR -> Char::class
            TypeKind.FLOAT -> Float::class
            TypeKind.DOUBLE -> Double::class
            else -> throw Exception("Unknown type: $type, kind: ${type.kind}")
        }
    }

}