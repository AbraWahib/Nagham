package com.abra.nagham.domain.usecases.playlist_management

import com.abra.nagham.domain.repo.PlaylistRepository

class RemoveSongFromPlaylistUseCase (
    private val playlistRepository: PlaylistRepository
) {
    suspend operator fun invoke(playlistId: Long, songId: Long) {
        playlistRepository.removeSongFromPlaylist(playlistId, songId)
    }
}