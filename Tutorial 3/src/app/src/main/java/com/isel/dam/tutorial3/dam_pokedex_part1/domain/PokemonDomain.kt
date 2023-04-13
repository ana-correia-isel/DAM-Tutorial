package com.isel.dam.tutorial3.dam_pokedex_part1.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.isel.dam.tutorial3.dam_pokedex_part1.data.mocks.PokemonMockData
import com.isel.dam.tutorial3.dam_pokedex_part1.data.model.Pokemon
import com.isel.dam.tutorial3.dam_pokedex_part1.data.model.PokemonDetail
import com.isel.dam.tutorial3.dam_pokedex_part1.data.model.PokemonRegion
import com.isel.dam.tutorial3.dam_pokedex_part1.data.model.PokemonType
import com.isel.dam.tutorial3.dam_pokedex_part1.data.paging.PokemonPagingSource

class PokemonDomain
{
    fun getAllRegions() : LiveData<List<PokemonRegion>>
    {
        return MutableLiveData<List<PokemonRegion>>(PokemonMockData.regions)
    }
    
    fun getAllPokemons(): LiveData<List<Pokemon>>
    {
        return MutableLiveData<List<Pokemon>>(PokemonMockData.pokemons)
    }

    fun getPokemonsByRegion(region: PokemonRegion): LiveData<List<Pokemon>>
    {
        return MutableLiveData<List<Pokemon>>(PokemonMockData.pokemons.filter { it.region == region })
    }

    fun getPokemonTypes(): List<PokemonType>
    {
        return ArrayList<PokemonType>(PokemonMockData.pokemonTypeMock)
    }

    fun getPokemonDetail(pokemon:Pokemon): LiveData<PokemonDetail>
    {
        return MutableLiveData(
            PokemonMockData.pokemonDetail.find { it.pokemon == pokemon })
    }

    fun getPokemonByPage(region:PokemonRegion? = null):LiveData<PagingData<Pokemon>> {

        return Pager(
            config = PagingConfig(
                pageSize = 4,
                enablePlaceholders = false,
                initialLoadSize = 5
            ),
            pagingSourceFactory = {
                PokemonPagingSource(region)
            }
            , initialKey = 0
        ).liveData
    }
}