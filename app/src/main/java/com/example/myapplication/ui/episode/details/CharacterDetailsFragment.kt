package com.example.myapplication.ui.episode.details

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.data.model.Character
import com.example.myapplication.data.model.Episode
import com.squareup.picasso.Picasso

class CharacterDetailsFragment: Fragment(R.layout.fragment_character_details) {
    companion object {
        const val EXTRA = "character_extra"
    }

    private val character: Character? by lazy {
        arguments?.getParcelable(EXTRA)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("CharacterDetailsFragment arguments: ${arguments}")
        initUi(view)
        Log.d("CharacterDetailsFragment Debug", "Character is ${character}")
    }


    private fun initUi(view: View) {
        with(view.findViewById<TextView>(R.id.character_name)) {
            text = character?.name
        }
        with(view.findViewById<TextView>(R.id.character_status)) {
            text = character?.status
        }
        with(view.findViewById<TextView>(R.id.character_species)) {
            text = character?.species
        }
        with(view.findViewById<TextView>(R.id.character_gender)) {
            text = character?.gender
        }
        with(view.findViewById<TextView>(R.id.character_origin)) {
            text = "Origin: ${character?.origin?.name}"
        }
        with(view.findViewById<TextView>(R.id.character_location)) {
            text = "Location: ${character?.location?.name}"
        }
        with(view.findViewById<ImageView>(R.id.character_image)) {
            Picasso.get().load(character?.image).into(this)
        }
    }
}