package com.example.vkpokeintern.api.dto

data class AbilityInfoResponse(
    val ability: UrlObject,
    val isHidden: Boolean = false,
    val slot: Int
)

data class AbilityResponse(
    val effectEntries: List<AbilityEffectResponse>,
    val pokemon: List<PokemonInfoResponse>
) {

    data class AbilityEffectResponse(
        val effect: String,
        val language: UrlObject,
        val shortEffect: String
    )
}


