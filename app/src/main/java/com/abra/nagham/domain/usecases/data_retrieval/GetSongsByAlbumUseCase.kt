package com.abra.nagham.domain.usecases.data_retrieval

import com.abra.nagham.domain.repo.MusicRepository

class GetSongsByAlbumUseCase (
    private val musicRepository: MusicRepository
) {
    operator fun invoke(albumId: Long) {
        musicRepository.getSongsByAlbum(albumId)
    }
}