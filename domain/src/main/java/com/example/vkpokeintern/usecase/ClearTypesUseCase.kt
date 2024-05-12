package com.example.vkpokeintern.usecase

import com.example.vkpokeintern.repository.Repository

class ClearTypesUseCase(private val repository: Repository) {

    suspend fun execute() = repository.clearTypes()
}