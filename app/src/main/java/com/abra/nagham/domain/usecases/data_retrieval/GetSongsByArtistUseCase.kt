package com.abra.nagham.domain.usecases.data_retrieval

import com.abra.nagham.domain.repo.MusicRepository

class GetSongsByArtistUseCase(
    private val musicRepository: MusicRepository
) {
    operator fun invoke(artistId: Long) {
        musicRepository.getSongsByArtist(artistId)
    }
}