package com.example.simplegallery.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.simplegallery.network.GalleryProperty

class DetailViewModelFactory(
    private val galleryProperty: GalleryProperty,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(galleryProperty, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}