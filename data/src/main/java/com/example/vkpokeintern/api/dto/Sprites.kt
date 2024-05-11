package com.example.vkpokeintern.api.dto

import kotlin.reflect.full.memberProperties

data class SpritesResponse(
    val backDefault: String? = null,
    val backFemale: String? = null,
    val backShiny: String? = null,
    val backShinyFemale: String? = null,
    val frontDefault: String? = null,
    val frontFemale: String? = null,
    val frontShiny: String? = null,
    val frontShinyFemale: String? = null,
    val other: OtherSprites? = null
) {
    fun getList(): List<String> {
        val list = mutableListOf<String>()
        for (property in SpritesResponse::class.memberProperties) {
            if (property.name != "other" && property.get(this) != null)
                list += (property.get(this) as String)
        }

        return list
    }

    data class OtherSprites(
        val dreamWorld: SpritesResponse,
        val home: SpritesResponse,
        val officialArtwork: SpritesResponse,
        val showdown: SpritesResponse
    )
}



