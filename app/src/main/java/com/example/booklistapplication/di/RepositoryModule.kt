package com.example.booklistapplication.di

import com.example.booklistapplication.data.*
import com.example.booklistapplication.data.remote.api.BookApi
import com.example.booklistapplication.data.remote.api.SearchApi
import com.example.booklistapplication.domain.repository.BookRepository
import com.example.booklistapplication.data.repository.BookDetailRepositoryImpl
import com.example.booklistapplication.domain.repository.SearchRepository
import com.example.booklistapplication.data.repository.SearchBookRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideBookRepository(bookApi: BookApi): BookRepository {
        return BookDetailRepositoryImpl(bookApi)
    }

    @Singleton
    @Provides
    fun provideSearchRepository(searchApi: SearchApi): SearchRepository {
        return SearchBookRepositoryImpl(searchApi)
    }

}