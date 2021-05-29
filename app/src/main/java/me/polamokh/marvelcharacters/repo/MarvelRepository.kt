package me.polamokh.marvelcharacters.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import me.polamokh.marvelcharacters.network.MarvelService
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
}