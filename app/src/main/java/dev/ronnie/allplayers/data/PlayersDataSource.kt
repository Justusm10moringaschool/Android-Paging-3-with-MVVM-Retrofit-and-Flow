package dev.ronnie.allplayers.data

import androidx.paging.PagingSource
import dev.ronnie.allplayers.api.PlayersApi
import dev.ronnie.allplayers.models.Player
import javax.inject.Inject

class PlayersDataSource (private val playersApi: PlayersApi) :
    PagingSource<Int, Player>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Player> {
        val page = params.key ?: 1

        return try {

            val response = playersApi.getPlayers(params.loadSize, page)

            val players = response.playersList
            LoadResult.Page(
                data = players,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.meta.next_page == null) null else page + 1
            )


        } catch (exception: Throwable) {

            LoadResult.Error(exception)
        }

    }
}