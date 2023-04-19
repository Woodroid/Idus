package com.example.booklistapplication.di

import com.example.booklistapplication.data.repository.BookRepository
import com.example.booklistapplication.data.repository.SearchRepository
import com.example.booklistapplication.domain.usecase.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideSearchBooksAndCombineUseCase(
        searchRepository: SearchRepository,
        bookRepository: BookRepository
    ): SearchBooksAndCombineUseCase {
        return SearchBooksAndCombineUseCaseImpl(searchRepository, bookRepository)
    }

}