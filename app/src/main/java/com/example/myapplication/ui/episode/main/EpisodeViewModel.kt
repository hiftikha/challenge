package com.example.myapplication.ui.episode.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.Episode
import com.example.myapplication.data.repo.Repository
import com.example.myapplication.di.MyApplication
import kotlinx.coroutines.launch
import javax.inject.Inject

class EpisodeViewModel(appContext: Application): AndroidViewModel(appContext) {

    @Inject
    lateinit var repository: Repository

    var episodesLiveData = MutableLiveData<List<Episode>>()

    init {
        MyApplication.myComponent.inject(this)
    }

    fun getList() {
        viewModelScope.launch {
            val response = repository.getEpisodes()
            if (response.isSuccessful) {
                episodesLiveData.value = response.body()?.results ?: emptyList()
            } else {

            }
        }
    }
}