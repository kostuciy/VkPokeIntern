package com.example.vkpokeintern.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "locationentity")
data class LocationEntity(
    @PrimaryKey val locationId: Long,
    val locationName: String,
    val locationPokemons: Map<String, String> // name to url
)

//data class LocationDetailed(
//    @Embedded val location: LocationEntity,
//    @Relation(
//        parentColumn = "locationId",
//        entityColumn = "pokemonId"
//    )
//    val pokemons: List<PokemonEntity>
//)

