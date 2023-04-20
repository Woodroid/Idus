package com.example.booklistapplication.domain.repository

import com.example.booklistapplication.domain.model.BookModel

interface SearchRepository {

    suspend fun search(query: String): Result<List<BookModel>>

}