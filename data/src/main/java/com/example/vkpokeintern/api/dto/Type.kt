package com.example.vkpokeintern.api.dto

data class TypeInfoResponse(
    val type: UrlObject
)

data class TypeResponse(
    val id: Long,
    val names: List<NameResponse>,
    val pokemon: List<PokemonInfoResponse>
)