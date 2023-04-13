package com.isel.dam.tutorial3.dam_pokedex_part1.ui.pokedex

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.isel.dam.tutorial3.dam_pokedex_part1.R
import com.isel.dam.tutorial3.dam_pokedex_part1.databinding.FragmentPokedexBinding
import com.isel.dam.tutorial3.dam_pokedex_part1.ui.pokemon.PokemonsFragment
import com.isel.dam.tutorial3.dam_pokedex_part1.ui.slideshow.SlideshowViewModel


class PokedexFragment : Fragment() {

    private var _binding: FragmentPokedexBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPokedexBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val childFragment = PokemonsFragment()
        childFragmentManager.beginTransaction()
            .add(R.id.pokemon_list_fragment, childFragment)
            .commit()

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}