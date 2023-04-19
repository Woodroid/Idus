package com.example.booklistapplication.domain.usecase

import com.example.booklistapplication.data.repository.BookRepository
import com.example.booklistapplication.data.repository.SearchRepository
import com.example.booklistapplication.domain.model.BookWithDetails
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class SearchBooksAndCombineUseCaseImpl @Inject constructor(
    private val searchRepository: SearchRepository,
    private val bookRepository: BookRepository
) : SearchBooksAndCombineUseCase {

    // Search Book 응답 성공여부 확인
    // Book 상세정보 응답 성공여부 확인

    override suspend operator fun invoke(query: String): Result<List<BookWithDetails>> = coroutineScope {
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
                BookWithDetails(
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