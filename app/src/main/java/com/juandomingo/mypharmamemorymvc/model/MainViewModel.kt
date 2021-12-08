package com.juandomingo.mypharmamemorymvc.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.juandomingo.mypharmamemorymvc.model.PharmacoModel

class MainViewModel: ViewModel() {
    private val repository = Repository()
    fun fetchPharmaData(): LiveData<MutableList<PharmacoModel>> {
        val mutableData = MutableLiveData<MutableList<PharmacoModel>>()
        repository.getPharmaData().observeForever {
            mutableData.value = it
        }
        return mutableData
    }
}