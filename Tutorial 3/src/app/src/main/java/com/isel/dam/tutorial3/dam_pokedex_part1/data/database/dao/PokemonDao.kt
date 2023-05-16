package com.isel.dam.tutorial3.dam_pokedex_part1.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.isel.dam.tutorial3.dam_pokedex_part1.data.database.entities.*
import com.isel.dam.tutorial3.dam_pokedex_part1.data.model.PokemonRegion

@Dao
interface PokemonDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemon(pokemon: PokemonEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemonWithType(pkType: PokemonTypesCrossRef)

    @Transaction
    @Query("SELECT * FROM pokemon_region WHERE region_id = :regionId")
    fun getPokemonByRegion(regionId: Int): RegionWithPokemons

    @Transaction
    @Query("SELECT * FROM pokemon WHERE pkId = :pokemonId")
    fun getTypesByPokemon(pokemonId: Int): PokemonWithTypes
}
