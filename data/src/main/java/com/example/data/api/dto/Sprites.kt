package com.example.data.api.dto

import kotlin.reflect.full.memberProperties

data class SpritesResponse(
    val front_default: String? = null,
    val front_female: String? = null,
    val front_shiny: String? = null,
    val front_shiny_female: String? = null,
    val back_default: String? = null,
    val back_female: String? = null,
    val back_shiny: String? = null,
    val back_shiny_female: String? = null,
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
        val dream_world: SpritesResponse,
        val home: SpritesResponse,
        val official_artwork: SpritesResponse,
        val showdown: SpritesResponse
    )
}



