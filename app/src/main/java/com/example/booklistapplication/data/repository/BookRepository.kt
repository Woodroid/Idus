package com.example.booklistapplication.data.repository

import com.example.booklistapplication.data.remote.response.BookResponse
import retrofit2.Response

interface BookRepository {

    suspend fun getBookDetails(isbn13: String): Result<BookResponse>

}