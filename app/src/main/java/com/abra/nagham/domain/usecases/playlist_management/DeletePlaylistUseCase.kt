package com.abra.nagham.domain.usecases.playlist_management

import com.abra.nagham.domain.repo.PlaylistRepository

class DeletePlaylistUseCase (
    private val playlistRepository: PlaylistRepository
) {
    suspend operator fun invoke(playlistId: Long) {
        playlistRepository.deletePlaylist(playlistId)
    }
}