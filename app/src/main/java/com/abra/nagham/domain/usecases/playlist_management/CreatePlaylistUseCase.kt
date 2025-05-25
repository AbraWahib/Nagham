package com.abra.nagham.domain.usecases.playlist_management

import com.abra.nagham.domain.repo.PlaylistRepository

class CreatePlaylistUseCase(
    private val playlistRepository: PlaylistRepository
) {
    suspend operator fun invoke(name: String) {
        playlistRepository.createPlaylist(name)
    }
}