package com.skow.lab5.ui.set_image

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.skow.lab5.ui.set_image.repository.GalleryDataItem
import com.skow.lab5.ui.set_image.repository.GalleryRepository

class GalleryViewModel(context: Context) : ViewModel() {
    private var galleryRepository: GalleryRepository = GalleryRepository.getinstance(context)
    val repo = MutableLiveData<List<GalleryDataItem>>()

    fun getGalleryData(context: Context): MutableList<GalleryDataItem> {
        return galleryRepository.getSharedList()
    }

    fun updateImages(context: Context){
        repo.value = getGalleryData(context)
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application = checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
                return GalleryViewModel(application.applicationContext) as T
            }
        }
    }
}