package com.example.mvvmdemoproject.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmdemoproject.Model.TestModel.testingModel
import com.example.mvvmdemoproject.Model.typeCodeReponse.typeCOdeTestingModel
import com.example.mvvmdemoproject.Util.NetworkResult
import com.example.mvvmdemoproject.models.UserRequest
import com.example.mvvmdemoproject.models.UserResponse
import com.example.mvvmdemoproject.network.DemoApi
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class DemoRepo @Inject constructor(private val demoApi: DemoApi) {

    private val _userResponseLiveData=MutableLiveData<NetworkResult<UserResponse>>()
    val userResponseLiveData:LiveData<NetworkResult<UserResponse>>
    get() = _userResponseLiveData

   /* private val _getUserResponseLiveData=MutableLiveData<NetworkResult<testingModel>>()
    val getUserResponseLiveData:LiveData<NetworkResult<testingModel>>
    get() = _getUserResponseLiveData*/


    private val _getUserResponseLiveData=MutableLiveData<NetworkResult<typeCOdeTestingModel>>()
    val getUserResponseLiveData:LiveData<NetworkResult<typeCOdeTestingModel>>
        get() = _getUserResponseLiveData


    suspend fun registerUser(userRequest: UserRequest) {
        _userResponseLiveData.postValue(NetworkResult.Loading())
        val response = demoApi.getData(userRequest)
        handleResponse(response)
    }

  /*  suspend fun getUserDetails(){
        _getUserResponseLiveData.postValue(NetworkResult.Loading())
        val response =demoApi.getUserData()
        handleResponseNew(response)
    }*/

    suspend fun getUserDetails() {
        _getUserResponseLiveData.postValue(NetworkResult.Loading())
        val response = demoApi.getu_Data()
        handleResponseNew(response)
    }


    private fun handleResponse(response: Response<UserResponse>) {
        try {
            if (response.isSuccessful && response.body() != null) {
                _userResponseLiveData.postValue(NetworkResult.Sucess(response.body()!!))
            }
            else if(response.errorBody()!=null){
                val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
                _userResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
            }
            else{
                _userResponseLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
            }
        }
        catch (E:Exception)
        {

        }

    }



    private fun handleResponseNew(response: Response<typeCOdeTestingModel>) {
        try {
            if (response.isSuccessful && response.body() != null) {
                _getUserResponseLiveData.postValue(NetworkResult.Sucess(response.body()!!))
            }
            else if(response.errorBody()!=null){
                val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
                _getUserResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
            }
            else{
                _getUserResponseLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
            }
        }
        catch (E:Exception)
        {

        }

    }


    /*
    suspend fun signUP(userRequest: UserRequest){
          val response=demoApi.getData(userRequest)
          if (response.isSuccessful && response.body()!=null)
          {
              _userResponseLiveData.postValue(NetworkResult.Sucess(response.body()!!))
          }
          if (response.errorBody()!=null)
          {
              var msg=response.errorBody().toString()!!
              _userResponseLiveData.postValue(NetworkResult.Error(msg))

          }



      }
      */


}