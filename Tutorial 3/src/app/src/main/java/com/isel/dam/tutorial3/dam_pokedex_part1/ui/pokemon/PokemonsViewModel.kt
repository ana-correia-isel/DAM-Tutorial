package com.isel.dam.tutorial3.dam_pokedex_part1.ui.pokemon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
import com.isel.dam.tutorial3.dam_pokedex_part1.domain.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class PokemonsViewModel : ViewModel() {

    private val pokemonDomain = PokemonDomain()

    private lateinit var listPokemons : LiveData<List<Pokemon>>

    private var _pokemonRepository: PokemonRepository? = null

    private var _regions: MutableLiveData<List<PokemonRegion>> = MutableLiveData()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun initViewMode(pkRepository: PokemonRepository)
    {
        _pokemonRepository = pkRepository
    }
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

    fun getPokemonsByRegion(region: PokemonRegion): LiveData<List<Pokemon>>
    {
        var pokemons = MutableLiveData<List<Pokemon>>()
        _isLoading.value = true
        viewModelScope.launch (Dispatchers.Default){
            val r = _pokemonRepository?.getPokemonsByRegion(region)
            withContext(Dispatchers.Main) {
                if (r != null) {
                    pokemons.postValue(r.value)
                    _isLoading.value = false
                }
            }

        }
        return pokemons
    }
}