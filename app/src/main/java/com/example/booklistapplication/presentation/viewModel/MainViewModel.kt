package com.example.booklistapplication.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booklistapplication.domain.usecase.SearchBooksAndCombineUseCase
import com.example.booklistapplication.presentation.model.BookModel
import com.example.booklistapplication.presentation.ui.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val searchBooksAndCombineUseCase: SearchBooksAndCombineUseCase) :
    ViewModel() {

    private val _books = MutableLiveData<UIState<List<BookModel>>>()
    val books: LiveData<UIState<List<BookModel>>>
        get() = _books

    fun getBookWithDetails(query: String) {
        viewModelScope.launch {

            val result = searchBooksAndCombineUseCase(query)
            _books.postValue(UIState.Loading)

            result.onSuccess { books ->
                _books.postValue(UIState.Success(books.map {
                    BookModel(
                        title = it.title,
                        authors = it.authors,
                        publisher = it.publisher,
                        image = it.image,
                        price = it.price
                    )
                }))
            }.onFailure { error ->
                _books.postValue(UIState.Error(error.message ?: "알수 없는 오류가 발생했습니다."))
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }

}