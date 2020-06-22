package com.accenture.codingassignment.networking


import com.metrolinx.presto.android.kotlinlistviewassignment.AppConstant
import com.metrolinx.presto.android.kotlinlistviewassignment.DataModel.DataModelClass
import retrofit2.http.GET

import retrofit2.Call

internal interface NetworkApiEndPoint {

    @GET(AppConstant.url)
    fun getListData(): Call<DataModelClass>

}