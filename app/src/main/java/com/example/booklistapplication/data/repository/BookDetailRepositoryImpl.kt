package com.example.booklistapplication.data.repository

import com.example.booklistapplication.data.remote.api.BookApi
import com.example.booklistapplication.data.remote.response.BookDetailResponse
import com.example.booklistapplication.domain.repository.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

class BookDetailRepositoryImpl @Inject constructor(private val bookApi: BookApi) : BookRepository {

    override suspend fun getBookDetails(isbn13: String): Result<BookDetailResponse> {
        return try {
            val response = withContext(Dispatchers.IO) { bookApi.getBookDetails(isbn13) }
            if (response.isSuccessful) {
                val bookDetails = response.body()
                if (bookDetails != null) {
                    Result.success(bookDetails)
                } else {
                    Result.failure(NullPointerException("Response Body null"))
                }
            } else {
                Result.failure(HttpException(response))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}