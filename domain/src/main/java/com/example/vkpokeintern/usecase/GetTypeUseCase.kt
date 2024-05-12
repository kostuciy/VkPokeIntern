package com.example.vkpokeintern.usecase

import com.example.vkpokeintern.repository.Repository

class GetTypeUseCase(private val repository: Repository) {

    suspend fun execute(url: String) = repository.getType(url)
}