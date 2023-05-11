package com.example.myapplication.ui.episode.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.model.Character
import com.squareup.picasso.Picasso

class CharacterAdapter(private val selectedIndexLiveData: MutableLiveData<Int>)
    : ListAdapter<Character, CharacterViewHolder>(CharacterItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.character_item, parent, false))
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(CharacterModelView(character.name, character.image))
        holder.itemView.setOnClickListener { selectedIndexLiveData.value = position }
    }
}

data class CharacterModelView(val name: String, val url: String?)

class CharacterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(modelView: CharacterModelView) {
        itemView.findViewById<TextView>(R.id.title).text = modelView.name
        Picasso.get().load(modelView.url).into(itemView.findViewById<ImageView>(R.id.thumbnail))
    }
}

class CharacterItemDiffCallback: DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.name == newItem.name
    }
}