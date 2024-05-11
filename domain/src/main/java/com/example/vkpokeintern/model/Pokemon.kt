package com.example.vkpokeintern.model

data class Pokemon(
    val id: Long,
    val abilities: Map<String, String>, // name to url
    val baseExperience: Int,
    val forms: List<String>,
    val height: Int,
    val locations: Map<String, String>, // name to url
    val moves: List<String>,
    val name: String,
    val sprites: List<String>, // url, first non-null - avatar
    val stats: Map<String, Int>, // stat name to value
    val types: Map<String, String>, // name to url
    val weight: Int
) : Model
