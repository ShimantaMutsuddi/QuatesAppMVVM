package com.example.quatesappmvvm.service.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quatesappmvvm.service.model.QuoteList
import com.example.quatesappmvvm.service.network.ApiClient
import com.example.quatesappmvvm.service.network.ApiInterface

class QuoteRepo(private val apiInterface: ApiInterface) {

    private val quotesLivaData=MutableLiveData<QuoteList>()

    val quotes:LiveData<QuoteList>
       get()=quotesLivaData

     suspend fun getQuotes(page:Int)
    {
        val result=apiInterface.getQuotes(page)
        if(result?.body() != null)
        {
            quotesLivaData.postValue(result.body())
        }

    }
}