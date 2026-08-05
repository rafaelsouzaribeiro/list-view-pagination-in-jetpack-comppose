package com.example.listview

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform