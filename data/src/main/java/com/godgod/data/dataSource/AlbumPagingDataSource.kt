package com.godgod.data.dataSource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.godgod.share.model.AlbumData
import javax.inject.Inject

class AlbumPagingDataSource @Inject constructor(private val albumProvider: AlbumDataProvider) :
    PagingSource<Int, AlbumData>() {

    companion object {
        private const val DEFAULT_PAGE_INDEX = 0
    }

    override fun getRefreshKey(state: PagingState<Int, AlbumData>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AlbumData> {
        return try {
            val pageIndex = params.key ?: DEFAULT_PAGE_INDEX
            val pageSize = params.loadSize

            val albumList = albumProvider.loadAlbum(pageIndex, pageSize)

            LoadResult.Page(
                data = albumList,
                prevKey = if (pageIndex == DEFAULT_PAGE_INDEX) null else pageIndex - 1,
                nextKey = if (albumList.isEmpty()) null else pageIndex + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}