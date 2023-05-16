package com.isel.dam.tutorial3.dam_pokedex_part1.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.isel.dam.tutorial3.dam_pokedex_part1.data.database.PokemonDatabase
import com.isel.dam.tutorial3.dam_pokedex_part1.data.database.dao.RegionDao
import com.isel.dam.tutorial3.dam_pokedex_part1.data.model.PokemonRegion
import com.isel.dam.tutorial3.dam_pokedex_part1.data.network.PokemonApi
import com.isel.dam.tutorial3.dam_pokedex_part1.domain.mappers.PokemonMapper

class RegionRepository(private val pokemonApi: PokemonApi,
                       private val regionDao: RegionDao
)
{
    suspend fun getRegions() : LiveData<List<PokemonRegion>>
    {
        var hasRegions = regionDao.count()
        if(hasRegions > 0)
        {
            var regionsEntities = regionDao.getRegions()

            val regions = regionsEntities.map {
                PokemonMapper.toRegionModel(it)
            }

            return MutableLiveData(regions)
        }
        try {
            var regionsResponse = pokemonApi.fetchRegionList()
            val regions = regionsResponse.results?.map {
                PokemonMapper.toRegionModel(it)
            }
            regions?.forEach {
                val r = PokemonMapper.toRegionEntity(it)
                regionDao.insertRegion(r) }

            return MutableLiveData(regions)
        }catch (e: java.lang.Exception)
        {
            Log.e("ERROR", e.toString())
        }
        return MutableLiveData<List<PokemonRegion>>()
    }

}