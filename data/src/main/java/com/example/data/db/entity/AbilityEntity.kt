package com.example.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "abilityentity")
data class AbilityEntity(
    @PrimaryKey val abilityId: Long,
    val abilityName: String,
    val abilityEffects: Map<String, String>, // name (short description) to long description
    val abilityPokemons: Map<String, String> // name to url
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


