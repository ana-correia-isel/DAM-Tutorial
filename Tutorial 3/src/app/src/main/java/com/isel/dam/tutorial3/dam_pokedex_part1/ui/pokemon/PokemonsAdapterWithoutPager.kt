package com.isel.dam.tutorial3.dam_pokedex_part1.ui.pokemon

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.isel.dam.tutorial3.dam_pokedex_part1.R
import com.isel.dam.tutorial3.dam_pokedex_part1.data.model.Pokemon
import com.isel.dam.tutorial3.dam_pokedex_part1.data.model.PokemonRegion
import com.isel.dam.tutorial3.dam_pokedex_part1.databinding.ItemPokemonBinding
import com.isel.dam.tutorial3.dam_pokedex_part1.databinding.ItemRegionBinding
import com.isel.dam.tutorial3.dam_pokedex_part1.ui.events.OnItemClickedListener

class PokemonsAdapterWithoutPager   (
private val pkList: List<Pokemon>,
private val itemClickedListener: OnItemClickedListener? = null,
private val context: Context
) : RecyclerView.Adapter<PokemonsAdapterWithoutPager.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val viewBinding = ItemPokemonBinding.bind(itemView)
        fun bindView(pk: Pokemon, itemClickedListener: OnItemClickedListener?) {
            viewBinding.pokemon = pk
            itemView.setOnClickListener {
                itemClickedListener?.invoke(pk)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokemonsAdapterWithoutPager.ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_pokemon, parent, false)
        return PokemonsAdapterWithoutPager.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = pkList[position]
        if (item != null) {
            holder.bindView(item, itemClickedListener)
        }
    }


    override fun getItemCount(): Int {
        return pkList.size
    }

    companion object {
        private val POKEMON_DIFF_CALLBACK = object : DiffUtil.ItemCallback<Pokemon>() {
            override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
                oldItem == newItem
        }
    }
}



