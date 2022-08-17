package com.udacity.shoestore

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class DetailViewModel : ViewModel() {
    val name = MutableLiveData<String>()
    val company = MutableLiveData<String>()
    val size = MutableLiveData<String>()
    val description = MutableLiveData<String>()

    fun crateNewItem(): Shoe {
        return Shoe(
            name.value ?: "",
            size.value?.toDouble()!! ,
            company.value ?: "",
            description.value ?: ""
        )

    }
}