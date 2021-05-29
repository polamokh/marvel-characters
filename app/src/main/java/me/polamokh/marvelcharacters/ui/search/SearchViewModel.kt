package me.polamokh.marvelcharacters.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import me.polamokh.marvelcharacters.repo.MarvelRepository
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(marvelRepository: MarvelRepository) : ViewModel() {

    private val searchQuery = MutableLiveData<String>()

    val marvelCharacters = searchQuery.switchMap {
        marvelRepository.searchForMarvelCharacters(it).cachedIn(viewModelScope)
    }

    fun searchForCharacters(query: String?) {
        searchQuery.value = query
    }
}