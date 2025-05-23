package com.abra.nagham.domain.usecases.playlist_management

import com.abra.nagham.domain.repo.PlaylistRepository
import javax.inject.Inject

class GetAllPlaylistsUseCase @Inject constructor(
    private val playlistRepository: PlaylistRepository
) {
    operator fun invoke() {
        playlistRepository.getAllPlaylists()
    }
}