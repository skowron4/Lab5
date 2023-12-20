package com.skow.lab5.ui.set_image.repository

import android.net.Uri

class GalleryDataItem {
    var name : String = "Empty name"
    var uripath : String = " Empty uri"
    var path : String = " Empty path"
    var curi : Uri? = null

    constructor(name:String, uripath:String, path: String, curi: Uri) : this() {
        this.name = name
        this.uripath = uripath
        this.path = path
        this.curi = curi
    }

    constructor()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GalleryDataItem

        if (name != other.name) return false
        if (uripath != other.uripath) return false
        if (path != other.path) return false
        if (curi != other.curi) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + uripath.hashCode()
        result = 31 * result + path.hashCode()
        result = 31 * result + (curi?.hashCode() ?: 0)
        return result
    }
}