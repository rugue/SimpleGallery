package com.example.simplegallery.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplegallery.network.GalleryApi
import com.example.simplegallery.network.GalleryProperty
import kotlinx.coroutines.launch

enum class GalleryApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel : ViewModel() {

    //MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<GalleryApiStatus>()
    val status: LiveData<GalleryApiStatus>
        get() = _status

    //MutableLiveData for properties
    private val _properties = MutableLiveData<List<GalleryProperty>>()
    val properties: LiveData<List<GalleryProperty>>
        get() = _properties

    // LiveData to handle navigation to the selected property
    private val _navigateToSelectedProperty = MutableLiveData<GalleryProperty>()
    val navigateToSelectedProperty: LiveData<GalleryProperty>
        get() = _navigateToSelectedProperty

    /**
     * Call getGalleryProperties() on init so we can display status immediately.
     */
    init {
        getGalleryProperties()
    }

    /**
     * Gets filtered Gallery property information from the API Retrofit service and
     * updates the [DataProperty] [List] and [GalleryApiStatus] [LiveData].
     */
    private fun getGalleryProperties() {
        viewModelScope.launch {
            _status.value = GalleryApiStatus.LOADING
            try {
                _properties.value = GalleryApi.retrofitService.getProperties()
                _status.value = GalleryApiStatus.DONE
            } catch (e: Exception) {
                _status.value = GalleryApiStatus.ERROR
                _properties.value = ArrayList()
            }
        }
    }

    fun displayPropertyDetails(galleryProperty: GalleryProperty) {
        _navigateToSelectedProperty.value = galleryProperty
    }
    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }
}