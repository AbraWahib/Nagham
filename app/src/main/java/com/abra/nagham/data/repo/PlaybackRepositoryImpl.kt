package com.abra.nagham.data.repo

import android.media.session.PlaybackState
import com.abra.nagham.data.data_source.PlaylistDao
import com.abra.nagham.domain.repo.PlaybackRepository
import com.abra.nagham.domain.usecases.playback.PlaybackUseCase
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class PlaybackRepositoryImpl @Inject constructor(
    private val playlistDao: PlaylistDao
) : PlaybackRepository {
    override fun setMediaItems(mediaItems: List<PlaybackUseCase.MediaItem>) {
        TODO("Not yet implemented")
    }

    override fun play() {
        TODO("Not yet implemented")
    }

    override fun pause() {
        TODO("Not yet implemented")
    }

    override fun seekTo(position: Long) {
        TODO("Not yet implemented")
    }

    override fun setShuffleMode(enable: Boolean) {
        TODO("Not yet implemented")
    }

    override val playbackState: StateFlow<PlaybackState>
        get() = TODO("Not yet implemented")
}