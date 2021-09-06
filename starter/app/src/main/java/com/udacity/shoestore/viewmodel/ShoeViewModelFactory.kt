package com.udacity.shoestore.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.models.Shoes

class ShoeViewModelFactory(private val shoes: Shoes?): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShoeViewModel::class.java)) {
            return ShoeViewModel(shoes) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}