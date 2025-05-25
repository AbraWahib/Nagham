package com.abra.nagham.domain.usecases.playlist_management

import com.abra.nagham.domain.repo.PlaylistRepository

class AddSongToPlaylistUseCase (
    private val playlistRepository: PlaylistRepository
) {
    suspend operator fun invoke(playlistId: Long, songId: Long) {
        playlistRepository.addSongToPlaylist(playlistId, songId)
    }
}