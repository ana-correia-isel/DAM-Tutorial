package com.isel.dam.tutorial3.dam_pokedex_part1.ui.region

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isel.dam.tutorial3.dam_pokedex_part1.PokemonApplication
import com.isel.dam.tutorial3.dam_pokedex_part1.data.model.PokemonRegion
import com.isel.dam.tutorial3.dam_pokedex_part1.domain.PokemonDomain
import com.isel.dam.tutorial3.dam_pokedex_part1.domain.PokemonRepository
import com.isel.dam.tutorial3.dam_pokedex_part1.domain.RegionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegionViewModel : ViewModel()
{
    private val _pokemonDomain = PokemonDomain()

    private var _regionRepository: RegionRepository? = null

    private var _regions: MutableLiveData<List<PokemonRegion>> = MutableLiveData()

    fun initViewMode(regionRepository: RegionRepository)
    {
        _regionRepository = regionRepository
    }
    fun getRegions(): LiveData<List<PokemonRegion>>
    {
        var regions = MutableLiveData<List<PokemonRegion>>()
        viewModelScope.launch(Dispatchers.Default) {
            val r = _regionRepository?.getRegions()
            if (r != null) {
                regions.postValue(r.value)
            }
        }
        return regions
    }
}