package com.example.myapplication

import android.icu.text.IDNA
import com.example.myapplication.data.model.ApiResponse
import com.example.myapplication.data.remote.ApiService
import com.example.myapplication.data.repo.Repository
import com.example.myapplication.data.model.Character
import com.example.myapplication.data.model.Info
import com.example.myapplication.data.model.NameUrlObj
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import retrofit2.Response

class RepositoryTest {

    private lateinit var apiService: ApiService
    private lateinit var repository: Repository

    @Before
    fun setup() {
        apiService = Mockito.mock(ApiService::class.java)
        repository = Repository(apiService)
    }

    @Test
    fun testGetCharacters(): Unit = runBlocking {
        val info = Info(
            count = 51,
            pages = 3,
            next = "https://rickandmortyapi.com/api/episode/?page=2",
            prev = null
        )
        val charactersResponse = ApiResponse(info, emptyList<Character>())
        val response: Response<ApiResponse<List<Character>>> = Response.success(charactersResponse)
        `when`(apiService.getCharacters()).thenReturn(response)

        val result = repository.getCharacters()

        assertEquals(response, result)
        Mockito.verify(apiService).getCharacters()
    }

    @Test
    fun testGetCharacterById(): Unit = runBlocking {
        val characterId = 1
        val location = NameUrlObj(name = "Location Name", url = "https://location.url")
        val origin = NameUrlObj(name = "Origin Name", url = "https://origin.url")
        val character = Character(
            id = characterId,
            name = "Character Name",
            status = "Alive",
            species = "Human",
            gender = "Male",
            type = "Type",
            location = location,
            origin = origin,
            image = "https://image.url",
            episode = listOf("Episode1", "Episode2"),
            url = "https://character.url",
            created = "2021-01-01"
        )
        val response: Response<Character> = Response.success(character)
        `when`(apiService.getCharacterById(characterId)).thenReturn(response)

        val result = repository.getCharacterById(characterId)

        assertEquals(response, result)
        Mockito.verify(apiService).getCharacterById(characterId)
    }
}
