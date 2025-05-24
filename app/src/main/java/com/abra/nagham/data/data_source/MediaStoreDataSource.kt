package com.abra.nagham.data.data_source

import android.content.ContentResolver
import android.os.Build
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import com.abra.nagham.domain.models.music.Album
import com.abra.nagham.domain.models.music.Artist
import com.abra.nagham.domain.models.music.Song
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MediaStoreDataSource @Inject constructor(
    private val contentResolver: ContentResolver
) {
    fun getAllSongs(): Flow<List<Song>> =
        getSongs("${MediaStore.Audio.Media.IS_MUSIC} != 0", null)

    fun getSongsByArtist(artistId: Long): Flow<List<Song>> =
        getSongs(
            "${MediaStore.Audio.Media.IS_MUSIC} != 0 AND ${MediaStore.Audio.Media.ARTIST_ID} = ?",
            arrayOf(artistId.toString())
        )

    fun getSongsByAlbumId(albumId: Long): Flow<List<Song>> =
        getSongs(
            "${MediaStore.Audio.Media.IS_MUSIC} != 0 AND ${MediaStore.Audio.Media.ALBUM_ID} = ?",
            arrayOf(albumId.toString())
        )

    private fun getSongs(selection: String, selectionArgs: Array<String>?): Flow<List<Song>> =
        flow {
            val songs = mutableListOf<Song>()
            val projection = arrayOf(
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST_ID,
                MediaStore.Audio.Media.ALBUM_ID,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.DATA
            )
            contentResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                selectionArgs,
                MediaStore.Audio.Media.TITLE
            )?.use { cursor ->
                val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
                val titleColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
                val artistIdColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST_ID)
                val albumIdColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID)
                val durationColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)
                val dataColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)

                while (cursor.moveToNext()) {
                    songs.add(
                        Song(
                            songId = cursor.getLong(idColumn),
                            title = cursor.getString(titleColumn),
                            artistId = cursor.getLong(artistIdColumn),
                            albumId = cursor.getLong(albumIdColumn),
                            duration = cursor.getLong(durationColumn),
                            filePath = cursor.getString(dataColumn)
                        )
                    )
                }
            }
            emit(songs)
        }

    fun getAllArtists(): Flow<List<Artist>> =
        flow {
            val artists = mutableListOf<Artist>()
            val projection = arrayOf(
                MediaStore.Audio.Artists._ID,
                MediaStore.Audio.Artists.ARTIST
            )
            contentResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                MediaStore.Audio.Artists.ARTIST
            )?.use { cursor ->
                val artistId = cursor.getColumnIndexOrThrow(MediaStore.Audio.Artists._ID)
                val artistName = cursor.getColumnIndexOrThrow(MediaStore.Audio.Artists.ARTIST)
                while (cursor.moveToNext()) {
                    artists.add(
                        Artist(
                            id = cursor.getLong(artistId),
                            name = cursor.getString(artistName)
                        )
                    )
                }
            }
            emit(artists)
        }
    @RequiresApi(Build.VERSION_CODES.Q)
    fun getAllAlbums(): Flow<List<Album>> =
        flow {
            val albums = mutableListOf<Album>()
            val projection = arrayOf(
                MediaStore.Audio.Albums._ID,
                MediaStore.Audio.Albums.ALBUM,
                MediaStore.Audio.Albums.ARTIST_ID,
            )
            contentResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                MediaStore.Audio.Albums.ALBUM
            )?.use {cursor->
                val albumId = cursor.getColumnIndexOrThrow(MediaStore.Audio.Albums._ID)
                val albumName = cursor.getColumnIndexOrThrow(MediaStore.Audio.Albums.ALBUM)
                val artistId = cursor.getColumnIndexOrThrow(MediaStore.Audio.Albums.ARTIST_ID)
                while (cursor.moveToNext()){
                    albums.add(
                        Album(
                            id = cursor.getLong(albumId),
                            title = cursor.getString(albumName),
                            artistId = cursor.getLong(artistId)
                        )
                    )
                }
            }
        }

}