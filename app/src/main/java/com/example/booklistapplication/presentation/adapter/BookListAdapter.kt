package com.example.booklistapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.booklistapplication.databinding.BookListItemBinding
import com.example.booklistapplication.presentation.model.BookModel

class BookListAdapter : ListAdapter<BookModel, BookListViewHolder>(BookDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListViewHolder {
        return BookListViewHolder(
            BookListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BookListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}