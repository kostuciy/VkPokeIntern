package com.example.vkpokeintern.usecase

import com.example.vkpokeintern.repository.Repository

class GetLocationListStateUseCase(private val repository: Repository) {

    suspend fun execute(url: String?) = repository.getLocationsInfo(url)
}