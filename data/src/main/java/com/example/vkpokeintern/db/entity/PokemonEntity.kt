package com.example.vkpokeintern.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.vkpokeintern.api.dto.AbilityInfoResponse
import com.example.vkpokeintern.api.dto.MoveInfoResponse
import com.example.vkpokeintern.api.dto.SpritesResponse
import com.example.vkpokeintern.api.dto.Stat
import com.example.vkpokeintern.api.dto.TypeResponse
import com.example.vkpokeintern.api.dto.UrlObject

@Entity
data class PokemonEntity(
    @PrimaryKey val id: Long,
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
)

