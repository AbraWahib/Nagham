package com.abra.nagham.data.repo

import android.os.Build
import androidx.annotation.RequiresApi
import com.abra.nagham.data.data_source.MediaStoreDataSource
import com.abra.nagham.domain.model.music.Album
import com.abra.nagham.domain.model.music.Artist
import com.abra.nagham.domain.model.music.Song
import com.abra.nagham.domain.repo.MusicRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MusicRepositoryImpl @Inject constructor(
    private val mediaStoreDataSource: MediaStoreDataSource
):MusicRepository {
    override fun getAllSongs(): Flow<List<Song>> =
        mediaStoreDataSource.getAllSongs()

    override fun getSongsByArtist(artistId: Long): Flow<List<Song>> =
        mediaStoreDataSource.getSongsByArtist(artistId)

    override fun getSongsByAlbum(albumId: Long): Flow<List<Song>> =
        mediaStoreDataSource.getSongsByAlbumId(albumId)

    override fun getAllArtists(): Flow<List<Artist>> =
        mediaStoreDataSource.getAllArtists()

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun getAllAlbums(): Flow<List<Album>> =
        mediaStoreDataSource.getAllAlbums()
}