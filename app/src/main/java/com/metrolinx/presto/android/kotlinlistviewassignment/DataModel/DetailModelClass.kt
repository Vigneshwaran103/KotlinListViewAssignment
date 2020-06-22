package com.metrolinx.presto.android.kotlinlistviewassignment.DataModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class DetailModelClass(val title:String,
                            val description:String,
                            @SerializedName("imageHref")
                            @Expose
                            val image:String)