package com.example.mvvmdemoproject.network

import com.example.mvvmdemoproject.Model.TestModel.testingModel
import com.example.mvvmdemoproject.Model.typeCodeReponse.typeCOdeTestingModel
import com.example.mvvmdemoproject.models.UserRequest
import com.example.mvvmdemoproject.models.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface DemoApi {

    @POST("/users/signup")
    suspend fun getData(@Body userrequest: UserRequest): Response<UserResponse>

    //@GET("/users?page=2")
    @GET("/users/2")
    suspend fun getUserData(): Response<testingModel>

    @GET("/posts")
    suspend fun getu_Data(): Response<typeCOdeTestingModel>



    @POST("/users/signin")
    suspend fun getSignIn(@Body userrequest: UserRequest): Response<UserResponse>


}