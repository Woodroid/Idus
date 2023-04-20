package com.example.booklistapplication.domain.repository

import com.example.booklistapplication.data.remote.response.BookDetailResponse

interface BookRepository {

    suspend fun getBookDetails(isbn13: String): Result<BookDetailResponse>

}