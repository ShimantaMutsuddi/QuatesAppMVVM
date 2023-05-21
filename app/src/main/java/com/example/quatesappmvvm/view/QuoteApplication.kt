package com.example.quatesappmvvm.view

import android.app.Application
import com.example.quatesappmvvm.service.db.QuoteDatabase
import com.example.quatesappmvvm.service.network.ApiClient
import com.example.quatesappmvvm.service.network.ApiInterface
import com.example.quatesappmvvm.service.repository.QuoteRepo

class QuoteApplication: Application(){

    lateinit var quoteRepo: QuoteRepo
    override fun onCreate() {
        super.onCreate()

        initialize()
    }

    private fun initialize() {
        val apiInterface = ApiClient.getInstance().create(ApiInterface::class.java)
        val quoteDao= QuoteDatabase.getDatabase(applicationContext)
        quoteRepo = QuoteRepo(apiInterface,quoteDao,applicationContext)
    }
}