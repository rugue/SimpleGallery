package com.example.simplegallery.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.simplegallery.R
import com.example.simplegallery.network.GalleryProperty

class DetailViewModel(galleryProperty: GalleryProperty,
                      app: Application
) : AndroidViewModel(app) {
    private val _selectedProperty = MutableLiveData<GalleryProperty>()
    val selectedProperty: LiveData<GalleryProperty>
        get() = _selectedProperty

    init {
        _selectedProperty.value = galleryProperty
    }

    val displayDestination = Transformations.map(selectedProperty) {
        app.applicationContext.getString(
            R.string.destination,
            it.destination
        )
    }

    val displayName = Transformations.map(selectedProperty) {
        app.applicationContext.getString(
            R.string.name,
            it.name
        )
    }
}