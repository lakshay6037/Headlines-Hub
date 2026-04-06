package com.example.headlinejetpackcompose.data.remote

import com.example.headlinejetpackcompose.data.remote.dto.NewsResponse
import com.example.headlinejetpackcompose.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/everything")
    suspend fun getNews(
        @Query("page`") page: Int?,
        @Query("sources") source: String,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse


    @GET("v2/everything")
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("page`") page: Int?,
        @Query("sources") source: String,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse
}