package com.abra.nagham.domain.repo

import com.abra.nagham.domain.models.Album
import com.abra.nagham.domain.models.Artist
import com.abra.nagham.domain.models.Song
import kotlinx.coroutines.flow.Flow

interface MusicRepository {
    fun getAllSongs(): Flow<List<Song>>
    fun getSongsByArtist(artistId: Long): Flow<List<Song>>
    fun getSongsByAlbum(albumId: Long): Flow<List<Song>>
    fun getAllArtists(): Flow<List<Artist>>
    fun getAllAlbums(): Flow<List<Album>>
}