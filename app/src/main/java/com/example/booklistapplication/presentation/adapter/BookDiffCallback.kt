package com.example.booklistapplication.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.booklistapplication.domain.model.BookWithDetails

class BookDiffCallback: DiffUtil.ItemCallback<BookWithDetails>() {

    override fun areItemsTheSame(oldItem: BookWithDetails, newItem: BookWithDetails): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: BookWithDetails, newItem: BookWithDetails): Boolean {
        return oldItem.image == newItem.image
    }
}