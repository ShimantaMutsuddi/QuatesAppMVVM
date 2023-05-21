package com.example.quatesappmvvm.service.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.quatesappmvvm.service.model.Result
import retrofit2.http.GET

@Dao
interface QuoteDao {

    @Insert
    suspend fun addQuotes(quotes: List<Result>)

    @Query("SELECT * FROM quote")
    suspend fun getQuotes():List<Result>
}