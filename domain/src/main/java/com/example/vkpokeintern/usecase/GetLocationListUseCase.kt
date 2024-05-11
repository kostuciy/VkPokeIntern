package com.example.vkpokeintern.usecase

import com.example.vkpokeintern.repository.Repository

class GetLocationListUseCase(private val repository: Repository) {

    fun execute() = repository.locationList
}