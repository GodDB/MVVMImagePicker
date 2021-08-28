package com.godgod.feature

import androidx.paging.PagingData
import com.godgod.domain.AlbumUseCase
import com.godgod.feature.base.BaseViewModel
import com.godgod.share.model.AlbumData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class AlbumPickerViewModel @Inject constructor(private val albumUseCase: AlbumUseCase) :
    BaseViewModel() {

    //pagesize 20
    val albumPagingData: Flow<PagingData<AlbumData>> = albumUseCase(params = 20)

}