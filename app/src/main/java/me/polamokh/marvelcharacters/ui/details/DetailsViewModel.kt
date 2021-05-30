package me.polamokh.marvelcharacters.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import me.polamokh.marvelcharacters.model.MarvelCharacter
import me.polamokh.marvelcharacters.repo.MarvelRepository
import me.polamokh.marvelcharacters.utils.Result
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val marvelRepository: MarvelRepository,
    private val state: SavedStateHandle
) :
    ViewModel() {

    val comics = liveData(Dispatchers.IO) {
        emit(Result.Loading)
        try {
            emit(
                Result.Success(
                    marvelRepository.getMarvelCharacterSpotlights(
                        state.get<MarvelCharacter>("marvelCharacter")?.id!!,
                        SpotlightType.COMICS
                    )
                )
            )
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    val events = liveData(Dispatchers.IO) {
        emit(Result.Loading)
        try {
            emit(
                Result.Success(
                    marvelRepository.getMarvelCharacterSpotlights(
                        state.get<MarvelCharacter>("marvelCharacter")?.id!!,
                        SpotlightType.EVENTS
                    )
                )
            )
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    val series = liveData(Dispatchers.IO) {
        emit(Result.Loading)
        try {
            emit(
                Result.Success(
                    marvelRepository.getMarvelCharacterSpotlights(
                        state.get<MarvelCharacter>("marvelCharacter")?.id!!,
                        SpotlightType.SERIES
                    )
                )
            )
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    val stories = liveData(Dispatchers.IO) {
        emit(Result.Loading)
        try {
            emit(
                Result.Success(
                    marvelRepository.getMarvelCharacterSpotlights(
                        state.get<MarvelCharacter>("marvelCharacter")?.id!!,
                        SpotlightType.STORIES
                    )
                )
            )
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}