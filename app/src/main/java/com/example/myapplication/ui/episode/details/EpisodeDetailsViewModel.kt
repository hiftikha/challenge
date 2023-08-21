package com.example.myapplication.ui.episode.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.Character
import com.example.myapplication.data.model.Episode
import com.example.myapplication.data.repo.Repository
import com.example.myapplication.di.MyApplication
import kotlinx.coroutines.launch
import javax.inject.Inject

class EpisodeDetailsViewModel(application: Application): AndroidViewModel(application) {

    @Inject
    lateinit var repository: Repository

    val progressBarVisibility = MutableLiveData(false)
    val charactersLiveData = MutableLiveData<List<Character>>()
    val selectedCharacterIdLiveData = MutableLiveData<Int>()
    val selectedEpisodeCharactersLiveData = MutableLiveData<List<Character>>()

    init {
        MyApplication.myComponent.inject(this)
    }

    fun getList() {
        viewModelScope.launch {
            progressBarVisibility.value = true
            val response = repository.getCharacters()
            if (response.isSuccessful) {
                charactersLiveData.value = response.body()?.results
            } else {

            }
            progressBarVisibility.value = false
        }
    }

    fun getCharactersByEpisode(episode: Episode) {
        viewModelScope.launch {
            progressBarVisibility.value = true
            val characters = mutableListOf<Character>()
            episode.characters.forEach{ characterUrl ->
                val characterId = characterUrl.split("/").last().toInt()
                val response = repository.getCharacterById(characterId)
                if (response.isSuccessful) {
                    response.body()?.let { characters.add(it) }
                }
            }
            selectedEpisodeCharactersLiveData.value = characters
            progressBarVisibility.value = false
        }
    }

}