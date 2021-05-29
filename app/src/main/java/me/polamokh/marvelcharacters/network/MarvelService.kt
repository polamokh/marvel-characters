package me.polamokh.marvelcharacters.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import me.polamokh.marvelcharacters.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.math.BigInteger
import java.security.MessageDigest

interface MarvelService {

    @GET("v1/public/characters")
    suspend fun getCharacters(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int = 0,
        @Query("nameStartsWith") nameStartsWith: String? = null
    ): ResponseDTO

    companion object {
        private const val BASE_URL = "https://gateway.marvel.com/"

        private const val TIMESTAMP_QUERY_KEY = "ts"
        private const val API_KEY_QUERY_KEY = "apikey"
        private const val HASH_QUERY_KEY = "hash"

        private const val MARVEL_API_KEY = BuildConfig.MARVEL_API_KEY
        private const val MARVEL_PRIVATE_API_KEY = BuildConfig.MARVEL_PRIVATE_API_KEY

        fun create(): MarvelService {
            val moshi = Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()

            val okHttpClient = createOkHttpClient()

            return Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .baseUrl(BASE_URL)
                .build()
                .create(MarvelService::class.java)
        }

        private fun createOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val currentTime = System.currentTimeMillis()

                    val request = chain.request().newBuilder()
                    val originalUrl = chain.request().url()
                    val newUrl = originalUrl.newBuilder()
                        .addQueryParameter(TIMESTAMP_QUERY_KEY, currentTime.toString())
                        .addQueryParameter(API_KEY_QUERY_KEY, MARVEL_API_KEY)
                        .addQueryParameter(
                            HASH_QUERY_KEY,
                            createHash("$currentTime$MARVEL_PRIVATE_API_KEY$MARVEL_API_KEY")
                        )
                        .build()

                    request.url(newUrl)
                    return@addInterceptor chain.proceed(request.build())
                }
                .build()
        }

        private fun createHash(input: String): String {
            val md5 = MessageDigest.getInstance("MD5")
            return BigInteger(1, md5.digest(input.toByteArray()))
                .toString(16).padStart(32, '0')
        }
    }
}