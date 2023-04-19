package com.example.booklistapplication.data.repository

import com.example.booklistapplication.data.remote.model.BookModel
import com.example.booklistapplication.data.remote.response.SearchResponse
import retrofit2.Response

interface SearchRepository {

    suspend fun search(query: String): Result<List<BookModel>>

}