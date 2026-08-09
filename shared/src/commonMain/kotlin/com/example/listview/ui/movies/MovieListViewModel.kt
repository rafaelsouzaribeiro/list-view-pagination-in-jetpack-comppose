package com.example.listview.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listview.model.Movie
import com.example.listview.model.PagedResult
import com.example.listview.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    private val _moviesListState = MutableStateFlow<MoviesListStates>(MoviesListStates.Loading)
    val movieListStates = _moviesListState.asStateFlow()

    private var currentPage = 0
    private val _isPaginating = MutableStateFlow(false)
    val isPaginating = _isPaginating.asStateFlow()

    private var endReached = false
    private val accumulatedMovies = mutableListOf<Movie>()

    init {
        loadPage()
    }

    fun loadPage() {
        if (_isPaginating.value || endReached) return

        viewModelScope.launch {
            _isPaginating.value = true
            try {
                val nextPage = currentPage + 1
                val pageResult = repository.getPopularMoviesPage(nextPage)

                currentPage = pageResult.page
                endReached = pageResult.endReached
                accumulatedMovies += pageResult.items

                _moviesListState.update {
                    MoviesListStates.Success(
                        pageResult.copy(items = accumulatedMovies.toList())
                    )
                }
            } catch (e: Exception) {
                if (accumulatedMovies.isEmpty()) {
                    _moviesListState.update {
                        MoviesListStates.Error(e.message ?: "An unexpected error occurred")
                    }
                }
            } finally {
                _isPaginating.value = false
            }
        }
    }
}


    sealed interface MoviesListStates{
    data class Success(val movieSections: PagedResult<Movie>): MoviesListStates
    data object Loading: MoviesListStates
    data class Error(val message: String): MoviesListStates
}