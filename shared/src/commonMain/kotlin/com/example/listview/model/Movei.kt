package com.example.listview.model

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterUrl: String,
    val genres: List<Genre>?,
    val year: Int,
    val duration:String?,
    val rating: String,
    val castMembers: List<CastMember>?,
    val movieTrailerYoutubeKey: String?
)