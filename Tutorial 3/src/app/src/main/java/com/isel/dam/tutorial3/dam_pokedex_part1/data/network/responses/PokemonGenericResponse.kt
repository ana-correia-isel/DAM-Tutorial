package com.isel.dam.tutorial3.dam_pokedex_part1.data.network.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonGenericResponse(
    @field:Json(name = "url") val url: String?,
    @field:Json(name = "name") val name: String,
)
