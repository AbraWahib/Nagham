package com.abra.nagham.di.modules

import androidx.media3.exoplayer.ExoPlayer
import com.abra.nagham.data.data_source.MediaStoreDataSource
import com.abra.nagham.data.data_source.PlaylistDao
import com.abra.nagham.data.repo.MusicRepositoryImpl
import com.abra.nagham.data.repo.PlaybackRepositoryImpl
import com.abra.nagham.data.repo.PlaylistRepositoryImpl
import com.abra.nagham.domain.repo.MusicRepository
import com.abra.nagham.domain.repo.PlaybackRepository
import com.abra.nagham.domain.repo.PlaylistRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideMusicRepository(dataSource: MediaStoreDataSource): MusicRepository =
        MusicRepositoryImpl(dataSource)

    @Provides
    @Singleton
    fun providePlaybackRepository(
        exoPlayer: ExoPlayer,
        coroutineScope: CoroutineScope
    ):PlaybackRepository =
        PlaybackRepositoryImpl(exoPlayer, coroutineScope)

    @Provides
    @Singleton
    fun providePlaylistRepository(
        playlistDao: PlaylistDao,
        mediaStoreDataSource: MediaStoreDataSource
    ):PlaylistRepository =
        PlaylistRepositoryImpl(playlistDao, mediaStoreDataSource)
}