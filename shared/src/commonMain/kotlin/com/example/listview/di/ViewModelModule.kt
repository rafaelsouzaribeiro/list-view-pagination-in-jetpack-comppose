package com.example.listview.di

import com.example.listview.ui.movies.MovieListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule= module {
    viewModel {
        MovieListViewModel(
            repository = get()
        )
    }
}