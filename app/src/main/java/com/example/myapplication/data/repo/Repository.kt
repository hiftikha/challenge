package com.example.myapplication.data.repo

import com.example.myapplication.data.model.ApiResponse
import com.example.myapplication.data.model.Character
import com.example.myapplication.data.model.Episode
import com.example.myapplication.data.remote.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class Repository(private val apiService: ApiService) {

    suspend fun getCharacters(): Response<ApiResponse<List<Character>>> {
        return withContext(Dispatchers.IO) {
            apiService.getCharacters()
        }
    }

    suspend fun getEpisodes(): Response<ApiResponse<List<Episode>>> {
        return withContext(Dispatchers.IO) {
            apiService.getEpisodes()
        }
    }
}