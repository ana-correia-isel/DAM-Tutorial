package com.isel.dam.tutorial3.dam_pokedex_part1.data.model

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize

@kotlinx.parcelize.Parcelize
@Entity(tableName = "Pokemon")
data class Pokemon(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "name")
    var name:String,

    @ColumnInfo(name = "imageUrl")
    var imageUrl: String,

    var region: PokemonRegion?,

    var types: List<PokemonType>
) : Parcelable




