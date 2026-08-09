package com.example.listview.mapper

import com.example.listview.model.CastMemberResponse
import com.example.listview.model.ImageSize
import com.example.listview.model.Movie
import com.example.listview.model.MovieResponse
import com.example.listview.network.IMAGE_BASE_URL
import com.example.listview.utils.formatRating
import kotlinx.datetime.LocalDate


fun MovieResponse.toModel(
    castMemberResponse: List<CastMemberResponse>?=null,
    movieTrailerYoutubeKey:String?=null,
    imageSize: ImageSize = ImageSize.SMALL,
)  = Movie(
    id = this.id,
    title = this.title,
    overview = this.overview,
    posterUrl = "$IMAGE_BASE_URL/${imageSize.size}/${this.posterPath}",
    genres = this.genres?.map { it.toModel() },
    year = this.getYearGenresFromReleaseDate(),
    duration = this.getDurationForHourandMinutes(),
    rating = this.voteAverage.formatRating(),
    castMembers = castMemberResponse
        ?.filter { it.department == "Acting" }
        ?.take(20)
        ?.map { it.toModel() },
    movieTrailerYoutubeKey = movieTrailerYoutubeKey

)

private fun MovieResponse.getYearGenresFromReleaseDate():Int{
    return releaseDate
        ?.let { runCatching { LocalDate.parse(it).year }.getOrNull() }
        ?: 0
}

private fun MovieResponse.getDurationForHourandMinutes():String?{
    return  this.runtime?.let {
        val hours = it / 60
        val minutes = it % 60

        buildString {
            if (hours > 0) {
                append("${hours}h ")
            }
            if (minutes > 0) {
                append("${minutes}m")
            }
        }
    }

}