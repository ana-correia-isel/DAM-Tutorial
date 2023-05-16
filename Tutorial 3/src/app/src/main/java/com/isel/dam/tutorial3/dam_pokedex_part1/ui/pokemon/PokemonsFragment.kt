package com.isel.dam.tutorial3.dam_pokedex_part1.ui.pokemon

import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.isel.dam.tutorial3.dam_pokedex_part1.PokemonApplication
import com.isel.dam.tutorial3.dam_pokedex_part1.R
import com.isel.dam.tutorial3.dam_pokedex_part1.data.model.Pokemon
import com.isel.dam.tutorial3.dam_pokedex_part1.data.model.PokemonRegion
import com.isel.dam.tutorial3.dam_pokedex_part1.databinding.FragmentPokemonsBinding
import com.isel.dam.tutorial3.dam_pokedex_part1.ui.events.OnItemClickedListener
import com.isel.dam.tutorial3.dam_pokedex_part1.ui.pokemondetail.PokemonDetailFragment
import com.isel.dam.tutorial3.dam_pokedex_part1.ui.region.RegionAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


/*class PokemonsFragment : Fragment() {
    private var _binding: FragmentPokemonsBinding? = null
    private val viewModel: PokemonsViewModel by viewModels()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val region = arguments?.getParcelable("region", PokemonRegion::class.java)

        if(region == null)
        {
            viewModel.getAllPokemons().observe(viewLifecycleOwner, Observer {
                val pokemons: List<Pokemon> = it
                binding?.pokemonsRecyclerView?.adapter = PokemonsAdapter(pokemons,view.context)
            })
        }else
        {
            viewModel.getListPokemonsByRegion(region).observe(viewLifecycleOwner, Observer {
                val pokemons: List<Pokemon> = it
                binding?.pokemonsRecyclerView?.adapter = PokemonsAdapter(pokemons,view.context)
            })
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}*/

class PokemonsFragment(
    var pkClickListener: OnItemClickedListener? = null
) : Fragment() {

    private var _binding: FragmentPokemonsBinding? = null
    private val viewModel: PokemonsViewModel by viewModels()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonsBinding.inflate(inflater, container, false)
        val root: View = binding.root


        //checkNotNull(arguments?.getParcelable("region", PokemonRegion::class.java))
        viewModel!!.initViewMode((activity?.application as PokemonApplication).pkContainer.pokemonRepository)


       /* val pkAdapter = PokemonsAdapter( itemClickedListener = {
            if(pkClickListener != null)
            {
                pkClickListener?.invoke(it)
            }else
            {
               val bundle = bundleOf(
                    "pokemon" to it)

                findNavController().navigate(
                    R.id.action_nav_pokemons_to_pokemonDetailFragment,
                    bundle,
                    null
                )
            }

            /*val parent = parentFragment

            if(parent != null && parent !is NavHostFragment)
            {
                val navController = Navigation.findNavController(requireParentFragment().requireView())
                navController.navigate(
                    R.id.action_nav_pokedex_to_pokemonDetailFragment,
                    bundle,
                    null
                )

            }else
            {
                findNavController().navigate(
                    R.id.action_nav_pokemons_to_pokemonDetailFragment,
                    bundle,
                    null
                )
            }*/






            /*findNavController()
                .navigate(
                    R.id.action_nav_pokemons_to_pokemonDetailFragment,
                    bundle,
                    null
                )*/
        })
        binding?.pokemonsRecyclerView?.adapter = pkAdapter*/

        val region = arguments?.getParcelable("region", PokemonRegion::class.java)
        if(region != null)
        {
            viewModel.getPokemonsByRegion(region).observe(viewLifecycleOwner) {
                val pk: List<Pokemon> = it
                binding?.pokemonsRecyclerView?.adapter = PokemonsAdapterWithoutPager(pk,
                    itemClickedListener = {pokemon->

                        val bundle = bundleOf(
                            "pokemon" to it)

                        findNavController().navigate(
                            R.id.action_nav_pokemons_to_pokemonDetailFragment,
                            bundle,
                            null
                        )
                    }, requireView().context)


            }

            viewModel.isLoading.observe(viewLifecycleOwner, Observer {
                binding.prependProgress.isVisible = it
            })
        }

       /* if(region == null)
        {
            viewModel.getAllPokemonPager().observe(viewLifecycleOwner, Observer {
                it?.let {
                    pkAdapter.submitData(lifecycle, it) }
            })
        }else
        {

            viewModel.getAllPokemonsPagerByRegion(region).observe(viewLifecycleOwner, Observer {
                it?.let {
                    pkAdapter.submitData(lifecycle, it) }
            })
        }*/


      /*  lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                pkAdapter.loadStateFlow.collect {
                    binding.prependProgress.isVisible = it.source.prepend is LoadState.Loading
                    binding.appendProgress.isVisible = it.source.append is LoadState.Loading
                }
            }
        }*/

        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}