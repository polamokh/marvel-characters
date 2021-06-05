package me.polamokh.marvelcharacters.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import me.polamokh.marvelcharacters.R
import me.polamokh.marvelcharacters.adapters.CharactersAdapter
import me.polamokh.marvelcharacters.adapters.PagingLoadStateAdapter
import me.polamokh.marvelcharacters.databinding.FragmentSearchBinding

@AndroidEntryPoint
class SearchFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var binding: FragmentSearchBinding

    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this

        binding.cancelSearch.setOnClickListener { findNavController().navigateUp() }

        binding.characterSearchView.setOnQueryTextListener(this)

        setupSearchCharactersRecyclerView()
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    private fun setupSearchCharactersRecyclerView() {
        val charactersAdapter = CharactersAdapter(R.layout.item_search_character) {
            findNavController().navigate(
                SearchFragmentDirections.actionSearchFragmentToDetailsFragment(it)
            )
        }

        with(binding.charactersRecyclerView) {
            adapter = charactersAdapter.withLoadStateHeaderAndFooter(
                PagingLoadStateAdapter { charactersAdapter.retry() },
                PagingLoadStateAdapter { charactersAdapter.retry() }
            )
        }

        viewModel.marvelCharacters.observe(viewLifecycleOwner) {
            charactersAdapter.submitData(lifecycle, it)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        viewModel.searchForCharacters(newText)
        return true
    }
}