package com.example.vkpokeintern.model

data class Type(
    val id: Long,
    val name: String,
    val pokemons: Map<String, String> // name to url
) : Model
