package com.example.vkpokeintern.usecase

import com.example.vkpokeintern.repository.Repository

class GetPokemonUseCase(private val repository: Repository) {

    suspend fun execute(url: String) = repository.getPokemon(url)
}