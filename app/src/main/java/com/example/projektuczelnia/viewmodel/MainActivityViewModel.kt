package com.example.projektuczelnia.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.projektuczelnia.data.company


class MainActivityViewModel(app: Application) : AndroidViewModel(app) {
    private val _myData = MutableLiveData<company>()
    val myData: LiveData<company>
        get() = _myData

    fun updateData(newData: company) {
        _myData.value = newData
    }
}