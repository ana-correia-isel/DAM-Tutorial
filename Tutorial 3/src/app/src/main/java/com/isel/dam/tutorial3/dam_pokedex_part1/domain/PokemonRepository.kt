package com.isel.dam.tutorial3.dam_pokedex_part1.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.isel.dam.tutorial3.dam_pokedex_part1.data.database.PokemonDatabase
import com.isel.dam.tutorial3.dam_pokedex_part1.data.database.dao.PokemonDao
import com.isel.dam.tutorial3.dam_pokedex_part1.data.database.dao.TypeDao
import com.isel.dam.tutorial3.dam_pokedex_part1.data.database.entities.PokemonTypesCrossRef
import com.isel.dam.tutorial3.dam_pokedex_part1.data.mocks.PokemonMockData
import com.isel.dam.tutorial3.dam_pokedex_part1.data.model.Pokemon
import com.isel.dam.tutorial3.dam_pokedex_part1.data.model.PokemonDetail
import com.isel.dam.tutorial3.dam_pokedex_part1.data.model.PokemonRegion
import com.isel.dam.tutorial3.dam_pokedex_part1.data.model.PokemonType
import com.isel.dam.tutorial3.dam_pokedex_part1.data.network.PokemonApi
import com.isel.dam.tutorial3.dam_pokedex_part1.data.paging.PokemonPagingSource
import com.isel.dam.tutorial3.dam_pokedex_part1.domain.mappers.PokemonMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class PokemonRepository(private val pokemonApi: PokemonApi,
                        private val pokemonDao: PokemonDao,
                        private val typeDao: TypeDao)
{

    suspend fun getPokemonsByRegion(region: PokemonRegion): LiveData<List<Pokemon>>
    {
        try {

           var regionWithPokemons = pokemonDao.getPokemonByRegion(region.id)

            if(regionWithPokemons == null || regionWithPokemons.pokemon.isEmpty() ) {

                var pkByRegionResponse = pokemonApi.fetchPokemonByRegionId(region.id)
                val regexToGetId = "\\/([^\\/]+)\\/?\$".toRegex()
                val pokemons = pkByRegionResponse.pokemons?.map {
                    var pkId = it.url?.let { it1 -> regexToGetId.find(it1)?.value }
                    pkId = pkId?.removeSurrounding("/")
                    var pkDetailResponse =
                        pkId?.let { pokemonApi.fetchPokemonDetailById(pkId.toInt()) }
                    PokemonMapper.toPokemonModel(it, region, pkDetailResponse!!)


                }
                CoroutineScope(Dispatchers.Default).async{

                    pokemons?.let { savePokemonsinDB(it) }
                }

                return MutableLiveData<List<Pokemon>>(pokemons)
            }
            else{
                var pks = regionWithPokemons.pokemon.map {
                    var pkWithTypes = pokemonDao.getTypesByPokemon(it.pkId)
                    PokemonMapper.toPokemonModel(it, region, pkWithTypes.types )
                }

                return MutableLiveData(pks)
            }
        }catch (e: java.lang.Exception)
        {
            Log.e("ERROR", e.toString())
        }

        return MutableLiveData<List<Pokemon>>()
    }

    suspend fun getAllRegions() : LiveData<List<PokemonRegion>>
    {
        try {
            var regionsResponse = pokemonApi.fetchRegionList()
            val regions = regionsResponse.results?.map {
                PokemonMapper.toRegionModel(it)
            }
            return MutableLiveData<List<PokemonRegion>>(regions)
        }catch (e: java.lang.Exception)
        {
            Log.e("ERROR", e.toString())
        }

        return MutableLiveData<List<PokemonRegion>>()
    }


    private suspend fun savePokemonsinDB(pokemons: List<Pokemon>)
    {
        var types = pokemons.flatMap { it.types }.distinct()

        types.forEach{
            val type = PokemonMapper.toTypeEntity(it)
            typeDao.insertType(type)
        }

        pokemons.forEach{
            val pk = PokemonMapper.toPokemonEntity(it)
            pk?.let { it1 -> pokemonDao.insertPokemon(it1) }
            it.types.forEach{
                pokemonDao.insertPokemonWithType(PokemonTypesCrossRef(pk!!.pkId, it.id))
            }
        }


    }
}