package com.isel.dam.tutorial3.dam_pokedex_part1.di

import android.content.Context
import androidx.room.Room
import com.isel.dam.tutorial3.dam_pokedex_part1.data.database.PokemonDatabase
import com.isel.dam.tutorial3.dam_pokedex_part1.data.network.PokemonApi
import com.isel.dam.tutorial3.dam_pokedex_part1.domain.PokemonDomain
import com.isel.dam.tutorial3.dam_pokedex_part1.domain.PokemonRepository
import com.isel.dam.tutorial3.dam_pokedex_part1.domain.RegionRepository
import com.isel.dam.tutorial3.dam_pokedex_part1.domain.mappers.PokemonMapper

class PokemonContainer(private val context:Context) {

    companion object {
        // For Singleton instantiation
        @Volatile private var instance : PokemonContainer ? = null
        fun getInstance (context : Context): PokemonContainer {
            if ( instance != null ) return instance !!
            synchronized ( this ) {
                return PokemonContainer(context)
            }
            return instance!!
        }
    }

    val pokemonClient: PokemonApi

    val pokemonDomain : PokemonDomain

    val regionRepository : RegionRepository

    val pokemonRepository : PokemonRepository

    val pokemonDBManager : PokemonDatabase

    init {
        pokemonClient = NetworkModule.initPokemonRemoteService()
        pokemonDomain = PokemonDomain()
        pokemonDBManager = PokemonDatabase.getInstance(context)
        pokemonRepository = PokemonRepository(pokemonClient, pokemonDBManager.pokemonDao(), pokemonDBManager.typeDao())
        regionRepository = RegionRepository(pokemonClient,pokemonDBManager.regionDao())

        PokemonMapper.initialize(context)
    }
}