package com.example.quatesappmvvm.service.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quatesappmvvm.service.db.QuoteDao
import com.example.quatesappmvvm.service.db.QuoteDatabase
import com.example.quatesappmvvm.service.model.QuoteList
import com.example.quatesappmvvm.service.network.ApiClient
import com.example.quatesappmvvm.service.network.ApiInterface
import com.example.quatesappmvvm.util.NetworkUtils

class QuoteRepo(
    private val apiInterface: ApiInterface,
    private val quoteDatabase: QuoteDatabase,
    private val context: Context
) {

    //to hold quoteList
    private val quotesLivaData=MutableLiveData<QuoteList>()

    //accessable livedata
    val quotes:LiveData<QuoteList>
       get()=quotesLivaData

     suspend fun getQuotes(page:Int)
    {

        var isNetwork:Boolean=NetworkUtils.isInternetAvailable(context)
        if(isNetwork) {
            val result=apiInterface.getQuotes(page)
            if (result?.body() != null) {
                quoteDatabase.quoteDao().addQuotes(result.body()!!.results)
                quotesLivaData.postValue(result.body())

            }
        }
        else{
                val quotes=quoteDatabase.quoteDao().getQuotes()
                val quoteList=QuoteList(1,1,1,quotes,1,1)

                quotesLivaData.postValue(quoteList)
            }



    }

    suspend fun getQuotesBackground()
    {

        val randomNumber=(Math.random()*10).toInt()
        val result=apiInterface.getQuotes(randomNumber)
        if(result?.body()!=null)
        {
            quoteDatabase.quoteDao().addQuotes(result.body()!!.results)

        }


    }
}