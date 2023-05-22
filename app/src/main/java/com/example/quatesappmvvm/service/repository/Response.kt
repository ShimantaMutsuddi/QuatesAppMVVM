package com.example.quatesappmvvm.service.repository

import com.example.quatesappmvvm.service.model.QuoteList

/*sealed class Response() {
    class Loading : Response()
    class Success(val quoteList: QuoteList): Response()
    class Error(val errorMessage :String) : Response()
}*/
/*
sealed class Response(val  quoteList: QuoteList?=null, val errorMessage :String?=null) {
    class Loading : Response()
    class Success( quoteList: QuoteList): Response(quoteList=quoteList)
    class Error( errorMessage :String) : Response(errorMessage=errorMessage)
}*/

sealed class Response<T>(val  data: T?=null, val errorMessage :String?=null) {
    class Loading<T> : Response<T>()
    class Success<T>(  data: T?=null): Response<T>(data=data)
    class Error<T>( errorMessage :String) : Response<T>(errorMessage=errorMessage)
}
