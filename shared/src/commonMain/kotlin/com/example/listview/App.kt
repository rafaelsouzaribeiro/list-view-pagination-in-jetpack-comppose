package com.example.listview

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.listview.di.kortClientModule
import com.example.listview.di.repositoryModule
import com.example.listview.di.viewModelModule
import com.example.listview.ui.movies.MoviesListScreen
import org.koin.compose.KoinApplication
import org.koin.dsl.koinConfiguration

@Composable
@Preview
fun App() {
    KoinApplication(configuration = koinConfiguration {
        modules(kortClientModule, repositoryModule, viewModelModule)
    }) {
        MaterialTheme {
            MoviesListScreen()
        }
    }
}