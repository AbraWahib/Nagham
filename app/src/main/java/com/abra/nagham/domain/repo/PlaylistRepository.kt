package com.abra.nagham.domain.repo

import com.abra.nagham.domain.models.Playlist
import com.abra.nagham.domain.models.Song
import kotlinx.coroutines.flow.Flow

interface PlaylistRepository {
    fun getAllPlaylists(): Flow<List<Playlist>>
    fun getSongsInPlaylist(playlistId: Long): Flow<List<Song>>
    fun createPlaylist(name: String): Long
    fun addSongToPlaylist(playlistId: Long, songId: Long)
    fun removeSongFromPlaylist(playlistId: Long, songId: Long)
    fun deletePlaylist(playlistId: Long)
}