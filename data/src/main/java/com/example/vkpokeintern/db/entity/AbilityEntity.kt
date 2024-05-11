package com.example.vkpokeintern.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class AbilityEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val effects: Map<String, String>, // name (short description) to long description
    val pokemons: Map<String, String> // name to url
)

//data class AbilityDetailedEntity(
//    @Embedded val ability: AbilityEntity,
//    @Relation(
//        entityColumn = "pokemonId",
//        parentColumn = "abilityId",
//        associateBy = Junction(PokemonAbilityCrossRef::class)
//    )
//    val pokemons: List<PokemonEntity>
//)


