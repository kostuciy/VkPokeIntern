package com.example.vkpokeintern.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.vkpokeintern.databinding.CardPokemonBinding
import com.example.vkpokeintern.model.Pokemon
import com.example.vkpokeintern.utils.ViewExtensions.loadFromUrl


interface OnInteractionListener {
    fun onCardClicked(pokemon: Pokemon)
}

class PokemonAdapter(
    private val onInteractionListener: OnInteractionListener
) : ListAdapter<Pokemon, PokemonAdapter.PokemonViewHolder>(
    PokemonCallback()
) {

    class PokemonViewHolder(
        private val binding: CardPokemonBinding,
        private val onInteractionListener: OnInteractionListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(pokemon: Pokemon) {
            with(binding) {
                name.text = pokemon.name
                Log.d("repository_tag", pokemon.sprites.last())
                avatar.loadFromUrl(pokemon.sprites.last())
                root.setOnClickListener {
                    onInteractionListener.onCardClicked(pokemon)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding = CardPokemonBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PokemonViewHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = currentList[position]
        holder.bind(pokemon)
    }
}

class PokemonCallback : DiffUtil.ItemCallback<Pokemon>() {

    override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem == newItem
    }
}