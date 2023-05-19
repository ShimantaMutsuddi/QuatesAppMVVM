package com.example.quatesappmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quatesappmvvm.service.model.QuoteList
import com.example.quatesappmvvm.service.repository.QuoteRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val quoteRepo: QuoteRepo): ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            quoteRepo.getQuotes(1)
        }

    }

    val quotes: LiveData<QuoteList>
        get()=quoteRepo.quotes

}