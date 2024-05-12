package com.example.vkpokeintern.usecase

import com.example.vkpokeintern.repository.Repository

class ClearAbilitiesUseCase(private val repository: Repository) {

    suspend fun execute() = repository.clearAbilities()
}