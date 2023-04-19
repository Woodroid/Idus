package com.example.booklistapplication.data.remote.api

import com.example.booklistapplication.data.remote.response.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SearchApi {

    @GET("search/{query}")
    suspend fun search(@Path("query") query: String): Response<SearchResponse>

}