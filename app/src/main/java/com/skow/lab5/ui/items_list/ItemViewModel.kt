package com.skow.lab5.ui.items_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.skow.lab5.ui.items_list.placeholder.ItemEntity
import com.skow.lab5.ui.items_list.placeholder.Repository

class ItemViewModel(application: Application) : AndroidViewModel(application) {
    //list database
//    private val _items = MutableLiveData<List<Placeholder.PlaceholderItem>>()
//    val items: LiveData<List<Placeholder.PlaceholderItem>> get() = _items

    //database SQLite
    private val _items = MutableLiveData<List<ItemEntity>>()
    val items: LiveData<List<ItemEntity>> get() = _items

   val dataBase: Repository

    init {
//        _items.value = Placeholder.ITEMS
        dataBase = Repository.getInstance(application)
        _items.value = dataBase.getData().toList()
    }

    fun addItem(item: ItemEntity){
        dataBase.addItem(item)
        _items.value = dataBase.getData().toList()
    }

    fun updateItem(item: ItemEntity){
        dataBase.updateItem(item)
        _items.value = dataBase.getData().toList()
    }

    fun deleteItem(item: ItemEntity?){
        dataBase.deleteItem(item!!)
        _items.value = dataBase.getData().toList()
    }

//    fun addItem(item: Placeholder.PlaceholderItem) {
//        Placeholder.ITEMS.add(item)
//        _items.value = Placeholder.ITEMS.toList()
//    }
//    fun updateItem(updatedItem: Placeholder.PlaceholderItem) {
//        val index = Placeholder.ITEMS.indexOfFirst { it.id == updatedItem.id }
//        if (index != -1) {
//            Placeholder.ITEMS[index] = updatedItem
//            _items.value = Placeholder.ITEMS.toList()
//        }
//    }
//
//    fun deleteItem(item: Placeholder.PlaceholderItem?) {
//        Placeholder.removeItem(item?.id!!)
//        _items.value = Placeholder.ITEMS.toList()
//    }
}