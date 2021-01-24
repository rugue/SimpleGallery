package com.example.simplegallery.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class GalleryProperty(
    val id: String,
    val name: String,
    val propellant: String,
    val destination: String,
    @Json(name = "imageurl") val imgSrcUrl: String,
    val technologyexists: String
): Parcelable
