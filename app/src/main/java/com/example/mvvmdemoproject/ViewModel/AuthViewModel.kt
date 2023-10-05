package com.example.mvvmdemoproject.ViewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import com.example.mvvmdemoproject.Model.TestModel.testingModel
import com.example.mvvmdemoproject.Model.typeCodeReponse.typeCOdeTestingModel

import com.example.mvvmdemoproject.Repository.DemoRepo
import com.example.mvvmdemoproject.Util.NetworkResult
import com.example.mvvmdemoproject.models.UserRequest
import com.example.mvvmdemoproject.models.UserResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repo: DemoRepo) : ViewModel() {

    lateinit var context: Context

    val userResponseLiveData: LiveData<NetworkResult<UserResponse>>
        get() = repo.userResponseLiveData


    val getUserDetailsLiveData:LiveData<NetworkResult<typeCOdeTestingModel>>
    get() = repo.getUserResponseLiveData


    fun signup(userRequest: UserRequest) {
    Log.e("## userResponse",userResponseLiveData.toString())
        viewModelScope.launch {
            repo.registerUser(userRequest)
            Log.e("## IN authViewModel",repo.registerUser(userRequest).toString())

        }
    }


    fun getUserDetails(){
        viewModelScope.launch {
            repo.getUserDetails()
        }
    }


}