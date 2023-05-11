package com.example.myapplication.ui.episode.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.model.Episode

class EpisodeAdapter(private val onSelected: (episode: Episode) -> Unit)
    : ListAdapter<Episode, EpisodeViewHolder>(EpisodeItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        return EpisodeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.episode_item, parent, false))
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        val episode = getItem(position)
        holder.bind(EpisodeModelView(episode.episode, episode.name))
        holder.itemView.setOnClickListener { onSelected.invoke(episode) }
    }
}

data class EpisodeModelView(val primaryText: String, val secondaryText: String)

class EpisodeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(modelView: EpisodeModelView) {
        itemView.findViewById<TextView>(R.id.primaryText).text = modelView.primaryText
        itemView.findViewById<TextView>(R.id.secondaryText).text = modelView.secondaryText
    }
}

class EpisodeItemDiffCallback: DiffUtil.ItemCallback<Episode>() {
    override fun areItemsTheSame(oldItem: Episode, newItem: Episode): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Episode, newItem: Episode): Boolean {
        return oldItem.name.plus(oldItem.episode) == newItem.name.plus(newItem.episode)
    }

}
