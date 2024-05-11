package com.example.vkpokeintern.usecase

import com.example.vkpokeintern.repository.Repository

class GetTypesListStateUseCase(private val repository: Repository) {

    suspend fun execute(url: String?) = repository.getTypesInfo(url)
}