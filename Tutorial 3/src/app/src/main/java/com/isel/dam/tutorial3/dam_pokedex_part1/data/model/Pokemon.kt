package com.isel.dam.tutorial3.dam_pokedex_part1.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pokemon(var id: Int,
                   var name:String,
                   var imageUrl: String,
                   var region: PokemonRegion?,
                   var types: List<PokemonType>
) : Parcelable




