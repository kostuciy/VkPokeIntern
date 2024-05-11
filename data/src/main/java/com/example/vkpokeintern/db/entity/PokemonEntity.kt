package com.example.vkpokeintern.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.vkpokeintern.api.dto.ImagesResponse
import com.example.vkpokeintern.api.dto.Stat
import com.example.vkpokeintern.api.dto.TypeResponse
import com.example.vkpokeintern.api.dto.UrlObject

@Entity
data class PokemonEntity(
    @PrimaryKey val pokemonId: Long,
    val baseExperience: Int,
    val forms: List<String>,
    val height: Int,
    val name: String,
    val stats: List<String>,
    val types: List<TypeResponse>,
    val weight: Int,
    val locationsUrl: String, // TODO: check if needed
    val avatarUrl: String
)

data class PokemonEntityFull(
    @Embedded val pokemon: PokemonEntity,
    @Relation(
        parentColumn = "pokemonId",
        entityColumn = "abilityId",
        associateBy = Junction(PokemonAbilityCrossRef::class)
    )
    val abilities: List<AbilityEntity>,
    @Relation(
        parentColumn = "pokemonId",
        entityColumn = "locationId",
        associateBy = Junction(PokemonLocationCrossRef::class)
    )
    val locations: List<LocationEntity>,
    @Relation(
        parentColumn = "pokemonId",
        entityColumn = "typeId",
        associateBy = Junction(PokemonLocationCrossRef::class)
    )
    val type: List<TypeEntity>,
    @Relation(
        parentColumn = "userId",
        entityColumn = "spriteId"
    )
    val spriteEntity: List<SpriteEntity>
//    TODO: add type and images
)

@Entity(primaryKeys = ["pokemonId", "abilityId"])
data class PokemonAbilityCrossRef(
    val pokemonId: Long,
    val abilityId: Long
)

@Entity(primaryKeys = ["pokemonId", "locationId"])
data class PokemonLocationCrossRef(
    val pokemonId: Long,
    val locationId: Long
)

@Entity(primaryKeys = ["pokemonId", "typeId"])
data class PokemonTypeCrossRef(
    val pokemonId: Long,
    val typeId: Long
)

// Pokemon - general
// Abilities - general
// Types - general
// Locations - general
// Sprites - general

// PokemonFull (3 crossrefs + 1 to many relationship) -> using to get full info about pokemons
// AbilityWithPokemon -> using detailed ability to show pokemon list (if clicks pokemon then gets it id
//                       and gets PokemonFull
// same for the rest (except sprites)