package com.abra.nagham.domain.usecases.data_retrieval

import com.abra.nagham.domain.model.music.Album
import com.abra.nagham.domain.repo.MusicRepository
import kotlinx.coroutines.flow.Flow

class GetAllAlbumsUseCase (
    private val musicRepository: MusicRepository
) {
    operator fun invoke(): Flow<List<Album>> =
        musicRepository.getAllAlbums()
}