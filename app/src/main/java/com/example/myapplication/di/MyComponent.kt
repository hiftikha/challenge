package com.example.myapplication.di

import com.example.myapplication.ui.episode.details.EpisodeDetailsViewModel
import com.example.myapplication.ui.episode.main.EpisodeViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface MyComponent {
    fun inject(episodeViewModel: EpisodeViewModel)
    fun inject(characterListViewModel: EpisodeDetailsViewModel)
}