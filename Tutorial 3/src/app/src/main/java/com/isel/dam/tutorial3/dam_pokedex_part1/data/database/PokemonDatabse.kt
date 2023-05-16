package com.isel.dam.tutorial3.dam_pokedex_part1.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.isel.dam.tutorial3.dam_pokedex_part1.data.database.dao.PokemonDao
import com.isel.dam.tutorial3.dam_pokedex_part1.data.database.dao.RegionDao
import com.isel.dam.tutorial3.dam_pokedex_part1.data.database.dao.TypeDao
import com.isel.dam.tutorial3.dam_pokedex_part1.data.database.entities.PokemonEntity
import com.isel.dam.tutorial3.dam_pokedex_part1.data.database.entities.PokemonTypesCrossRef
import com.isel.dam.tutorial3.dam_pokedex_part1.data.database.entities.RegionEntity
import com.isel.dam.tutorial3.dam_pokedex_part1.data.database.entities.TypeEntity
import com.isel.dam.tutorial3.dam_pokedex_part1.data.model.Pokemon
import com.isel.dam.tutorial3.dam_pokedex_part1.data.model.PokemonRegion
import com.isel.dam.tutorial3.dam_pokedex_part1.data.model.PokemonType

@Database( entities = [PokemonEntity::class, RegionEntity::class, TypeEntity::class, PokemonTypesCrossRef::class ], version = 1, exportSchema = false )
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao (): PokemonDao
    abstract fun regionDao (): RegionDao
    abstract fun typeDao (): TypeDao

    companion object {
        // For Singleton instantiation
        @Volatile private var instance : PokemonDatabase ? = null
        fun getInstance ( context : Context): PokemonDatabase {
            if ( instance != null ) return instance !!
            synchronized ( this ) {
                instance = Room
                    .databaseBuilder ( context , PokemonDatabase :: class.java , "pokedex_dabase" )
                    .fallbackToDestructiveMigration ()
                    .build ()
            }
            return instance!!
        }
    }
}