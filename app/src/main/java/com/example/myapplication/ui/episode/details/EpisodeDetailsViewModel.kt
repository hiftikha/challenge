package com.example.myapplication.ui.episode.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.Character
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

}