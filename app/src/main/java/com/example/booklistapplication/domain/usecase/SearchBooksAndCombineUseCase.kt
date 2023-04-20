package com.example.booklistapplication.domain.usecase

import com.example.booklistapplication.domain.model.BookWithDetailsModel

interface SearchBooksAndCombineUseCase {

    suspend operator fun invoke(query: String): Result<List<BookWithDetailsModel>>

}