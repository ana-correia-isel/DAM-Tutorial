package com.isel.dam.tutorial3.dam_pokedex_part1.data.network.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonDetailResponse(
    @field:Json(name = "id") val id: Int?,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "weight") val weight: Float?,
    @field:Json(name = "height") val height: Float?,
    @field:Json(name = "stats") val stats: List<PokemonStats>?,
    @field:Json(name = "types") val types: List<PokemonTypeResponse>?
)
@JsonClass(generateAdapter = true)
data class PokemonStats(
    @field:Json(name = "base_stat") val baseStat: Int?,
    @field:Json(name = "effort") val effort: Int?,
    @field:Json(name = "stat") val statName: PokemonStatDescriotion?
)
@JsonClass(generateAdapter = true)
data class PokemonStatDescriotion(
    @field:Json(name = "name") val statName: String?
)