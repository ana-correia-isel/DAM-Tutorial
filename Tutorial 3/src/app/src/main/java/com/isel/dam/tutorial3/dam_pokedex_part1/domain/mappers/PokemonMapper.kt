package com.isel.dam.tutorial3.dam_pokedex_part1.domain.mappers

import android.content.Context
import android.content.Entity
import com.isel.dam.tutorial3.dam_pokedex_part1.R
import com.isel.dam.tutorial3.dam_pokedex_part1.data.database.entities.PokemonEntity
import com.isel.dam.tutorial3.dam_pokedex_part1.data.database.entities.RegionEntity
import com.isel.dam.tutorial3.dam_pokedex_part1.data.database.entities.TypeEntity
import com.isel.dam.tutorial3.dam_pokedex_part1.data.model.Pokemon
import com.isel.dam.tutorial3.dam_pokedex_part1.data.model.PokemonRegion
import com.isel.dam.tutorial3.dam_pokedex_part1.data.model.PokemonType
import com.isel.dam.tutorial3.dam_pokedex_part1.data.network.responses.*


object PokemonMapper: IPokemonMapper
{
    private val regexToGetId = "\\/([^\\/]+)\\/?\$".toRegex()

    private lateinit var appContext: Context

    fun initialize(context: Context) {
        appContext = context.applicationContext
        // Additional initialization logic
    }
    override fun toRegionModel(response: PokemonRegionsResponse): PokemonRegion {
        var regionId = regexToGetId.find(response.url!!)?.value
        regionId = regionId?.removeSurrounding("/")

        val bgUri= "@drawable/bg_${response.name}"
        val startersUri = "@drawable/pk_${response.name}"

       return PokemonRegion(regionId?.toInt() ?: 0,response.name.toString(), appContext.resources.getIdentifier(bgUri,null, appContext.packageName),  appContext.resources.getIdentifier(startersUri,null, appContext.packageName))
    }

    override fun toRegionModel(
        entity: RegionEntity
    ): PokemonRegion
    {
        return PokemonRegion(entity.id,entity.name, entity.bg,  entity.starters)
    }

    override fun toRegionEntity(
        region: PokemonRegion
    ): RegionEntity
    {
        return RegionEntity(region.id,region.name, region.bg, region.starters)
    }

    override fun toPokemonModel(response: PokemonResponse, region: PokemonRegion, detail: PokemonDetailResponse): Pokemon {

        var pkId = regexToGetId.find(response.url!!)?.value
        pkId = pkId?.removeSurrounding("/")

        var imgUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${pkId}.png"

        var types = detail.types?.map {
            toPokemonTypeModel(it)
        }

        return Pokemon(pkId?.toInt() ?: 0,response.name.toString(),imgUrl,region, types ?: listOf())
    }

    override fun toPokemonModel(entity: PokemonEntity, region: PokemonRegion, types:List<TypeEntity>): Pokemon {

        val pkTypes = types.map { toPokemonTypeModel(it) }
        return Pokemon(entity.pkId, entity.name, entity.imageUrl, region, pkTypes)
    }

    override fun toPokemonTypeModel(response: PokemonTypeResponse): PokemonType
    {
        var typeId = regexToGetId.find(response.type?.url!!)?.value
        typeId = typeId?.removeSurrounding("/")

        val colorUri= "@color/${response.type.name}"
        val iconUri = "@drawable/${response.type.name}"

        return PokemonType(typeId?.toInt() ?: 0,response.type.name, appContext.resources.getIdentifier(iconUri,null, appContext.packageName), appContext.resources.getIdentifier(colorUri,null, appContext.packageName))
    }

    override fun toTypeEntity(response: PokemonGenericResponse): TypeEntity
    {
        var typeId = regexToGetId.find(response.url!!)?.value
        typeId = typeId?.removeSurrounding("/")

        val colorUri= "@color/${response.name}"
        val iconUri = "@drawable/${response.name}"

        return TypeEntity(typeId?.toInt() ?: 0,response.name, appContext.resources.getIdentifier(iconUri,null, appContext.packageName), appContext.resources.getIdentifier(colorUri,null, appContext.packageName))
    }

    override fun toTypeEntity(type: PokemonType): TypeEntity
    {
        return TypeEntity(type.id,type.name, type.icon, type.color)
    }

    override fun toPokemonTypeModel(entity: TypeEntity): PokemonType
    {
        return PokemonType(entity.typeId, entity.name, entity.icon, entity.color)
    }

    override fun toPokemonEntity(pokemon: Pokemon): PokemonEntity?
    {
        return pokemon.region?.let { PokemonEntity(pokemon.id, pokemon.name,pokemon.imageUrl, it.id) }
    }

}