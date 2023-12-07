package com.skow.lab5.ui.items_list.placeholder

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

object Placeholder {
    val ITEMS: MutableList<PlaceholderItem> = ArrayList()
    private val indexs: MutableList<Int> = ArrayList()

    init {
        ITEMS.add(PlaceholderItem(1, "Kai", 9f, "M","Czerwony ninja"))
        ITEMS.add(PlaceholderItem(2, "Zane", 9f, "M","Bialy ninja"))
        ITEMS.add(PlaceholderItem(3, "Jay", 9f, "M","Niebieski ninja"))
        ITEMS.add(PlaceholderItem(4, "Cole", 9f, "M","Czarny ninja"))
        ITEMS.add(PlaceholderItem(5, "Sensei Wu", 12f, "M","Mistrz ninja"))
        ITEMS.add(PlaceholderItem(6, "Nya", 8f, "K", "Siostra Kai'a"))
        ITEMS.add(PlaceholderItem(7, "Lord Garmadon", 12f, "M","Brat Wu"))
        ITEMS.add(PlaceholderItem(8, "Skylar", 6f, "K", "W sumie nie wiem kto to"))
        ITEMS.add(PlaceholderItem(9, "Pixal", 5f, "K", "Tej tez nie znam"))
        ITEMS.add(PlaceholderItem(10, "Ksiezna Qi", 4f, "K", "O tej nigdy nie slyszalem"))
        ITEMS.add(PlaceholderItem(11, "Harumi", 4f, "K", "Kto to jest?"))
        ITEMS.add(PlaceholderItem(12, "Szef INVIL", 10f, "M","Szef wszystkich szefow"))
        ITEMS.add(PlaceholderItem(13, "Pierwszy mistrz spinjitzu", 15f, "M", "Nie wiem, moze tak moze nie"))
    }

    fun nextIndex(): Int {
        var minId = indexs.minOrNull()
        if (minId != null) indexs.remove(minId)
        else
            minId = ITEMS.size + 1
        return minId
    }

    fun removeItem(id: Int){
        indexs.add(id)

        for (item: PlaceholderItem in ITEMS){
            if (item.id == id) {
                ITEMS.remove(item)
                break
            }
        }
    }

    data class PlaceholderItem(var id: Int, var name: String, var power: Float, var gender: String, var desc: String) : Parcelable{
        override fun toString(): String {
            return name
        }

        constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString() ?: "",
            parcel.readFloat(),
            parcel.readString() ?: "",
            parcel.readString() ?: ""
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(id)
            parcel.writeString(name)
            parcel.writeFloat(power)
            parcel.writeString(gender)
            parcel.writeString(desc)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<PlaceholderItem> {
            override fun createFromParcel(parcel: Parcel): PlaceholderItem {
                return PlaceholderItem(parcel)
            }

            override fun newArray(size: Int): Array<PlaceholderItem?> {
                return arrayOfNulls(size)
            }
        }
    }
}