package com.godgod.data.dataSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.godgod.share.model.AlbumData
import java.lang.Exception
import javax.inject.Inject

class AlbumPagingDataSource @Inject constructor(private val albumProvider: AlbumDataProvider) :
    PagingSource<Int, AlbumData>() {

    override fun getRefreshKey(state: PagingState<Int, AlbumData>): Int? {
       return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AlbumData> {
        return try {
            val pageIndex = params.key ?: 0
            val pageSize = params.loadSize

            val albumList = albumProvider.loadAlbum(pageIndex, pageSize)

            LoadResult.Page(
                data = albumList,
                prevKey = null,
                nextKey = pageIndex + 1
            )
        } catch (e : Exception) {
            LoadResult.Error(e)
        }
    }
}