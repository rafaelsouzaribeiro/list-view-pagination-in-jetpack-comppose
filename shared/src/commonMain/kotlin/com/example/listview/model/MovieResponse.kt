package com.example.listview.model

import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MovieListResponse(
    val page: Int,
    val results: List<MovieResponse>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int
)

@Serializable
data class MovieResponse (
    val id: Int,
    val title: String,
    val overview: String,
    @SerialName("poster_path")
    val posterPath: String,
    val genres: List<GenreResponse>?=null,
    @SerialName("release_date")
    val releaseDate: String?=null,
    val runtime: Int?=null,
    @SerialName("vote_average")
    val voteAverage: Double,
)

@Serializable
data class GenreResponse(
    val id: Int,
    val name: String
)