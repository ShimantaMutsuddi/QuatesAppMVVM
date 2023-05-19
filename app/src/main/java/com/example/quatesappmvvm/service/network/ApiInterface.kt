package com.example.quatesappmvvm.service.network

import androidx.lifecycle.LiveData
import com.example.quatesappmvvm.service.model.QuoteList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/quotes")
     suspend fun getQuotes(@Query("page") page: Int): Response<QuoteList>

}