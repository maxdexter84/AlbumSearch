package ru.maxdexter.albumsearch.data.remotedatasource

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.maxdexter.albumsearch.data.remotedatasource.MusicApi.KEY

const val BASE_URL = "https://spotify-scraper.p.rapidapi.com"
const val HOST = "spotify-scraper.p.rapidapi.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val requestInterceptor = Interceptor { chain ->
    val url = chain.request()
        .url
        .newBuilder()
        .build()
    val request = chain.request()
        .newBuilder()
        .url(url)
        .addHeader("X-RapidAPI-Key", KEY)
        .addHeader("X-RapidAPI-Host", HOST)
        .build()
    return@Interceptor chain.proceed(request)
}
private val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(requestInterceptor)
    .build()

private val retrofit = Retrofit.Builder()
    .client(okHttpClient)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

object MusicApi {
    val musicService: IMusicApi by lazy {
        retrofit.create(IMusicApi::class.java)
    }
    const val KEY = "0d64c0c81dmsh8c9e60a1a7daf8dp1eedd5jsn8d8e64a2577e"
}

