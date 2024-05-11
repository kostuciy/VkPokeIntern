package com.example.vkpokeintern.usecase

import com.example.vkpokeintern.repository.Repository

class GetPokemonListUseCase(private val repository: Repository) {

    fun execute() = repository.pokemonList
}