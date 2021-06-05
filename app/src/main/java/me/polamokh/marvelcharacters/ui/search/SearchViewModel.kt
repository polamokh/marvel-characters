package me.polamokh.marvelcharacters.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import me.polamokh.marvelcharacters.repo.MarvelRepository
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(marvelRepository: MarvelRepository) : ViewModel() {

    private val TAG = "SearchViewModel"

    private val searchQuery = MutableStateFlow("")

    /**
     * Reduce number of API requests using Kotlin flow and operators
     *
     * [debounce] Wait until user finish typing search query.
     * [filter] Ensure that the query is not empty.
     * [flatMapLatest] Discard any previous call requests and get the latest.
     */
    @ExperimentalCoroutinesApi
    @FlowPreview
    val marvelCharacters = searchQuery
        .debounce(500)
        .filter {
            return@filter it.isNotEmpty()
        }
        .flatMapLatest {
            marvelRepository.searchForMarvelCharacters(it)
        }
        .asLiveData()
        .cachedIn(viewModelScope)

    fun searchForCharacters(query: String?) {
        if (query != null) {
            searchQuery.value = query
        }
    }
}