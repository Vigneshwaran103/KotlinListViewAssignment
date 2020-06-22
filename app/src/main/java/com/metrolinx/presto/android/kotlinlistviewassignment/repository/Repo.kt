package com.accenture.codingassignment.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.accenture.codingassignment.networking.NetworkApiEndPoint
import com.accenture.codingassignment.networking.RetrofitClient
import com.metrolinx.presto.android.kotlinlistviewassignment.DataModel.DataModelClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repo {
    val listData = MutableLiveData<DataModelClass>()
    var isResponseFromAPI = MutableLiveData<Boolean>()

    fun fetchDataList(context: Context) {

        val retrofit = RetrofitClient.getRetrofitClient(context)

        val service = retrofit?.create(NetworkApiEndPoint::class.java)

        service?.getListData()?.enqueue(object : Callback<DataModelClass> {

            override fun onFailure(call: Call<DataModelClass>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<DataModelClass>,
                response: Response<DataModelClass>
            ) {
                if (response.body() != null)
                    isResponseFromAPI.value = true;
                listData.value = response.body()
            }
        })
    }
}