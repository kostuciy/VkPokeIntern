package com.example.vkpokeintern.usecase

import com.example.vkpokeintern.repository.Repository

class GetAbilityUseCase(private val repository: Repository) {

    suspend fun execute(url: String) = repository.getAbility(url)
}