package me.polamokh.marvelcharacters.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import me.polamokh.marvelcharacters.databinding.ItemCharacterBinding
import me.polamokh.marvelcharacters.model.MarvelCharacter

class CharactersAdapter :
    PagingDataAdapter<MarvelCharacter, CharactersAdapter.CharactersViewHolder>(
        characterDiffUtil
    ) {

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        return CharactersViewHolder.from(parent)
    }

    class CharactersViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MarvelCharacter) {
            binding.character = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): CharactersViewHolder {
                val binding = ItemCharacterBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return CharactersViewHolder(binding)
            }
        }
    }

    companion object {
        private val characterDiffUtil = object : DiffUtil.ItemCallback<MarvelCharacter>() {
            override fun areItemsTheSame(
                oldItem: MarvelCharacter,
                newItem: MarvelCharacter
            ) = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: MarvelCharacter,
                newItem: MarvelCharacter
            ) = oldItem == newItem
        }
    }
}