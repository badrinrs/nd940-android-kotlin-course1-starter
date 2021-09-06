package com.udacity.shoestore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.Shoes
import timber.log.Timber

class ShoeViewModel(shoes: Shoes?): ViewModel() {

    private var _shoeList =  MutableLiveData<Shoes>()
    val shoeList: LiveData<Shoes>
        get() = _shoeList

    init {
        var existingShoeList: Shoes? = _shoeList.value
        if(existingShoeList==null) {
            existingShoeList = shoes
        } else {
            shoes?.let {
                existingShoeList.addAll(it)
            }
        }
        _shoeList.value = existingShoeList
    }
}