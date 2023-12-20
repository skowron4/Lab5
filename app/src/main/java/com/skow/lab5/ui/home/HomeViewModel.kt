package com.skow.lab5.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skow.lab5.R

class HomeViewModel : ViewModel() {
    val hello = MutableLiveData("Hello my friend!")
    val desc = MutableLiveData("Opis nie wiem czego w sumie")
    var imgId = MutableLiveData( R.drawable.kai )
}