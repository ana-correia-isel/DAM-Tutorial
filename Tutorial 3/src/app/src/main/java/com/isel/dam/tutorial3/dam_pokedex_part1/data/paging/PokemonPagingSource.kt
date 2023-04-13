package com.isel.dam.tutorial3.dam_pokedex_part1.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.isel.dam.tutorial3.dam_pokedex_part1.data.mocks.PokemonMockData
import com.isel.dam.tutorial3.dam_pokedex_part1.data.model.Pokemon
import com.isel.dam.tutorial3.dam_pokedex_part1.data.model.PokemonRegion
import kotlinx.coroutines.delay
import kotlin.math.max

private const val STARTING_KEY = 0
private const val LOAD_DELAY_MILLIS = 3_000L


class PokemonPagingSource(
    val region: PokemonRegion?
)
    : PagingSource<Int, Pokemon>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {

        val currentPage = params.key ?: STARTING_KEY

        val pageSize = params.loadSize

        lateinit var data : List<Pokemon>

        var begin = currentPage
        var end = (currentPage + pageSize)

        var noMorePages = false

        if(region != null)
        {
            val pokemonsByRegion = PokemonMockData.pokemons.filter { it.region == region }

            if(pokemonsByRegion != null && pokemonsByRegion.isNotEmpty())
            {
                if (end >= pokemonsByRegion.size)
                {
                    end = pokemonsByRegion.size
                    noMorePages = true
                }

                data = pokemonsByRegion.subList(begin,end)
            }
        }
        else
        {
            if ((currentPage + pageSize) >= PokemonMockData.pokemons.size)
            {
                end = PokemonMockData.pokemons.size
                noMorePages = true
            }
            data = PokemonMockData.pokemons.subList(begin, end)
        }

        // Simulate a delay for loads adter the initial load
       // delay(LOAD_DELAY_MILLIS)

        return LoadResult.Page(
            data = data,
            prevKey = when (currentPage) {
                STARTING_KEY -> null
                else -> when (val prevKey = ensureValidKey(key = currentPage - pageSize)) {
                    // We're at the start, there's nothing more to load
                    STARTING_KEY -> null
                    else -> prevKey
                }
            },
            nextKey = if (noMorePages) null else end
        )
    }

    // The refresh key is used for the initial load of the next PagingSource, after invalidation
    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        // In our case we grab the item closest to the anchor position
        // then return its id - (state.config.pageSize / 2) as a buffer
        val anchorPosition = state.anchorPosition ?: return null
        val pokemon = state.closestItemToPosition(anchorPosition) ?: return null
        return ensureValidKey(key = pokemon.id - (state.config.pageSize / 2))
    }

    /**
     * Makes sure the paging key is never less than [STARTING_KEY]
     */
    private fun ensureValidKey(key: Int) = max(STARTING_KEY, key)
}