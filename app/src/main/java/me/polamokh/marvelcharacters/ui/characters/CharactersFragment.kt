package me.polamokh.marvelcharacters.ui.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import me.polamokh.marvelcharacters.R
import me.polamokh.marvelcharacters.adapters.CharactersAdapter
import me.polamokh.marvelcharacters.databinding.FragmentCharactersBinding

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private lateinit var binding: FragmentCharactersBinding

    private val viewModel: CharactersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this

        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            if (menuItem.itemId == R.id.action_search) {
                findNavController().navigate(
                    CharactersFragmentDirections.actionCharactersFragmentToSearchFragment()
                )
            }
            false
        }

        val charactersAdapter = CharactersAdapter {
            findNavController().navigate(
                CharactersFragmentDirections.actionCharactersFragmentToDetailsFragment(it)
            )
        }
        with(binding.charactersRecyclerView) {
            adapter = charactersAdapter
        }

        viewModel.marvelCharacters.observe(viewLifecycleOwner) {
            charactersAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }
}