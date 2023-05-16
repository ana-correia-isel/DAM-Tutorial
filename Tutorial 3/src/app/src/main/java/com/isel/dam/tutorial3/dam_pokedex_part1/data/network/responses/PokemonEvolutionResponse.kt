package com.isel.dam.tutorial3.dam_pokedex_part1.data.network.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonEvolutionResponse(
    @field:Json(name = "id") val id: Int?,
    @field:Json(name = "chain") val evolve_Chain: PokemonEvolveChain,
)

@JsonClass(generateAdapter = true)
data class PokemonEvolveChain(
    @field:Json(name = "is_baby") val isBaby: Boolean?,
    @field:Json(name = "specie") val pokemon: PokemonGenericResponse?,
    @field:Json(name = "evolves_to") val chain: List<PokemonEvolveChain>?,
)