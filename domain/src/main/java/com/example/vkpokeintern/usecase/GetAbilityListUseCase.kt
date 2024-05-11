package com.example.vkpokeintern.usecase

import com.example.vkpokeintern.repository.Repository

class GetAbilityListUseCase(private val repository: Repository) {

    fun execute() = repository.abilityList
}