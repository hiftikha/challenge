package com.example.myapplication.ui.episode.details

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.model.Episode

class EpisodeDetailsFragment: Fragment(R.layout.fragment_episode_details) {

    private val viewModel by lazy { ViewModelProvider(this).get(EpisodeDetailsViewModel::class.java) }

    lateinit var characterAdapter: CharacterAdapter
    lateinit var progressBar: ProgressBar

    private val episode: Episode? by lazy {
        arguments?.get(EpisodeDetailsActivity.EXTRA) as Episode
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi(view)
    }

    override fun onResume() {
        super.onResume()
        with (viewModel) {
            getList()
            charactersLiveData.observe(this@EpisodeDetailsFragment) { characterAdapter.submitList(it) }
            selectedCharacterIdLiveData.observe(this@EpisodeDetailsFragment) {
                CharacterDialogFragment(it).show(childFragmentManager, CharacterDialogFragment.TAG)
            }
            progressBarVisibility.observe(this@EpisodeDetailsFragment) {
                progressBar.visibility = if (it) View.VISIBLE else View.GONE
            }
        }
    }

    private fun initUi(view: View) {
        with (view.findViewById<RecyclerView>(R.id.recyclerView)) {
            layoutManager = GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false)
            characterAdapter = CharacterAdapter(viewModel.selectedCharacterIdLiveData)
            adapter = characterAdapter
        }
        with (view.findViewById<ProgressBar>(R.id.indeterminateBar)) {
            progressBar = this
        }
    }
}

class CharacterDialogFragment(private val characterId: Int): DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setMessage("Character id is $characterId")
            .setPositiveButton("Exciting") { _,_ -> }
            .create()

    companion object {
        const val TAG = "CharacterDialogFragment"
    }
}