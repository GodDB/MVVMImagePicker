package com.godgod.mvvmimagepicker.di

import android.content.Context
import com.godgod.data.dataSource.AlbumDataProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object UtilsModule {

    @Singleton
    @Provides
    fun provideAlbumDataProvider(@ApplicationContext applicationContext: Context): AlbumDataProvider {
        return AlbumDataProvider(applicationContext)
    }
}