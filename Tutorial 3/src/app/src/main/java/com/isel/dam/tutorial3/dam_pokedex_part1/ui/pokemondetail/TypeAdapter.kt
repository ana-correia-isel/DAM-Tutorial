package com.isel.dam.tutorial3.dam_pokedex_part1.ui.pokemondetail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.isel.dam.tutorial3.dam_pokedex_part1.R
import com.isel.dam.tutorial3.dam_pokedex_part1.data.model.PokemonType
import com.isel.dam.tutorial3.dam_pokedex_part1.databinding.ItemTypeDetailBinding

class TypeAdapter(
    private val list: List<PokemonType>,
    private val context: Context
) : RecyclerView.Adapter<TypeAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val viewBinding = ItemTypeDetailBinding.bind(itemView)
        fun bindView(item: PokemonType) {
            viewBinding.type = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_type_detail, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bindView(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}