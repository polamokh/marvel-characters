package me.polamokh.marvelcharacters.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import me.polamokh.marvelcharacters.adapters.SpotlightsAdapter
import me.polamokh.marvelcharacters.databinding.FragmentDetailsBinding

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val TAG = "DetailsFragment"

    private lateinit var binding: FragmentDetailsBinding

    private val args: DetailsFragmentArgs by navArgs()

    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val marvelCharacter = args.marvelCharacter

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.character = marvelCharacter

        if (savedInstanceState == null)
            viewModel.getCharacterSpotlights(marvelCharacter.id)

        with(binding.comicsRecyclerView) {
            adapter = SpotlightsAdapter()
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        with(binding.eventsRecyclerView) {
            adapter = SpotlightsAdapter()
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        with(binding.seriesRecyclerView) {
            adapter = SpotlightsAdapter()
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        with(binding.storiesRecyclerView) {
            adapter = SpotlightsAdapter()
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }
}