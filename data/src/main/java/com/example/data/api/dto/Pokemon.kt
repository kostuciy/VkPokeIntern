package com.example.data.api.dto

data class PokemonInfoResponse(
    val pokemon: UrlObject
)

data class PokemonResponse(
    val id: Long,
    val abilities: List<AbilityInfoResponse>?,
    val base_experience: Int,
    val forms: List<UrlObject>?,
    val height: Int,
    val location_area_encounters: String?, // URL to area list, response in LocationAreasListResponse
    val moves: List<MoveInfoResponse>?,
    val name: String,
    val sprites: SpritesResponse?,
    val stats: List<Stat>?,
    val types: List<TypeInfoResponse>?,
    val weight: Int
)

//"abilities": [],
//"base_experience": 112,
//"cries": {},
//"forms": [],
//"game_indices": [],
//"height": 4,
//"held_items": [],
//"id": 25,
//"is_default": true,
//"location_area_encounters": "https://pokeapi.co/api/v2/pokemon/25/encounters",
//"moves": [],
//"name": "pikachu",
//"order": 35,
//"past_abilities": [],
//"past_types": [],
//"species": {},
//"sprites": {},
//"stats": [],
//"types": [],
//"weight": 60