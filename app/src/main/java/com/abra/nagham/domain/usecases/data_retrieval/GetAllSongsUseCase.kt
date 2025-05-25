package com.abra.nagham.domain.usecases.data_retrieval

import com.abra.nagham.domain.model.music.Song
import com.abra.nagham.domain.repo.MusicRepository
import kotlinx.coroutines.flow.Flow

class GetAllSongsUseCase (
    private val musicRepository: MusicRepository
) {
    operator fun invoke(): Flow<List<Song>> =
        musicRepository.getAllSongs()
}