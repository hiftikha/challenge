package com.example.myapplication.ui.episode.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.ui.episode.details.EpisodeDetailsActivity

class EpisodeGridActivity : AppCompatActivity(R.layout.activity_episode_grid) {

    private val viewModel by lazy { ViewModelProvider(this).get(EpisodeViewModel::class.java) }
    private val episodeAdapter = EpisodeAdapter {
        startActivity(EpisodeDetailsActivity.getIntent(this, it))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getList()
        viewModel.episodesLiveData.observe(this) { episodeAdapter.submitList(it) }
    }

    private fun initRecyclerView() {
        with (findViewById<RecyclerView>(R.id.recyclerView)) {
            layoutManager = GridLayoutManager(this@EpisodeGridActivity, 3, GridLayoutManager.VERTICAL, false)
            adapter = episodeAdapter
        }
    }
}