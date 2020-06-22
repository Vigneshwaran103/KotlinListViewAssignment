package com.accenture.codingassignment.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.accenture.codingassignment.repository.Repo
import com.metrolinx.presto.android.kotlinlistviewassignment.DataModel.DataModelClass

class MainViewModel() : ViewModel() {
    lateinit var context :Context
    private val repository = Repo()
    val listData: LiveData<DataModelClass>
    val isResponseFromAPI: LiveData<Boolean>

    init {
        this.listData = repository.listData
        this.isResponseFromAPI = repository.isResponseFromAPI
    }

    fun getDataFromAPI() {
        repository.fetchDataList(context)
    }

    fun setApplicationContext(context : Context){
        this.context = context
    }

    fun updateValue(){
        repository.isResponseFromAPI.value=false

    }


}