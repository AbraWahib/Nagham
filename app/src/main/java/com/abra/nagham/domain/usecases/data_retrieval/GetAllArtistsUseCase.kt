package com.abra.nagham.domain.usecases.data_retrieval

import com.abra.nagham.domain.model.music.Artist
import com.abra.nagham.domain.repo.MusicRepository
import kotlinx.coroutines.flow.Flow

class GetAllArtistsUseCase (
    private val musicRepository: MusicRepository
) {
    operator fun invoke(): Flow<List<Artist>> =
        musicRepository.getAllArtists()
}