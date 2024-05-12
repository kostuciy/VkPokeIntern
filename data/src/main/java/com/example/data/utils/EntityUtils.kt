package com.example.data.utils

import com.example.data.api.dto.AbilityResponse
import com.example.data.api.dto.EncountersResponse
import com.example.data.api.dto.LocationResponse
import com.example.data.api.dto.NameResponse
import com.example.data.api.dto.PokemonResponse
import com.example.data.api.dto.SpritesResponse
import com.example.data.api.dto.TypeResponse
import com.example.data.api.dto.UrlObject
import com.example.data.api.dto.UrlObjectList
import com.example.data.db.entity.AbilityEntity
import com.example.data.db.entity.LocationEntity
import com.example.data.db.entity.PokemonEntity
import com.example.data.db.entity.TypeEntity
import com.example.vkpokeintern.model.Ability
import com.example.vkpokeintern.model.ListState
import com.example.vkpokeintern.model.Location
import com.example.vkpokeintern.model.Pokemon
import com.example.vkpokeintern.model.Type
import com.example.vkpokeintern.model.UrlHolder

object EntityUtils {

    fun toUrlHolder(urlObject: UrlObject): UrlHolder =
        UrlHolder(urlObject.name, urlObject.url)

    fun toListState(
        urlObjectList: UrlObjectList,
        lastUnfiltered: String? = null
    ): ListState =
        ListState(
            size = urlObjectList.count,
            previous = urlObjectList.previous,
            next = urlObjectList.next,
            list = urlObjectList.results.map { toUrlHolder(it) },
            lastUnfilteredState = lastUnfiltered
        )

    fun toPokemonModel(pokemonEntity: PokemonEntity): Pokemon =
        pokemonEntity.let {
            Pokemon(
                it.pokemonId, it.abilities, it.baseExperience, it.forms,
                it.height, it.pokemonLocations, it.moves, it.pokemonName, it.sprites,
                it.stats, it.types, it.weight
                )
        }

    fun toAbilityModel(abilityEntity: AbilityEntity): Ability =
        abilityEntity.let {
            Ability(
                it.abilityId, it.abilityName,
                it.abilityEffects, it.abilityPokemons
            )
        }

    fun toTypeModel(typeEntity: TypeEntity): Type =
        typeEntity.let {
            Type(it.typeId, it.typeName, it.pokemonsType)
        }

    fun toLocationModel(locationEntity: LocationEntity): Location =
        locationEntity.let {
            Location(it.locationId, it.locationName, it.locationPokemons)
        }

    fun toPokemonEntity(
        pokemonResponse: PokemonResponse,
//        encountersResponseList: List<EncountersResponse>,
    ): PokemonEntity =
        pokemonResponse.let {
            PokemonEntity(
                pokemonId = it.id,
                abilities = it.abilities?.associate { abilityResponse ->
                    abilityResponse.ability.name to abilityResponse.ability.url
                } ?: emptyMap(),
                baseExperience = it.base_experience,
                forms = it.forms?.map { form -> form.name } ?: emptyList(),
                height = it.height,
//                pokemonLocations = encountersResponseList.associate {
//                    encounter -> encounter.location_area.name to encounter.location_area.url
//                },
                pokemonLocations = it.location_area_encounters!!,
                moves = it.moves?.map { move -> move.move.name } ?: emptyList(),
                pokemonName = it.name,
                sprites = it.sprites?.let { spritesToList(it) } ?: emptyList(),
                stats = it.stats?.associate { stat -> stat.stat.name to stat.base_stat } ?: emptyMap(),
                types = it.types?.associate { type -> type.type.name to type.type.url } ?: emptyMap(),
                weight = it.weight
            )
        }

    fun toLocationEntity(
        locationResponse: LocationResponse
    ): LocationEntity =
        locationResponse.let {
            LocationEntity(
                locationId = it.id,
                locationName = getEnglishName(it.names)?.name ?: "error eng", // TODO: fix
                locationPokemons = it.pokemon_encounters.associate { pokemon ->
                    pokemon.pokemon.name to pokemon.pokemon.url
                }
            )
        }

    fun toAbilityEntity(
        abilityResponse: AbilityResponse
    ): AbilityEntity =
        abilityResponse.let {
            AbilityEntity(
                abilityId = it.id,
                abilityName = getEnglishName(it.names)?.name ?: "error eng", // TODO: fix
                abilityEffects = it.effect_entries
                    .filter { effect -> effect.language.name == "en" }
                    .associate { effect -> effect.short_effect to effect.effect },
                abilityPokemons = it.pokemon.associate { pokemon ->
                    pokemon.pokemon.name to pokemon.pokemon.url
                }
            )
        }

    fun toTypeEntity(
        typeResponse: TypeResponse
    ): TypeEntity =
        typeResponse.let {
            TypeEntity(
                typeId = it.id,
                typeName = getEnglishName(it.names)?.name ?: "error eng", // TODO: fix
                pokemonsType = it.pokemon.associate { pokemon ->
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
        spritesResponse.getList()
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