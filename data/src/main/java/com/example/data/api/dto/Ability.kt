package com.example.data.api.dto

data class AbilityInfoResponse(
    val ability: UrlObject,
    val is_hidden: Boolean = false,
    val slot: Int
)

data class AbilityResponse(
    val id: Long,
    val effect_entries: List<AbilityEffectResponse>,
    val pokemon: List<PokemonInfoResponse>,
    val names: List<NameResponse>
) {

    data class AbilityEffectResponse(
        val effect: String,
        val language: UrlObject,
        val short_effect: String
    )
}


