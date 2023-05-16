package com.isel.dam.tutorial3.dam_pokedex_part1.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.isel.dam.tutorial3.dam_pokedex_part1.data.database.entities.TypeEntity

@Dao
interface TypeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertType(type: TypeEntity)
}