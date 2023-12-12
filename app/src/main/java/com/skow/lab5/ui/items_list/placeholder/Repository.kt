package com.skow.lab5.ui.items_list.placeholder

import android.content.ClipData.Item
import android.content.Context

class Repository(context: Context) {
    private var itemDao: ItemDao
    private var appDb: MyDB

    init {
        appDb = MyDB.getDatabase(context)!!
        itemDao = appDb.itemDao()
//        addItem(ItemEntity(1, "Kai", 9f, "M","Czerwony ninja"))
//        addItem(ItemEntity(2, "Zane", 9f, "M","Bialy ninja"))
//        addItem(ItemEntity(3, "Jay", 9f, "M","Niebieski ninja"))
//        addItem(ItemEntity(4, "Cole", 9f, "M","Czarny ninja"))
//        addItem(ItemEntity(5, "Sensei Wu", 12f, "M","Mistrz ninja"))
//        addItem(ItemEntity(6, "Nya", 8f, "K", "Siostra Kai'a"))
//        addItem(ItemEntity(7, "Lord Garmadon", 12f, "M","Brat Wu"))
//        addItem(ItemEntity(8, "Skylar", 6f, "K", "W sumie nie wiem kto to"))
//        addItem(ItemEntity(9, "Pixal", 5f, "K", "Tej tez nie znam"))
//        addItem(ItemEntity(10, "Ksiezna Qi", 4f, "K", "O tej nigdy nie slyszalem"))
//        addItem(ItemEntity(11, "Harumi", 4f, "K", "Kto to jest?"))
//        addItem(ItemEntity(12, "Szef INVIL", 10f, "M","Szef wszystkich szefow"))
//        addItem(ItemEntity(13, "Pierwszy mistrz spinjitzu", 15f, "M", "Nie wiem, moze tak moze nie"))
    }

    fun getData(): MutableList<ItemEntity> {
        return itemDao.getAllItems()
    }

    fun addItem(item: ItemEntity)  {
        itemDao.addItem(item)
    }

    fun updateItem(item: ItemEntity){
        itemDao.updateItem(item)
    }

    fun deleteItem(item: ItemEntity){
        itemDao.deleteItem(item)
    }

    fun getItem(id: Int): ItemEntity {
        return itemDao.getItem(id)
    }

    companion object {
        private var R_INSTANCE: Repository? = null
        fun getInstance(context: Context): Repository {
            if (R_INSTANCE === null){
                R_INSTANCE = Repository(context)
            }
            return R_INSTANCE as Repository
        }
    }
}