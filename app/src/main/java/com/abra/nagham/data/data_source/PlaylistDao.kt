package com.abra.nagham.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.abra.nagham.data.database.entities.PlayListEntity
import com.abra.nagham.data.database.entities.PlaylistSongEntity
import com.abra.nagham.data.database.entities.PlaylistWithSongs
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaylistDao {
    @Insert
    suspend fun insertPlaylist(playlist: PlayListEntity): Long

    @Insert
    suspend fun insertPlaylistSong(playlistSong: PlaylistSongEntity)

    @Query("DELETE FROM playlist_songs WHERE playlistId = :playlistId AND songId = :songId")
    suspend fun deletePlaylistSong(playlistId: Long, songId: Long)

    @Query("DELETE FROM playlists WHERE id = :playlistId")
    suspend fun deletePlaylist(playlistId: Long)

    @Query("""
        SELECT playlists.*, GROUP_CONCAT(playlist_songs.songId) as songIds
        FROM playlists
        LEFT JOIN playlist_songs ON playlists.id = playlist_songs.playlistId
        GROUP BY playlists.id
    """)
    fun getAllPlaylistsWithSongs(): Flow<List<PlaylistWithSongs>>

    @Query("""
        SELECT playlists.*, GROUP_CONCAT(playlist_songs.songId) as songIds
        FROM playlists
        LEFT JOIN playlist_songs ON playlists.id = playlist_songs.playlistId
        WHERE playlists.id = :playlistId
        GROUP BY playlists.id
    """)
    fun getPlaylistWithSongs(playlistId: Long): Flow<PlaylistWithSongs?>
}