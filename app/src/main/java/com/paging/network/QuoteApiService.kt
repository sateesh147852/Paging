package com.paging.network

import com.paging.model.Quotelist
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteApiService {

    @GET("quotes")
    suspend fun getQuotes(@Query("page") page: Int) : Quotelist
}