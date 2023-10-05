package com.example.mvvmdemoproject.Model.TestModel


import com.google.gson.annotations.SerializedName

data class testingModel(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("support")
    val support: Support
)