package me.polamokh.marvelcharacters.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.polamokh.marvelcharacters.model.CharacterSpotlight
import me.polamokh.marvelcharacters.repo.MarvelRepository
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val marvelRepository: MarvelRepository) :
    ViewModel() {

    private val _comics = MutableLiveData<List<CharacterSpotlight>>()
    val comics: LiveData<List<CharacterSpotlight>>
        get() = _comics

    private val _events = MutableLiveData<List<CharacterSpotlight>>()
    val events: LiveData<List<CharacterSpotlight>>
        get() = _events

    private val _series = MutableLiveData<List<CharacterSpotlight>>()
    val series: LiveData<List<CharacterSpotlight>>
        get() = _series

    private val _stories = MutableLiveData<List<CharacterSpotlight>>()
    val stories: LiveData<List<CharacterSpotlight>>
        get() = _stories

    fun getCharacterSpotlights(marvelCharacterId: Int) {
        getCharacterComics(marvelCharacterId)

        getCharacterEvents(marvelCharacterId)

        getCharacterSeries(marvelCharacterId)

        getCharacterStories(marvelCharacterId)
    }

    private fun getCharacterStories(marvelCharacterId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val stories = marvelRepository.getMarvelCharacterSpotlights(
                    marvelCharacterId,
                    SpotlightType.STORIES
                )
                _stories.postValue(stories)
            }
        }
    }

    private fun getCharacterSeries(marvelCharacterId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val series = marvelRepository.getMarvelCharacterSpotlights(
                    marvelCharacterId,
                    SpotlightType.SERIES
                )
                _series.postValue(series)
            }
        }
    }

    private fun getCharacterEvents(marvelCharacterId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val events = marvelRepository.getMarvelCharacterSpotlights(
                    marvelCharacterId,
                    SpotlightType.EVENTS
                )
                _events.postValue(events)
            }
        }
    }

    private fun getCharacterComics(marvelCharacterId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val comics = marvelRepository.getMarvelCharacterSpotlights(
                    marvelCharacterId,
                    SpotlightType.COMICS
                )
                _comics.postValue(comics)
            }
        }
    }
}