package com.example.vkpokeintern.usecase

import com.example.vkpokeintern.repository.Repository

class GetTypesListUseCase(private val repository: Repository) {

    fun execute() = repository.typeList
}