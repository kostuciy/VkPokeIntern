package com.example.vkpokeintern.usecase

import com.example.vkpokeintern.repository.Repository

class ClearPokemonsUseCase(val repository: Repository) {

    suspend fun execute() = repository.clearPokemons()
}