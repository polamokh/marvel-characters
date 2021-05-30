package me.polamokh.marvelcharacters.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import me.polamokh.marvelcharacters.adapters.SpotlightsAdapter
import me.polamokh.marvelcharacters.databinding.FragmentDetailsBinding
import me.polamokh.marvelcharacters.databinding.UiResultStateBinding
import me.polamokh.marvelcharacters.model.CharacterSpotlight
import me.polamokh.marvelcharacters.utils.Result

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
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.character = args.marvelCharacter

        setupSpotlightRecyclerViews(
            binding.comicsRecyclerView,
            binding.eventsRecyclerView,
            binding.seriesRecyclerView,
            binding.storiesRecyclerView
        )

        observeSpotlightStates(
            Pair(viewModel.comics, binding.comicsResultState),
            Pair(viewModel.events, binding.eventsResultState),
            Pair(viewModel.series, binding.seriesResultState),
            Pair(viewModel.stories, binding.storiesResultState)
        )
    }

    private fun setupSpotlightRecyclerViews(vararg recyclerViews: RecyclerView) {
        recyclerViews.forEach {
            with(it) {
                adapter = SpotlightsAdapter()
                layoutManager =
                    LinearLayoutManager(
                        requireContext(),
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
            }
        }
    }

    private fun observeSpotlightStates(
        vararg spotlights: Pair<LiveData<Result<List<CharacterSpotlight>>>, UiResultStateBinding>
    ) {
        spotlights.forEach { entry ->
            entry.first.observe(viewLifecycleOwner) {
                val resultStateBinding = entry.second

                resultStateBinding.loadingState.isVisible = it is Result.Loading
                resultStateBinding.successEmptyState.isVisible =
                    it is Result.Success && it.data.isEmpty()
                resultStateBinding.errorState.isVisible = it is Result.Error
            }
        }
    }
}