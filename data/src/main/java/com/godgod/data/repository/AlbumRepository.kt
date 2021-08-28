package com.godgod.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.godgod.data.dataSource.AlbumPagingDataSource
import com.godgod.share.model.AlbumData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface AlbumRepository {
    fun getAllAlbumMedia(pageSize: Int): Flow<PagingData<AlbumData>>
}

class AlbumRepositoryImpl @Inject constructor(
    private val albumDataSource: AlbumPagingDataSource
) : AlbumRepository {

    override fun getAllAlbumMedia(pageSize : Int): Flow<PagingData<AlbumData>> {
        return Pager(PagingConfig(pageSize = pageSize)) {
            albumDataSource
        }.flow
    }
}