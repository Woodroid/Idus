package com.example.booklistapplication.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.booklistapplication.presentation.model.BookModel

class BookDiffCallback: DiffUtil.ItemCallback<BookModel>() {

    override fun areItemsTheSame(oldItem: BookModel, newItem: BookModel): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: BookModel, newItem: BookModel): Boolean {
        return oldItem.image == newItem.image
    }
}