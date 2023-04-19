package com.example.booklistapplication.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booklistapplication.domain.model.BookWithDetails
import com.example.booklistapplication.domain.usecase.SearchBooksAndCombineUseCase
import com.example.booklistapplication.presentation.ui.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val searchBooksAndCombineUseCase: SearchBooksAndCombineUseCase) :
    ViewModel() {

    private val _books = MutableLiveData<UIState<List<BookWithDetails>>>()
    val books: LiveData<UIState<List<BookWithDetails>>>
        get() = _books

    fun getBookWithDetails(query: String) {
        viewModelScope.launch {

            val result = searchBooksAndCombineUseCase(query)
            _books.postValue(UIState.Loading)

            result.onSuccess { books ->
                _books.postValue(UIState.Success(books))
            }.onFailure { error ->
                _books.postValue(UIState.Error(error.message ?: "알수 없는 오류가 발생했습니다."))
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }

}