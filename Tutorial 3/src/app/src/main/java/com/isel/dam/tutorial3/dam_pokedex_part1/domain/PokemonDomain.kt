package com.isel.dam.tutorial3.dam_pokedex_part1.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.isel.dam.tutorial3.dam_pokedex_part1.data.mocks.PokemonMockData
import com.isel.dam.tutorial3.dam_pokedex_part1.data.model.Pokemon
import com.isel.dam.tutorial3.dam_pokedex_part1.data.model.PokemonDetail
import com.isel.dam.tutorial3.dam_pokedex_part1.data.model.PokemonRegion
import com.isel.dam.tutorial3.dam_pokedex_part1.data.model.PokemonType

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
}