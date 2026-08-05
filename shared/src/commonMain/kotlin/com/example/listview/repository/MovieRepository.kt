package com.example.listview.repository

import com.example.listview.model.MovieListResponse
import com.example.listview.network.KortClient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class MovieRepository(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val kortClient: KortClient
) {
    suspend fun getMoviesPopular(): MovieListResponse {
        return withContext(dispatcher) {
            val responseDeferred = async { kortClient.getMoviesPopular() }
            val response = responseDeferred.await()

            response

        }

    }

}