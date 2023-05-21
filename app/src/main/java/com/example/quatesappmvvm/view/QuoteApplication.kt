package com.example.quatesappmvvm.view

import android.app.Application
import androidx.constraintlayout.widget.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.quatesappmvvm.service.db.QuoteDatabase
import com.example.quatesappmvvm.service.network.ApiClient
import com.example.quatesappmvvm.service.network.ApiInterface
import com.example.quatesappmvvm.service.repository.QuoteRepo
import com.example.quatesappmvvm.worker.QuoteWorker
import java.util.concurrent.TimeUnit

class QuoteApplication : Application() {

    lateinit var quoteRepo: QuoteRepo
    override fun onCreate() {
        super.onCreate()

        initialize()
        setUpWorker()
    }

    private fun setUpWorker() {
        // constraint for connected network
        val constraint =
            androidx.work.Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
        val workerRequest =
            PeriodicWorkRequest.Builder(QuoteWorker::class.java, 30, TimeUnit.MINUTES)
                .setConstraints(constraint).build()

        WorkManager.getInstance(this).enqueue(workerRequest)


    }

    private fun initialize() {
        val apiInterface = ApiClient.getInstance().create(ApiInterface::class.java)
        val quoteDao = QuoteDatabase.getDatabase(applicationContext)
        quoteRepo = QuoteRepo(apiInterface, quoteDao, applicationContext)
    }
}