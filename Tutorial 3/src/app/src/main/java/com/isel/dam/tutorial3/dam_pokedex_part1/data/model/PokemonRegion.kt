package com.isel.dam.tutorial3.dam_pokedex_part1.data.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "PokemonRegion")
data class PokemonRegion(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id:Int,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "bg")
    @DrawableRes val bg: Int,
    @ColumnInfo(name = "starters")
    @DrawableRes val starters: Int
) : Parcelable {
}
