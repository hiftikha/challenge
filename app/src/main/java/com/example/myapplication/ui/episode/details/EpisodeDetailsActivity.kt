package com.example.myapplication.ui.episode.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.data.model.Episode

class EpisodeDetailsActivity: AppCompatActivity(R.layout.activity_episode_details) {

    companion object {
        const val EXTRA = "episode_extra"
        @JvmStatic fun getIntent(context: Context, episode: Episode): Intent {
            return Intent(context, EpisodeDetailsActivity::class.java).apply {
                putExtra(EXTRA, episode)
            }
        }
    }
}