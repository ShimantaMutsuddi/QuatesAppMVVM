package com.example.quatesappmvvm.service.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://quotable.io/"

    fun getInstance() : Retrofit
    {
        /*return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()*/
        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }

}
/*
class ApiClient {



    companion object {
        val BASE_URL = "https://quotable.io/"

        private var instance: ApiClient? = null

        @Synchronized
        fun getInstance(): ApiClient {
            if (instance == null)
                instance = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()
                    .create(ApiClient::class.java)
            return instance as ApiClient


        }
    }

}*/
