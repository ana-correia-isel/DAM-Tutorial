package com.isel.dam.tutorial3.dam_pokedex_part1.domain.mappers

import com.isel.dam.tutorial3.dam_pokedex_part1.data.database.entities.PokemonEntity
import com.isel.dam.tutorial3.dam_pokedex_part1.data.database.entities.RegionEntity
import com.isel.dam.tutorial3.dam_pokedex_part1.data.database.entities.TypeEntity
import com.isel.dam.tutorial3.dam_pokedex_part1.data.model.Pokemon
import com.isel.dam.tutorial3.dam_pokedex_part1.data.model.PokemonRegion
import com.isel.dam.tutorial3.dam_pokedex_part1.data.model.PokemonType
import com.isel.dam.tutorial3.dam_pokedex_part1.data.network.responses.*

interface IPokemonMapper {

    fun toRegionModel(
        response: PokemonRegionsResponse
    ): PokemonRegion

    fun toPokemonModel(response: PokemonResponse, region: PokemonRegion, detail: PokemonDetailResponse): Pokemon

    fun toPokemonTypeModel(response: PokemonTypeResponse): PokemonType
    fun toPokemonTypeModel(entity: TypeEntity): PokemonType


    fun toRegionModel(
        entity: RegionEntity
    ): PokemonRegion

    fun toRegionEntity(
        region: PokemonRegion
    ): RegionEntity

    fun toTypeEntity(response: PokemonGenericResponse): TypeEntity

    fun toTypeEntity(type: PokemonType): TypeEntity

    fun toPokemonEntity(pokemon: Pokemon): PokemonEntity?

    fun toPokemonModel(entity: PokemonEntity, region: PokemonRegion, types:List<TypeEntity>): Pokemon
}