package com.skow.lab5.ui.items_list.placeholder

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class ItemEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "power")
    var power: Float,
    @ColumnInfo(name = "gender")
    var gender: String,
    @ColumnInfo(name = "desc")
    var desc: String
) : Parcelable{
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

    companion object CREATOR : Parcelable.Creator<ItemEntity> {
        override fun createFromParcel(parcel: Parcel): ItemEntity{
            return ItemEntity(parcel)
        }

        override fun newArray(size: Int): Array<ItemEntity?> {
            return arrayOfNulls(size)
        }
    }
}
