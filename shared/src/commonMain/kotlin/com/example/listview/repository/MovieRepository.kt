package com.example.listview.repository

import com.example.listview.mapper.toModel
import com.example.listview.model.Movie
import com.example.listview.model.PagedResult
import com.example.listview.network.KortClient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class MovieRepository(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val kortClient: KortClient
) {
    suspend fun getPopularMoviesPage(page: Int): PagedResult<Movie> {
        val response = withContext(dispatcher) { kortClient.getMoviesPopular(page) }

        val mapped = response.results.map { it.toModel() }
        val endReached = response.page >= response.totalPages

        return PagedResult(
            items = mapped,
            page = response.page,
            totalPages = response.totalPages,
            endReached = endReached
        )

    }
}