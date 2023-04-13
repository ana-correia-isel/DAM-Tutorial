package com.isel.dam.tutorial3.dam_pokedex_part1.ui.pokedex

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
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

        val childFragment = PokemonsFragment(pkClickListener = {

            val bundle = bundleOf(
                "pokemon" to it
            )

            findNavController().navigate(
                R.id.action_nav_pokedex_to_pokemonDetailFragment,
                bundle,
                null
            )
        })

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