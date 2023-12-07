package com.skow.lab5.ui.items_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skow.lab5.ui.items_list.placeholder.Placeholder

class ItemViewModel : ViewModel() {
    private val _items = MutableLiveData<List<Placeholder.PlaceholderItem>>()
    val items: LiveData<List<Placeholder.PlaceholderItem>> get() = _items

    init {
        _items.value = Placeholder.ITEMS
    }

    fun addItem(item: Placeholder.PlaceholderItem) {
        Placeholder.ITEMS.add(item)
        _items.value = Placeholder.ITEMS.toList()
    }

    fun updateItem(updatedItem: Placeholder.PlaceholderItem) {
        val index = Placeholder.ITEMS.indexOfFirst { it.id == updatedItem.id }
        Log.i("dziala", index.toString())
        if (index != -1) {
            Placeholder.ITEMS[index] = updatedItem
            _items.value = Placeholder.ITEMS.toList()
        }
    }

    fun deleteItem(item: Placeholder.PlaceholderItem?) {
        Placeholder.removeItem(item?.id!!)
        _items.value = Placeholder.ITEMS.toList()
    }
}