package com.example.vkpokeintern.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import com.example.vkpokeintern.R
import com.example.vkpokeintern.databinding.FragmentListBinding
import com.example.vkpokeintern.model.ModelType
import com.example.vkpokeintern.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {

    private val viewModel: PokemonViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentListBinding.inflate(layoutInflater)

//        viewModel.changePage(ModelType.POKEMON)

        return binding.root
    }
}