package com.isel.dam.tutorial3.dam_pokedex_part1.data.database.entities

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.room.*
import com.isel.dam.tutorial3.dam_pokedex_part1.data.model.PokemonRegion

@Entity(tableName = "pokemon",
    foreignKeys = [
        ForeignKey(entity = RegionEntity::class, parentColumns = ["region_id"], childColumns = ["region_id"])
    ])
data class PokemonEntity(
    @PrimaryKey
    @ColumnInfo(name = "pkId")
    var pkId: Int,

    @ColumnInfo(name = "name")
    var name:String,

    @ColumnInfo(name = "imageUrl")
    var imageUrl: String,

    @ColumnInfo(name = "region_id")
    var regionId: Int
)


@Entity(tableName = "pokemon_region")
data class RegionEntity(
    @PrimaryKey
    @ColumnInfo(name = "region_id")
    var id: Int,

    @ColumnInfo(name = "region_name")
    var name: String,

    @ColumnInfo(name = "bg")
    val bg: Int,

    @ColumnInfo(name = "starters")
   val starters: Int
)

data class RegionWithPokemons(
    @Embedded
    val region: RegionEntity,

    @Relation(
        parentColumn = "region_id",
        entityColumn = "region_id"
    )
    val pokemon: List<PokemonEntity>
)


@Entity(tableName = "pokemon_type")
data class TypeEntity(
    @PrimaryKey
    @ColumnInfo(name = "typeId")
    var typeId: Int,

    @ColumnInfo(name = "type_name")
    var name: String,

    @ColumnInfo(name = "icon")
    val icon: Int,

    @ColumnInfo(name = "color")
    val color: Int
)

@Entity(primaryKeys = ["pkId", "typeId"])
data class PokemonTypesCrossRef(
    val pkId: Int,
    val typeId: Int
)

data class PokemonWithTypes(
    @Embedded val pokemon: PokemonEntity,
    @Relation(
        parentColumn = "pkId",
        entityColumn = "typeId",
        associateBy = Junction(PokemonTypesCrossRef::class)
    )
    val types: List<TypeEntity>
)

data class TypeWithPokemons(
    @Embedded val type: TypeEntity,
    @Relation(
        parentColumn = "typeId",
        entityColumn = "pkId",
        associateBy = Junction(PokemonTypesCrossRef::class)
    )
    val pokemons: List<PokemonEntity>
)


