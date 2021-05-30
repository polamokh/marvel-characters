package me.polamokh.marvelcharacters.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import me.polamokh.marvelcharacters.databinding.ItemCharacterBinding
import me.polamokh.marvelcharacters.databinding.ItemSearchCharacterBinding
import me.polamokh.marvelcharacters.model.MarvelCharacter

class CharactersAdapter(
    private val itemLayoutId: Int,
    private val block: (marvelCharacter: MarvelCharacter) -> Unit
) :
    PagingDataAdapter<MarvelCharacter, CharactersAdapter.CharactersViewHolder>(
        characterDiffUtil
    ) {

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
            holder.itemView.setOnClickListener { block(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        return CharactersViewHolder.from(itemLayoutId, parent)
    }

    class CharactersViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MarvelCharacter) {
            if (binding is ItemCharacterBinding)
                binding.character = item
            else if (binding is ItemSearchCharacterBinding)
                binding.character = item

            binding.executePendingBindings()
        }

        companion object {
            fun from(itemLayoutId: Int, parent: ViewGroup): CharactersViewHolder {
                val binding: ViewDataBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    itemLayoutId,
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