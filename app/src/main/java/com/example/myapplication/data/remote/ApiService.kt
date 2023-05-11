package com.example.myapplication.data.remote

import com.example.myapplication.data.model.Character
import com.example.myapplication.data.model.Episode
import com.example.myapplication.data.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Rick and Morty API documentation https://rickandmortyapi.com/documentation
 */
interface ApiService {

    @GET("character/")
    suspend fun getCharacters(): Response<ApiResponse<List<Character>>>

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Response<List<Character>>

    @GET("episode/")
    suspend fun getEpisodes(): Response<ApiResponse<List<Episode>>>
}