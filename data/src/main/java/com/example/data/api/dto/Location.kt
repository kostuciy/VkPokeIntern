package com.example.data.api.dto

data class EncountersResponse(
    val location_area: UrlObject,
)

data class LocationResponse(
    val id: Long,
    val names: List<NameResponse>,
    val pokemon_encounters: List<PokemonInfoResponse>
)
