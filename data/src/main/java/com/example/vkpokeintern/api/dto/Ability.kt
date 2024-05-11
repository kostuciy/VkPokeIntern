package com.example.vkpokeintern.api.dto

data class AbilityInfoResponse(
    val ability: UrlObject,
    val isHidden: Boolean = false,
    val slot: Int
)

data class AbilityResponse(
    val id: Long,
    val effectEntries: List<AbilityEffectResponse>,
    val pokemon: List<PokemonInfoResponse>,
    val names: List<NameResponse>
) {

    data class AbilityEffectResponse(
        val effect: String,
        val language: UrlObject,
        val shortEffect: String
    )
}


