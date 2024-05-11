package com.example.vkpokeintern.utils

import com.example.vkpokeintern.api.dto.AbilityResponse
import com.example.vkpokeintern.api.dto.EncountersListResponse
import com.example.vkpokeintern.api.dto.LocationResponse
import com.example.vkpokeintern.api.dto.NameResponse
import com.example.vkpokeintern.api.dto.PokemonResponse
import com.example.vkpokeintern.api.dto.SpritesResponse
import com.example.vkpokeintern.api.dto.TypeResponse
import com.example.vkpokeintern.db.entity.AbilityEntity
import com.example.vkpokeintern.db.entity.LocationEntity
import com.example.vkpokeintern.db.entity.PokemonEntity
import com.example.vkpokeintern.db.entity.TypeEntity

object EntityUtils {

    fun toPokemonEntity(
        pokemonResponse: PokemonResponse,
        encountersListResponse: EncountersListResponse,
        locationsList: List<LocationResponse>
    ): PokemonEntity =
        pokemonResponse.let {
            val locationMap = locationsList.mapIndexed { index, location ->
                toLocationEntity(location).name to encountersListResponse.locationArea[index].url
            }.toMap()
            PokemonEntity(
                id = it.id,
                abilities = it.abilities.associate { abilityResponse ->
                    abilityResponse.ability.name to abilityResponse.ability.url
                },
                baseExperience = it.baseExperience,
                forms = it.forms.map { form -> form.name },
                height = it.height,
                locations = locationMap,
                moves = it.moves.map { move -> move.move.name },
                name = it.name,
                sprites = spritesToList(it.sprites),
                stats = it.stats.associate { stat -> stat.stat.name to stat.baseStat },
                types = it.types.associate { type -> type.type.name to type.type.url },
                weight = it.weight
            )
        }

    fun toLocationEntity(
        locationResponse: LocationResponse
    ): LocationEntity =
        locationResponse.let {
            LocationEntity(
                id = it.id,
                name = getEnglishName(it.names)?.name ?: "error eng", // TODO: fix
                pokemons = it.pokemonEncounters.associate { pokemon ->
                    pokemon.pokemon.name to pokemon.pokemon.url
                }
            )
        }

    fun toAbilityEntity(
        abilityResponse: AbilityResponse
    ): AbilityEntity =
        abilityResponse.let {
            AbilityEntity(
                id = it.id,
                name = getEnglishName(it.names)?.name ?: "error eng", // TODO: fix
                effects = it.effectEntries
                    .filter { effect -> effect.language.name == "en" }
                    .associate { effect -> effect.shortEffect to effect.effect },
                pokemons = it.pokemon.associate { pokemon ->
                    pokemon.pokemon.name to pokemon.pokemon.url
                }
            )
        }

    fun toTypeEntity(
        typeResponse: TypeResponse
    ): TypeEntity =
        typeResponse.let {
            TypeEntity(
                id = it.id,
                name = getEnglishName(it.names)?.name ?: "error eng", // TODO: fix
                pokemons = it.pokemon.associate { pokemon ->
                    pokemon.pokemon.name to pokemon.pokemon.url
                }
            )
        }

    private fun getEnglishName(
        names: List<NameResponse>
    ): NameResponse? = names.firstOrNull { it.language.name == "en" }

    private fun spritesToList(
        spritesResponse: SpritesResponse
    ): List<String> =
        spritesResponse.getList() +
                (spritesResponse.other?.home?.getList() ?: emptyList()) +
                (spritesResponse.other?.showdown?.getList() ?: emptyList()) +
                (spritesResponse.other?.dreamWorld?.getList() ?: emptyList()) +
                (spritesResponse.other?.officialArtwork?.getList() ?: emptyList())
}

//val forms: List<String>,
//val height: Int,
//val locations: Map<String, String>, // name to url
//val moves: List<String>,
//val name: String,
//val sprites: List<String>, // url, first non-null - avatar
//val stats: Map<String, Int>, // stat name to value
//val types: Map<String, String>, // name to url
//val weight: Int