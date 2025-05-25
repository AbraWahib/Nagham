package com.abra.nagham.di.modules

import com.abra.nagham.domain.repo.MusicRepository
import com.abra.nagham.domain.repo.PlaybackRepository
import com.abra.nagham.domain.repo.PlaylistRepository
import com.abra.nagham.domain.usecases.data_retrieval.GetAllAlbumsUseCase
import com.abra.nagham.domain.usecases.data_retrieval.GetAllArtistsUseCase
import com.abra.nagham.domain.usecases.data_retrieval.GetAllSongsUseCase
import com.abra.nagham.domain.usecases.data_retrieval.GetSongsByAlbumUseCase
import com.abra.nagham.domain.usecases.data_retrieval.GetSongsByArtistUseCase
import com.abra.nagham.domain.usecases.playback.PlaybackUseCase
import com.abra.nagham.domain.usecases.playlist_management.AddSongToPlaylistUseCase
import com.abra.nagham.domain.usecases.playlist_management.CreatePlaylistUseCase
import com.abra.nagham.domain.usecases.playlist_management.DeletePlaylistUseCase
import com.abra.nagham.domain.usecases.playlist_management.GetAllPlaylistsUseCase
import com.abra.nagham.domain.usecases.playlist_management.GetSongsInPlaylistUseCase
import com.abra.nagham.domain.usecases.playlist_management.RemoveSongFromPlaylistUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetAllSongsUseCase(repository: MusicRepository) =
        GetAllSongsUseCase(repository)


    @Provides
    @Singleton
    fun provideGetSongsByArtistUseCase(repository: MusicRepository) =
        GetSongsByArtistUseCase(repository)

    @Provides
    @Singleton
    fun provideGetSongsByAlbumUseCase(repository: MusicRepository) =
        GetSongsByAlbumUseCase(repository)


    @Provides
    @Singleton
    fun provideGetAllArtistsUseCase(repository: MusicRepository) =
        GetAllArtistsUseCase(repository)


    @Provides
    @Singleton
    fun provideGetAllAlbumsUseCase(repository: MusicRepository) =
        GetAllAlbumsUseCase(repository)


    @Provides
    @Singleton
    fun provideCreatePlaylistUseCase(repository: PlaylistRepository) =
        CreatePlaylistUseCase(repository)


    @Provides
    @Singleton
    fun provideAddSongToPlaylistUseCase(repository: PlaylistRepository) =
        AddSongToPlaylistUseCase(repository)


    @Provides
    @Singleton
    fun provideRemoveSongFromPlaylistUseCase(repository: PlaylistRepository) =
        RemoveSongFromPlaylistUseCase(repository)


    @Provides
    @Singleton
    fun provideGetAllPlaylistsUseCase(repository: PlaylistRepository) =
        GetAllPlaylistsUseCase(repository)


    @Provides
    @Singleton
    fun provideGetSongsInPlaylistUseCase(repository: PlaylistRepository) =
        GetSongsInPlaylistUseCase(repository)


    @Provides
    @Singleton
    fun provideDeletePlaylistUseCase(repository: PlaylistRepository) =
        DeletePlaylistUseCase(repository)


    @Provides
    @Singleton
    fun providePlaybackUseCase(repository: PlaybackRepository) =
        PlaybackUseCase(repository)
}