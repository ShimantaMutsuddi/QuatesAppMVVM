package com.example.quatesappmvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quatesappmvvm.service.repository.QuoteRepo

class MainViewModelFactory(private val quoteRepository: QuoteRepo) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(quoteRepository) as T
    }
}