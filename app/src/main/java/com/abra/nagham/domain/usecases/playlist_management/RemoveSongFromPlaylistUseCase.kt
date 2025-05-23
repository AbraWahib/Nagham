package com.abra.nagham.domain.usecases.playlist_management

import com.abra.nagham.domain.repo.PlaylistRepository
import javax.inject.Inject

class RemoveSongFromPlaylistUseCase @Inject constructor(
    private val playlistRepository: PlaylistRepository
) {
    operator fun invoke(playlistId: Long, songId: Long) {
        playlistRepository.removeSongFromPlaylist(playlistId, songId)
    }
}