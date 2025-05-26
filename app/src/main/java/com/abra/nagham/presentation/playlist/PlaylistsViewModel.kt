package com.abra.nagham.presentation.playlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abra.nagham.domain.model.playlist.Playlist
import com.abra.nagham.domain.usecases.playlist_management.AddSongToPlaylistUseCase
import com.abra.nagham.domain.usecases.playlist_management.CreatePlaylistUseCase
import com.abra.nagham.domain.usecases.playlist_management.DeletePlaylistUseCase
import com.abra.nagham.domain.usecases.playlist_management.GetAllPlaylistsUseCase
import com.abra.nagham.domain.usecases.playlist_management.RemoveSongFromPlaylistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaylistsViewModel @Inject constructor(
    private val getAllPlaylistsUseCase: GetAllPlaylistsUseCase,
    private val createPlaylistUseCase: CreatePlaylistUseCase,
    private val addSongToPlaylistUseCase: AddSongToPlaylistUseCase,
    private val removeSongFromPlaylistUseCase: RemoveSongFromPlaylistUseCase,
    private val deletePlaylistUseCase: DeletePlaylistUseCase
) : ViewModel() {
    private val _playlists = MutableStateFlow<List<Playlist>>(emptyList())
    val playlists: StateFlow<List<Playlist>> = _playlists.asStateFlow()

    init {
        viewModelScope.launch {
            getAllPlaylistsUseCase().collect { playlists ->
                _playlists.value = playlists
            }
        }
    }

    fun createPlaylist(name: String) {
        viewModelScope.launch {
            createPlaylistUseCase(name)
        }
    }

    fun addSongToPlaylist(playlistId: Long, songId: Long) {
        viewModelScope.launch {
            addSongToPlaylistUseCase(playlistId, songId)
        }
    }

    fun removeSongFromPlaylist(playlistId: Long, songId: Long) {
        viewModelScope.launch {
            removeSongFromPlaylistUseCase(playlistId, songId)
        }
    }

    fun deletePlaylist(playlistId: Long) {
        viewModelScope.launch {
            deletePlaylistUseCase(playlistId)
        }
    }
}