package com.isel.dam.tutorial3.dam_pokedex_part1.ui.pokemon

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.isel.dam.tutorial3.dam_pokedex_part1.data.model.Pokemon
import com.isel.dam.tutorial3.dam_pokedex_part1.data.model.PokemonRegion
import com.isel.dam.tutorial3.dam_pokedex_part1.data.paging.PokemonPagingSource
import com.isel.dam.tutorial3.dam_pokedex_part1.domain.PokemonDomain
import kotlinx.coroutines.flow.Flow


class PokemonsViewModel : ViewModel() {

    private val pokemonDomain = PokemonDomain()

    private lateinit var listPokemons : LiveData<List<Pokemon>>

    fun getListPokemonsByRegion(region: PokemonRegion): LiveData<List<Pokemon>>
    {
        listPokemons = pokemonDomain.getPokemonsByRegion(region)
        return listPokemons
    }

    fun getAllPokemons(): LiveData<List<Pokemon>>
    {
        listPokemons = pokemonDomain.getAllPokemons()
        return listPokemons
    }

    fun getAllPokemonPager(): LiveData<PagingData<Pokemon>>
    {
        return pokemonDomain.getPokemonByPage().cachedIn(viewModelScope)
    }

    fun getAllPokemonsPagerByRegion(region: PokemonRegion): LiveData<PagingData<Pokemon>>
    {
        return  pokemonDomain.getPokemonByPage(region)
    }
}