package com.example.myapplication.ui.episode.details

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.model.Character
import com.example.myapplication.data.model.Episode
import com.example.myapplication.ui.episode.details.EpisodeDetailsActivity.Companion.EXTRA
import org.w3c.dom.Text

class EpisodeDetailsFragment: Fragment(R.layout.fragment_episode_details) {

    private val viewModel by lazy { ViewModelProvider(this).get(EpisodeDetailsViewModel::class.java) }

    lateinit var characterAdapter: CharacterAdapter
    lateinit var progressBar: ProgressBar

    private val episode: Episode? by lazy {
        activity?.intent?.getParcelableExtra<Episode>(EXTRA)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi(view)
    }


    override fun onResume() {
        super.onResume()
        with (viewModel) {
            getList()
            episode?.let {
                getCharactersByEpisode(it)
            }
            selectedEpisodeCharactersLiveData.observe(this@EpisodeDetailsFragment) { characterAdapter.submitList(it) }
            selectedCharacterIdLiveData.observe(this@EpisodeDetailsFragment) { index ->
                val character = selectedEpisodeCharactersLiveData.value?.get(index)
                character?.let {
                    goToCharacterDetails(it)
                }
            }
            progressBarVisibility.observe(this@EpisodeDetailsFragment) {
                progressBar.visibility = if (it) View.VISIBLE else View.GONE
            }
        }
    }

    private fun goToCharacterDetails(character: Character) {
        val bundle = Bundle()
        bundle.putParcelable(CharacterDetailsFragment.EXTRA, character)

        val characterDetailsFragment = CharacterDetailsFragment()
        characterDetailsFragment.arguments = bundle

        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container_view, characterDetailsFragment)
        transaction.addToBackStack("characterDetails")
        transaction.commit()
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
        with(view.findViewById<TextView>(R.id.episodeName)) {
            text = episode?.name ?: ""
        }
        with(view.findViewById<TextView>(R.id.episodeAirDate)) {
            text = episode?.airDate ?: ""
        }
        with(view.findViewById<TextView>(R.id.episode)) {
            text = episode?.episode ?: ""
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