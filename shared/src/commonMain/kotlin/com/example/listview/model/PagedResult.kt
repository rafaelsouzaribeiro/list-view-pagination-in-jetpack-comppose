package com.example.listview.model

data class PagedResult<T>(
    val items: List<T>,
    val page: Int,
    val totalPages: Int,
    val endReached: Boolean
)