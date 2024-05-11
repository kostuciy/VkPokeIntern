package com.example.vkpokeintern.api.dto

data class ImagesResponse(
    val backDefault: String? = null,
    val backFemale: String? = null,
    val backShiny: String? = null,
    val backShinyFemale: String? = null,
    val frontDefault: String? = null,
    val frontFemale: String? = null,
    val frontShiny: String? = null,
    val frontShinyFemale: String? = null,
    val other: OtherImages? = null
) {

    data class OtherImages(
        val dreamWorld: ImagesResponse,
        val home: ImagesResponse,
        val officialArtwork: ImagesResponse,
        val showdown: ImagesResponse
    )
}



