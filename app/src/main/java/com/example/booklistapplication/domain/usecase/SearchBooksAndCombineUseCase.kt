package com.example.booklistapplication.domain.usecase

import com.example.booklistapplication.domain.model.BookWithDetails

interface SearchBooksAndCombineUseCase {

    suspend operator fun invoke(query: String): Result<List<BookWithDetails>>

}