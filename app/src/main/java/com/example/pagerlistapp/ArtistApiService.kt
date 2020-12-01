package com.example.pagerlistapp


import com.company.artist.model.response.ExpositionsResponse
import com.example.pagerlistapp.models.responses.*
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ArtistApiService {

    companion object {

        fun create(): ArtistApiService {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build()

            val retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .client(client)
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(ArtistApiService::class.java)
        }
    }

    @GET("artists.info")
    fun getArtistInfo(@Query("artist_ids") artistId: Int = BuildConfig.ARTIST_ID,
                      @Query("extends") extends: String = "artists.hello,artists.about_app,artists.media_id,artists.count_works,artists.biography,artists.properties,artists.about_app,artists.contact")
            : Single<ArtistsResponce>

    @GET("expositions.search")
    fun getExpositions(@Query("date_start_to") startDate: Long,
                       @Query("date_end_from") endDate: Long,
                       @Query("uri_owner") uri_owner: String = "artist://${BuildConfig.ARTIST_ID}",
                       @Query("offset") offset: Int = 0,
                       @Query("count") count: Int = 20,
                       @Query("extends") extends: String = "expositions.media_ids")
    : Single<ExpositionsResponse>

    @GET("events.artist")
    fun getArtistNews(@Query("before_event_id") beforeEventId: Int = 0,
                      @Query("count") count: Int = 20,
                      @Query("artist_id") artistId: Int = BuildConfig.ARTIST_ID,
                      @Query("extends") extends: String = "events.attaches,works.description,works.properties,expositions.media_ids,set.info,artists.hello,works.media_id,works.counters,topics.first_media_id,topics.full,set.info,artists.hello,topics.uris")
    : Observable<EventsResponse>

    @GET("sets.artist_sets")
    fun getCollections(@Query("type") type: String = "artist",
                       @Query("order") order: String = "date",
                       @Query("direction") direction: String = "desc",
                       @Query("count") count: Int = 30,
                       @Query("offset") offset: Int = 0,
                       @Query("artist_id") artistId: Int = BuildConfig.ARTIST_ID,
                       @Query("extends") extends: String = "asets.cover_work_id")
            : Observable<CollectionResponce>


    @GET("works.search")
    fun getWorks(@Query("count") count: Int = 30,
                 @Query("offset") offset: Int = 0,
                 @Query("order") order: String = "",
                 @Query("artist_id") artistId: Int = BuildConfig.ARTIST_ID,
                 @Query("uris") uris: String = "",
                 @Query("min_x") min_x: String = "",
                 @Query("max_x") max_x: String = "",
                 @Query("extends") extends: String = "works.media_id,works.counters,works.properties,works.collection_id,works.infos,works.description,filters.uri,works.aset_ids,works.artist_ids,works.media_id")
            : Observable<WorksResponse>

    @GET("works.search")
    fun getFilters(@Query("with_filters") with_filters: String = "y",
                   @Query("filter_prefixes") filter_prefixes: String = "salest,genre,style,tech",
                   @Query("count_filters") count_filters: Int = 20,
                   @Query("count") count: Int = 0)
            : Observable<FiltersResponse>

    @GET("static.info")
    fun getInfoForUsers(@Query("section") section: String)
            : Observable<InfoResponse>
}