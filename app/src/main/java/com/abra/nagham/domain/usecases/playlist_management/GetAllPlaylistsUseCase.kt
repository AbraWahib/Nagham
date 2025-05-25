package com.abra.nagham.domain.usecases.playlist_management

import com.abra.nagham.domain.repo.PlaylistRepository

class GetAllPlaylistsUseCase(
    private val playlistRepository: PlaylistRepository
) {
    operator fun invoke() =
        playlistRepository.getAllPlaylists()
}