package com.example.listview.di

import com.example.listview.repository.MovieRepository
import org.koin.dsl.module

val repositoryModule= module {
    factory {
        MovieRepository(
            kortClient = get()
        )
    }
}