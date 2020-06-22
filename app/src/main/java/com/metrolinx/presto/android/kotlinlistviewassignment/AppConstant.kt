package com.metrolinx.presto.android.kotlinlistviewassignment

import android.content.Context
import android.net.ConnectivityManager

class AppConstant {
    companion object {
        const val url = "facts.json"
        const val BASE_URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/"
        fun isNetworkAvailable(context:Context):Boolean{
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = connectivityManager.activeNetworkInfo
            return netInfo != null && netInfo.isConnected

        }

    }

}