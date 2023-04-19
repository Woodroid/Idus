package com.example.booklistapplication.di

import com.example.booklistapplication.data.*
import com.example.booklistapplication.data.remote.api.BookApi
import com.example.booklistapplication.data.remote.api.SearchApi
import com.example.booklistapplication.data.repository.BookRepository
import com.example.booklistapplication.data.repository.BookRepositoryImpl
import com.example.booklistapplication.data.repository.SearchRepository
import com.example.booklistapplication.data.repository.SearchRepositoryImpl
import dagger.Binds
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
        return BookRepositoryImpl(bookApi)
    }

    @Singleton
    @Provides
    fun provideSearchRepository(searchApi: SearchApi): SearchRepository {
        return SearchRepositoryImpl(searchApi)
    }

}