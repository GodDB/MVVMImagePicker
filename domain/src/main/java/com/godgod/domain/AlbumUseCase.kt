package com.godgod.domain

import androidx.paging.PagingData
import com.godgod.data.repository.AlbumRepository
import com.godgod.domain.base.BaseFlowUseCase
import com.godgod.share.model.AlbumData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AlbumUseCase @Inject constructor(
    private val albumRepository: AlbumRepository
) : BaseFlowUseCase<Int, PagingData<AlbumData>>() {

    override fun execute(params: Int): Flow<PagingData<AlbumData>> {
        return albumRepository.getAllAlbumMedia(params)
    }
}