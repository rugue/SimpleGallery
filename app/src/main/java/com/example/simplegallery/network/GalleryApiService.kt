package com.example.simplegallery.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface GalleryApiService {
    @GET("Oclemy/SampleJSON/338d9585/spacecrafts.json")
    suspend fun getProperties():
            List<GalleryProperty>
}

object GalleryApi {
    val retrofitService : GalleryApiService by lazy {
        retrofit.create(GalleryApiService::class.java) }
}