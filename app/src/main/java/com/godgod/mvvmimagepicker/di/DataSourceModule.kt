package com.godgod.mvvmimagepicker.di

import com.godgod.data.dataSource.AlbumDataSource
import com.godgod.data.dataSource.AlbumDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataSourceModule {

    @Binds
    abstract fun bindAlbumDataSource(albumDataSourceImpl: AlbumDataSourceImpl): AlbumDataSource

}