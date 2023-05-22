package com.example.quatesappmvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quatesappmvvm.R

import com.example.quatesappmvvm.service.repository.QuoteRepo
import com.example.quatesappmvvm.service.repository.Response
import com.example.quatesappmvvm.viewmodel.MainViewModel
import com.example.quatesappmvvm.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    lateinit var quoteRepo: QuoteRepo
    private  val TAG = "MainActivity"
    //lateinit  var quotesText:TextView
    lateinit  var recyclerView:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        quoteRepo= (application as QuoteApplication).quoteRepo



        recyclerView=findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        val adapter=QuoteAdapter()
        recyclerView.adapter=adapter


        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(quoteRepo))[MainViewModel::class.java]

       mainViewModel.quotes.observe(this){

           //Toast.makeText(this@MainActivity,it.results.size.toString(),Toast.LENGTH_SHORT).show()
           when(it)
           {
               is Response.Loading ->{}
               is Response.Success ->{
                   adapter.submitList(it.data?.results)
               }
               is Response.Error ->{
                   Toast.makeText(this@MainActivity,it.errorMessage.toString(),Toast.LENGTH_SHORT).show()
               }
           }




          // quotesText.text = it.results.toString()


             //  Log.d(TAG, "results ${it.results.toString()}")
        }


    }
}