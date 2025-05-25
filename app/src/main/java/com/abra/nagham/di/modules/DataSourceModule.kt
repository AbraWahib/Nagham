package com.abra.nagham.di.modules

import android.content.ContentResolver
import com.abra.nagham.data.data_source.MediaStoreDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideMediaStoreDataSource(contentResolver: ContentResolver) =
        MediaStoreDataSource(contentResolver)
}