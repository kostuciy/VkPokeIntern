package com.example.vkpokeintern.usecase

import com.example.vkpokeintern.repository.Repository

class GetLocationUseCase(private val repository: Repository) {

    suspend fun execute(url: String) = repository.getLocation(url)
}