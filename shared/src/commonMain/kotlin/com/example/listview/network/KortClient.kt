package com.example.listview.network

import com.example.listview.model.MovieListResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


private const val BASEURL="https://api.themoviedb.org"
const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p"
class KortClient {
    private val kortClient = HttpClient{
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }
        install(Auth){
            bearer {
                loadTokens {
                    BearerTokens(
                        accessToken = Secrets.TMDB_ACCESS_TOKEN,
                        refreshToken = "",
                    )
                }
            }
        }
    }

    suspend fun  getMoviesPopular(): MovieListResponse {
        return kortClient.get("${BASEURL}/3/movie/popular").body()

    }
}