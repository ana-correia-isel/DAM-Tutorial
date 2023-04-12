package com.isel.dam.tutorial3.dam_pokedex_part1.ui.pokemon

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.isel.dam.tutorial3.dam_pokedex_part1.R
import com.isel.dam.tutorial3.dam_pokedex_part1.data.model.Pokemon
import com.isel.dam.tutorial3.dam_pokedex_part1.databinding.ItemPokemonBinding
import com.isel.dam.tutorial3.dam_pokedex_part1.ui.events.OnItemClickedListener

class PokemonsAdapter(
    private val pokemonList: List<Pokemon>,
    private val context: Context
) : RecyclerView.Adapter<PokemonsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val viewBinding = ItemPokemonBinding.bind(itemView)
        fun bindView(item: Pokemon) {
            viewBinding.pokemon = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_pokemon, parent, false)


        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = pokemonList[position]
        holder.bindView(item)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

}
