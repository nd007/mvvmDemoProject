package com.example.mvvmdemoproject.di

import com.example.mvvmdemoproject.Util.Constants
import com.example.mvvmdemoproject.Util.Constants.BASE_URL
import com.example.mvvmdemoproject.network.DemoApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class networkModule {


    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }



    @Singleton
    @Provides
    fun provideUserApi(retrofitBuilder: Retrofit.Builder):DemoApi{
       return retrofitBuilder.build().create(DemoApi::class.java)
    }
}