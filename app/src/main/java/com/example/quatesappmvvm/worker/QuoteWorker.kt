package com.example.quatesappmvvm.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.quatesappmvvm.view.QuoteApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuoteWorker(private val context:Context,private val params:WorkerParameters):Worker(context,params) {
    private  val TAG = "QuoteWorker"
    override fun doWork(): Result {
        Log.d(TAG, "WorkerCalled")
        val quoteRepo=(context as QuoteApplication).quoteRepo

        CoroutineScope(Dispatchers.IO).launch {
            quoteRepo.getQuotesBackground()

        }

        return Result.success()
    }
}