package com.example.vkpokeintern.db.entity

import androidx.room.PrimaryKey

data class TypeEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val pokemons: Map<String, String> // name to url
)