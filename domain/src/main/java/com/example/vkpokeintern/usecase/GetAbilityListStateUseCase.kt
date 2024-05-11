package com.example.vkpokeintern.usecase

import com.example.vkpokeintern.repository.Repository

class GetAbilityListStateUseCase(private val repository: Repository) {

    suspend fun execute(url: String?) = repository.getAbilitiesInfo(url)
}