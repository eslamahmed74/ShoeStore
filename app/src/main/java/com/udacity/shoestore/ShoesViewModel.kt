package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoesViewModel : ViewModel() {
    private val _shoesItems = MutableLiveData<MutableList<Shoe>>()
    val shoesItem: LiveData<MutableList<Shoe>>
        get() = _shoesItems

    fun addNewItem(shoe: Shoe) {
        if (_shoesItems.value == null)
            _shoesItems.value = mutableListOf(shoe)
        else
            _shoesItems.value?.add(shoe)
    }
}