package com.isel.dam.tutorial3.dam_pokedex_part1

import android.app.Application
import com.isel.dam.tutorial3.dam_pokedex_part1.di.PokemonContainer

class PokemonApplication : Application() {

    /**
     * Provides centralised Repository throughout the app
     */
    lateinit var pkContainer : PokemonContainer

    override fun onCreate() {
        super.onCreate()
        pkContainer = PokemonContainer(applicationContext)
    }

}