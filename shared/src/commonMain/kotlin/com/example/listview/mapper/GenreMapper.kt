package com.example.listview.mapper

import com.example.listview.model.Genre
import com.example.listview.model.GenreResponse

fun GenreResponse.toModel() = Genre(
    id = this.id,
    name = this.name
)