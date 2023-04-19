package com.example.booklistapplication.data.remote.api

import com.example.booklistapplication.data.remote.response.BookResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BookApi {

    @GET("books/{isbn13}")
    suspend fun getBookDetails(@Path("isbn13") isbn13: String): Response<BookResponse>

}