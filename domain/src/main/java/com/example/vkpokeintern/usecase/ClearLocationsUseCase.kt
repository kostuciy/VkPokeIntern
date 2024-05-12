package com.example.vkpokeintern.usecase

import com.example.vkpokeintern.repository.Repository

class ClearLocationsUseCase(private val repository: Repository) {

    suspend fun execute() = repository.clearLocations()
}