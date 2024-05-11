package com.example.vkpokeintern.model

data class Ability(
    val id: Long,
    val name: String,
    val effects: Map<String, String>, // name (short description) to long description
    val pokemons: Map<String, String> // name to url
) : Model