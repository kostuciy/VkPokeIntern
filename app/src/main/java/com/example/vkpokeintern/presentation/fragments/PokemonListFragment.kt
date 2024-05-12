package com.example.vkpokeintern.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.vkpokeintern.R
import com.example.vkpokeintern.databinding.FragmentMainBinding
import com.example.vkpokeintern.databinding.FragmentPokemonListBinding
import com.example.vkpokeintern.model.ModelType
import com.example.vkpokeintern.model.Pokemon
import com.example.vkpokeintern.model.ViewState
import com.example.vkpokeintern.presentation.adapter.OnInteractionListener
import com.example.vkpokeintern.presentation.adapter.PokemonAdapter
import com.example.vkpokeintern.viewmodel.PokemonViewModel
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.launch


class PokemonListFragment : Fragment() {

    private val viewModel: PokemonViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPokemonListBinding.inflate(
            inflater, container, false
        )


        val adapter = PokemonAdapter(object : OnInteractionListener {
            override fun onCardClicked(pokemon: Pokemon) {
//                viewModel.toCurrentProduct(product)
//                findNavController().navigate(R.id.action_listFragment_to_productFragment)
            }
        })

        with(binding) {
                list.apply {
                this.adapter = adapter
                layoutManager = GridLayoutManager(
                    context,
//                    resources.getInteger(R.integer.grid_column_count)
                    2
                )
            }

            next.setOnClickListener {
                viewModel.nextPage(ModelType.POKEMON)
            }
            back.setOnClickListener {
                viewModel.nextPage(ModelType.POKEMON)
            }
            reload.setOnClickListener {
                viewModel.nextPage(ModelType.POKEMON)
            }
        }

        with(viewModel) {
            lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    launch {
                        pokemonList.collect { pokemons ->
                            adapter.submitList(pokemons)
                        }
                    }

                    launch {
                        viewState.collect { view ->
                            when (view.state) {
                                ViewState.ViewStateTypes.LOADING -> {
                                    binding.back.isEnabled = false
                                    binding.next.isEnabled = false
                                    binding.progressBar.isVisible = true
                                    binding.reload.isGone = true
                                }
                                ViewState.ViewStateTypes.ERROR -> {
                                    binding.back.isGone = true
                                    binding.next.isGone = true
                                    binding.reload.isVisible = true
                                }
                                ViewState.ViewStateTypes.LIST -> {
                                    binding.back.isEnabled = true
                                    binding.next.isEnabled = true
                                    binding.back.isVisible = true
                                    binding.next.isVisible = true
                                    binding.progressBar.isGone = true
                                    binding.reload.isGone = true
                                }
                            }
                        }
                    }
                }
            }
        }


        return binding.root
    }

    private fun extracted() {
        TODO()
    }
}