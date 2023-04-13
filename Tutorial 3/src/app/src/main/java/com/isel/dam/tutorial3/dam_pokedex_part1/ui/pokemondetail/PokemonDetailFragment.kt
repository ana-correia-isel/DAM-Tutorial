package com.isel.dam.tutorial3.dam_pokedex_part1.ui.pokemondetail

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.isel.dam.tutorial3.dam_pokedex_part1.data.model.Pokemon
import com.isel.dam.tutorial3.dam_pokedex_part1.databinding.FragmentPokemonDetailBinding
import com.isel.dam.tutorial3.dam_pokedex_part1.ui.utils.EqualSpacingItemDecoration


class PokemonDetailFragment : Fragment() {

    private var _binding: FragmentPokemonDetailBinding? = null
    private val viewModel: PokemonDetailViewModel by viewModels()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val activity: Activity? = activity
        if (activity is AppCompatActivity) {
            // Cast the Activity to AppCompatActivity to access the toolbar
            val appCompatActivity = activity
            // Hide the toolbar
            if (appCompatActivity.supportActionBar != null) {
                appCompatActivity.supportActionBar!!.hide()
            }
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pokemon = checkNotNull(arguments?.getParcelable("pokemon", Pokemon::class.java))

        binding.pokemon = pokemon
        viewModel.getPokemonDetail(pokemon).observe(viewLifecycleOwner, Observer {
            binding.pkDetail = it

            binding.typeListView.layoutManager = LinearLayoutManager(
                view.context,
                LinearLayoutManager.HORIZONTAL,false
            )

            binding.typeListView.adapter = TypeAdapter(pokemon.types,view.context)

            binding.typeListView.addItemDecoration(EqualSpacingItemDecoration(30, EqualSpacingItemDecoration.HORIZONTAL));

            binding.evolutionListView.adapter = EvolutionAdapter(it.evolution!!,view.context)

            binding.evolutionListView.addItemDecoration(EqualSpacingItemDecoration(30, EqualSpacingItemDecoration.VERTICAL));
        })

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

        val activity: Activity? = activity
        if (activity is AppCompatActivity) {
            // Cast the Activity to AppCompatActivity to access the toolbar
            val appCompatActivity = activity
            // Hide the toolbar
            if (appCompatActivity.supportActionBar != null) {
                appCompatActivity.supportActionBar!!.show()
            }
        }
    }


}