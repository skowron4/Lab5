package com.skow.lab5.ui.items_list.placeholder

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
@Dao
interface ItemDao {
    @Query("SELECT * FROM items")
    fun getAllItems(): MutableList<ItemEntity>
    @Insert
    fun addItem(item: ItemEntity)
    @Update
    fun updateItem(item: ItemEntity)
    @Delete
    fun deleteItem(item: ItemEntity)
}