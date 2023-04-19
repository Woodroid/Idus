package com.example.booklistapplication.data.remote.response

import com.example.booklistapplication.data.remote.model.BookModel
import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("total") val total: String,
    @SerializedName("page") val page: String,
    @SerializedName("books") val books: List<BookModel>
)