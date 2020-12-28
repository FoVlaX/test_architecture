package com.example.pagerlistapp

import android.os.Build
import com.example.pagerlistapp.amodels.Data
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.core.Observable
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface ImageApiService {

    companion object {

        fun create(): ImageApiService {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor { chain ->
                    val request: Request =
                        chain.request()
                            .newBuilder()
                            .addHeader(BuildConfig.BASE_KEY, BuildConfig.KEY_VALUE)
                            .addHeader(BuildConfig.BASE_HOST,BuildConfig.HOST_VALUE).build()
                    chain.proceed(request)
                }
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_IMAGE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ImageApiService::class.java)
        }
    }

    @GET("api/Search/ImageSearchAPI")
    fun getImagesForName(@Query("q") q: String,
                         @Query("pageNumber") pageNumber: Int,
                         @Query("pageSize") pageSize: Int,
                         @Query("autoCorrect") autoCorrect: Boolean = true) : Observable<Data>

}