package com.example.booklistapplication.domain.usecase

import com.example.booklistapplication.domain.repository.BookRepository
import com.example.booklistapplication.domain.repository.SearchRepository
import com.example.booklistapplication.domain.model.BookWithDetailsModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class SearchBooksAndCombineUseCaseImpl @Inject constructor(
    private val searchRepository: SearchRepository,
    private val bookRepository: BookRepository
) : SearchBooksAndCombineUseCase {

    override suspend operator fun invoke(query: String): Result<List<BookWithDetailsModel>> = coroutineScope {
        val searchResult = searchRepository.search(query)
        if (searchResult.isSuccess) {
            val books = searchResult.getOrThrow()

            val bookDetailsDeferreds = books.map {
                async {
                    val bookDetailsResult = bookRepository.getBookDetails(it.isbn13)
                    bookDetailsResult.getOrThrow()
                }
            }
            val bookDetails = bookDetailsDeferreds.awaitAll()

            val results = bookDetails.map {
                BookWithDetailsModel(
                    title = it.title,
                    authors = it.authors,
                    publisher = it.publisher,
                    image = it.image,
                    price = it.price
                )
            }
            Result.success(results)
        } else {
            Result.failure(searchResult.exceptionOrNull()!!)
        }
    }

}