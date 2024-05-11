package com.example.vkpokeintern.api.dto

data class EncountersListResponse(
    val locationArea: List<UrlObject>,
)

data class LocationResponse(
    val id: Long,
    val names: List<NameResponse>,
    val pokemonEncounters: List<PokemonInfoResponse>
)
