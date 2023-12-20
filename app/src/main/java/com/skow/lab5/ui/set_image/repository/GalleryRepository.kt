package com.skow.lab5.ui.set_image.repository

import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.Log

class GalleryRepository(private val context: Context) {
    lateinit var uri: Uri

    fun getSharedList(): MutableList<GalleryDataItem> {
            uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            val imageList = mutableListOf<GalleryDataItem>()
            val cursor = context.contentResolver.query(uri, null, null, null, null)

            cursor?.use {
                val idColumn = cursor.getColumnIndex(MediaStore.Images.Media._ID)
                val nameColumn = cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME)
                while (it.moveToNext()) {
                    val thisId = cursor.getLong(idColumn)
                    val thisName = cursor.getString(nameColumn)
                    val thisContentUri = ContentUris.withAppendedId(uri, thisId)
                    val thisUriPath = thisContentUri.toString()
                    imageList.add(GalleryDataItem(
                        thisName, thisUriPath, "No path yet",
                        thisContentUri
                    ))
                }
            }
        Log.i("dataXDDDD", imageList.toString())
        return imageList
    }

    companion object {
        private var INSTANCE: GalleryRepository? = null
        var sharedStoreList: MutableList<GalleryDataItem>? = null
        var appStoreList: MutableList<GalleryDataItem>? = null

        fun getinstance(context: Context): GalleryRepository {
            if (INSTANCE == null) {
                INSTANCE = GalleryRepository(context)
                sharedStoreList = mutableListOf()
                appStoreList = mutableListOf()
            }
            return INSTANCE as GalleryRepository
        }
    }
}