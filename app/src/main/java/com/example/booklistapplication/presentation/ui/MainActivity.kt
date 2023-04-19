package com.example.booklistapplication.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.booklistapplication.databinding.ActivityMainBinding
import com.example.booklistapplication.domain.model.BookWithDetails
import com.example.booklistapplication.presentation.adapter.BookListAdapter
import com.example.booklistapplication.presentation.ui.state.UIState
import com.example.booklistapplication.presentation.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = BookListAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.books.observe(this) { uiState ->
            when (uiState) {
                UIState.Loading -> showLoading()
                is UIState.Success -> showSuccess(uiState.data, adapter)
                is UIState.Error -> showError(uiState.message)

            }
        }

        viewModel.getBookWithDetails("android")
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE
    }

    private fun showSuccess(books: List<BookWithDetails>, adapter: BookListAdapter) {
        binding.progressBar.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE
        adapter.submitList(books)
    }

    private fun showError(errorMessage: String) {
        binding.progressBar.visibility = View.GONE
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }
}