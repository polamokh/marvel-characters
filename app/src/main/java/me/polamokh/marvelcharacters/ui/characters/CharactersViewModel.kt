package me.polamokh.marvelcharacters.ui.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import me.polamokh.marvelcharacters.repo.MarvelRepository
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(marvelRepository: MarvelRepository) : ViewModel() {

    val marvelCharacters = marvelRepository.getMarvelCharacters().cachedIn(viewModelScope)
}