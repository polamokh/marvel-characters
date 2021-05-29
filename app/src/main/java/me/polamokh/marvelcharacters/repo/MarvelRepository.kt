package me.polamokh.marvelcharacters.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import me.polamokh.marvelcharacters.model.CharacterSpotlight
import me.polamokh.marvelcharacters.network.MarvelService
import me.polamokh.marvelcharacters.ui.details.SpotlightType
import javax.inject.Inject

class MarvelRepository @Inject constructor(private val marvelService: MarvelService) {

    fun getMarvelCharacters() = Pager(
        config = PagingConfig(DEFAULT_PAGE_SIZE, enablePlaceholders = false)
    ) {
        CharactersDataSource(marvelService)
    }.liveData

    fun searchForMarvelCharacters(query: String) = Pager(
        config = PagingConfig(DEFAULT_PAGE_SIZE, enablePlaceholders = false)
    ) {
        CharactersDataSource(marvelService, query)
    }.liveData

    suspend fun getMarvelCharacterSpotlights(
        marvelCharacterId: Int,
        spotlightType: SpotlightType
    ): List<CharacterSpotlight> {
        return when (spotlightType) {
            SpotlightType.COMICS -> marvelService.getComics(marvelCharacterId).await().data.results
            SpotlightType.EVENTS -> marvelService.getEvents(marvelCharacterId).await().data.results
            SpotlightType.SERIES -> marvelService.getSeries(marvelCharacterId).await().data.results
            SpotlightType.STORIES -> marvelService.getStories(marvelCharacterId)
                .await().data.results
        }
    }
}