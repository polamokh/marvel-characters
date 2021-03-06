package me.polamokh.marvelcharacters.repo

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import me.polamokh.marvelcharacters.model.MarvelCharacter
import me.polamokh.marvelcharacters.network.MarvelService
import retrofit2.HttpException
import java.io.IOException
import kotlin.math.ceil

class CharactersDataSource(
    private val marvelService: MarvelService,
    private val query: String? = null
) :
    PagingSource<Int, MarvelCharacter>() {

    private val TAG = "CharactersDataSource"

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MarvelCharacter> {
        val pageNumber = params.key ?: 0

        return try {
            val response =
                marvelService.getCharacters(
                    DEFAULT_PAGE_SIZE,
                    pageNumber * CHARACTERS_OFFSET,
                    query
                )

            val prevKey = if (pageNumber > 0) pageNumber - 1 else null

            val totalPages =
                ceil(response.data.total.toFloat() / response.data.limit.toFloat()).toInt()
            val nextKey = if (pageNumber < totalPages) pageNumber + 1 else null

            LoadResult.Page(
                data = response.data.results,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            Log.e(TAG, "load: $e")
            LoadResult.Error(e)
        } catch (e: HttpException) {
            Log.e(TAG, "load: $e")
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MarvelCharacter>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}