package com.example.listview.di

import com.example.listview.network.KortClient
import org.koin.dsl.module

val kortClientModule= module {
    single{
        KortClient()
    }
}