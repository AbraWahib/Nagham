package com.abra.nagham.domain.usecases.playlist_management

import com.abra.nagham.domain.repo.PlaylistRepository

class GetSongsInPlaylistUseCase (
    private val playlistRepository: PlaylistRepository
){
    operator fun invoke(playlistId: Long) {
        playlistRepository.getSongsInPlaylist(playlistId)
    }
}