package com.isel.dam.tutorial3.dam_pokedex_part1.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.isel.dam.tutorial3.dam_pokedex_part1.data.database.entities.RegionEntity
import com.isel.dam.tutorial3.dam_pokedex_part1.data.model.PokemonRegion

@Dao
interface RegionDao {

    @Query("SELECT * FROM pokemon_region")
    fun getRegions() : List<RegionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRegion(region: RegionEntity)

    @Query("SELECT COUNT(*) FROM pokemon_region")
    fun count(): Int
}

