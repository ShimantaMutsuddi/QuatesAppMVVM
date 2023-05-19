package com.example.quatesappmvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quatesappmvvm.R

import com.example.quatesappmvvm.service.network.ApiClient
import com.example.quatesappmvvm.service.network.ApiInterface
import com.example.quatesappmvvm.service.repository.QuoteRepo
import com.example.quatesappmvvm.viewmodel.MainViewModel
import com.example.quatesappmvvm.viewmodel.MainViewModelFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    lateinit var quoteRepo: QuoteRepo
    private  val TAG = "MainActivity"
    //lateinit  var quotesText:TextView
    lateinit  var recyclerView:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val apiInterface = ApiClient.getInstance().create(ApiInterface::class.java)


        recyclerView=findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        val adapter=QuoteAdapter()
        recyclerView.adapter=adapter

       // quotesText=findViewById<TextView>(R.id.quotesText)

        quoteRepo = QuoteRepo(apiInterface)

        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(quoteRepo))[MainViewModel::class.java]

       mainViewModel.quotes.observe(this){
           adapter.submitList(it.results)



          // quotesText.text = it.results.toString()


             //  Log.d(TAG, "results ${it.results.toString()}")
        }

    }
}