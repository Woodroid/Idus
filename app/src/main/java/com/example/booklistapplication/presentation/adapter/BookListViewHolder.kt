package com.example.booklistapplication.presentation.adapter

import android.util.Log
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.booklistapplication.databinding.BookListItemBinding
import com.example.booklistapplication.domain.model.BookWithDetails

class BookListViewHolder(val binding: BookListItemBinding): ViewHolder(binding.root) {

    fun bind(item: BookWithDetails) {
        Log.d("woo", "${item.title}")
        binding.image = item.image
        binding.title = item.title
        binding.author = item.authors
        binding.publisher = item.publisher
        binding.price = item.price

        binding.executePendingBindings()
    }

}