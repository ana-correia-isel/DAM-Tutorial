package com.isel.dam.tutorial3.dam_pokedex_part1.ui.region

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.isel.dam.tutorial3.dam_pokedex_part1.data.model.PokemonRegion
import com.isel.dam.tutorial3.dam_pokedex_part1.domain.PokemonDomain

class RegionViewModel : ViewModel()
{
    private val _pokemonDomain = PokemonDomain()

    fun getRegions(): LiveData<List<PokemonRegion>>
    {
        return _pokemonDomain.getAllRegions()
    }
}