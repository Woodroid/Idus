package com.example.booklistapplication.data.repository

import com.example.booklistapplication.data.remote.api.SearchApi
import com.example.booklistapplication.domain.model.BookModel
import com.example.booklistapplication.domain.repository.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

class SearchBookRepositoryImpl @Inject constructor(private val searchApi: SearchApi) :
    SearchRepository {

    override suspend fun search(query: String): Result<List<BookModel>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = searchApi.search(query)
                if (response.isSuccessful) {
                    val books = response.body()?.books?.map {
                        BookModel(
                            title = it.title,
                            subtitle = it.subtitle,
                            price = it.price,
                            image = it.image,
                            url = it.url,
                            isbn13 = it.isbn13
                        )
                    } ?: emptyList()
                    Result.success(books)
                } else {
                    Result.failure(HttpException(response))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}
