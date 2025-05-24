package com.abra.nagham.data.repo

import com.abra.nagham.data.data_source.MediaStoreDataSource
import com.abra.nagham.data.data_source.PlaylistDao
import com.abra.nagham.data.database.entities.PlayListEntity
import com.abra.nagham.data.database.entities.PlaylistSongEntity
import com.abra.nagham.domain.model.music.Song
import com.abra.nagham.domain.model.playlist.Playlist
import com.abra.nagham.domain.repo.PlaylistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PlaylistRepositoryImpl @Inject constructor(
    private val playlistDao: PlaylistDao,
    private val mediaStoreDataSource: MediaStoreDataSource
) : PlaylistRepository {
    override fun getAllPlaylists(): Flow<List<Playlist>> {
        return playlistDao.getAllPlaylistsWithSongs().map { playlistsWithSongs ->
            playlistsWithSongs.map { playlistWithSongs ->
                Playlist(
                    id = playlistWithSongs.playlist.id,
                    name = playlistWithSongs.playlist.name,
                )
            }
        }
    }

    override fun getSongsInPlaylist(playlistId: Long): Flow<List<Song>> {
        return playlistDao.getPlaylistWithSongs(playlistId)
            .combine(mediaStoreDataSource.getAllSongs()) { playlistWithSongs, allSongs ->
                val songsIds = playlistWithSongs?.songIds ?: emptyList()
                allSongs.filter { it.songId in songsIds }
            }
    }

    override suspend fun createPlaylist(name: String): Long {
        return playlistDao.insertPlaylist(PlayListEntity(name = name))
    }

    override suspend fun addSongToPlaylist(playlistId: Long, songId: Long) {
        playlistDao.insertPlaylistSong(PlaylistSongEntity(playlistId, songId))
    }

    override suspend fun removeSongFromPlaylist(playlistId: Long, songId: Long) {
        playlistDao.deletePlaylistSong(playlistId, songId)
    }

    override suspend fun deletePlaylist(playlistId: Long) {
        playlistDao.deletePlaylist(playlistId)
    }

}