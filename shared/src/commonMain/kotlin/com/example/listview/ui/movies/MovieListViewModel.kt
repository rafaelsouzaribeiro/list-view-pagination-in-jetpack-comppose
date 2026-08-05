package com.example.listview.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.listview.model.MovieListResponse
import com.example.listview.model.MovieResponse
import com.example.listview.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieListViewModel (
    private val repository: MovieRepository
): ViewModel() {
    init {
        getMoviesPopular()
    }
    private val _moviesListState = MutableStateFlow<MoviesListStates>(MoviesListStates.Loading)
    val movieListStates = _moviesListState.asStateFlow()

    private fun getMoviesPopular() {
        viewModelScope.launch {
                try {
                    val movieList = repository.getMoviesPopular()
                    _moviesListState.update {
                        MoviesListStates.Success(movieList)
                    }
                } catch (e: Exception) {
                    _moviesListState.update {
                        MoviesListStates.Error(e.message ?: "An unexpected error occurred")
                    }
                }
            }
        }

    }

    sealed interface MoviesListStates{
        data class Success(val movieSections: MovieListResponse): MoviesListStates
        data object Loading: MoviesListStates
        data class Error(val message: String): MoviesListStates
    }
