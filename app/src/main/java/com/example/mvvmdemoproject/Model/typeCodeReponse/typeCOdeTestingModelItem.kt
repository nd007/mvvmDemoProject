package com.example.mvvmdemoproject.Model.typeCodeReponse


import com.google.gson.annotations.SerializedName

data class typeCOdeTestingModelItem(
    @SerializedName("body")
    val body: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int
)