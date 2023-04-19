package com.example.booklistapplication.data.repository

import com.example.booklistapplication.data.remote.api.SearchApi
import com.example.booklistapplication.data.remote.model.BookModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val searchApi: SearchApi) :
    SearchRepository {

    override suspend fun search(query: String): Result<List<BookModel>> {
        return try {
            val response = withContext(Dispatchers.IO) { searchApi.search(query) }
            if (response.isSuccessful) {
                val books = response.body()?.books ?: emptyList()
                Result.success(books)
            } else {
                Result.failure(HttpException(response))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}