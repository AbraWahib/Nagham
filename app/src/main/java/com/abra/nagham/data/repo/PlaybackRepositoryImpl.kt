package com.abra.nagham.data.repo

import androidx.media3.common.MediaItem as ExoMediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.abra.nagham.domain.model.playback.PlaybackState
import com.abra.nagham.domain.repo.PlaybackRepository
import com.abra.nagham.domain.usecases.playback.PlaybackUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class PlaybackRepositoryImpl @Inject constructor(
    private val exoPlayer: ExoPlayer,
    private val coroutineScope: CoroutineScope
) : PlaybackRepository {

    private val _playbackState = MutableStateFlow(PlaybackState())
    override val playbackState: StateFlow<PlaybackState> = _playbackState.asStateFlow()

    private var updateJob: Job? = null

    init {
        setupPlayerListener()
    }

    override fun setMediaItems(mediaItems: List<PlaybackUseCase.MediaItem>) {
        exoPlayer.setMediaItems(mediaItems.map { mediaItem ->
            ExoMediaItem.Builder()
                .setMediaId(mediaItem.id.toString())
                .setUri(mediaItem.filePath)
                .build()
        })
        exoPlayer.prepare()
    }

    override fun play() {
        exoPlayer.play()
    }

    override fun pause() {
        exoPlayer.pause()
    }

    override fun seekTo(position: Long) {
        exoPlayer.seekTo(position)
    }

    override fun seekToIndex(index: Int) {
        exoPlayer.seekTo(index, 0L)
    }

    override fun setShuffleMode(enable: Boolean) {
        exoPlayer.shuffleModeEnabled = enable
        updatePlaybackState()
    }

    private fun setupPlayerListener() {
        exoPlayer.addListener(object : Player.Listener {
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                updatePlaybackState()
            }

            override fun onMediaItemTransition(mediaItem: ExoMediaItem?, reason: Int) {
                updatePlaybackState()
            }

            override fun onPlaybackStateChanged(playbackState: Int) {
                updatePlaybackState()
            }
        })

        updateJob = coroutineScope.launch(Dispatchers.Main) {
            while (true) {
                if (exoPlayer.isPlaying) {
                    updatePlaybackState()
                }
                kotlinx.coroutines.delay(1000) // Update every second
            }
        }
    }

    private fun updatePlaybackState() {
        _playbackState.value = PlaybackState(
            currentSongId = exoPlayer.currentMediaItem?.mediaId?.toLongOrNull(),
            isPlaying = exoPlayer.isPlaying,
            currentPosition = exoPlayer.currentPosition,
            duration = exoPlayer.duration,
            isShuffleEnabled = exoPlayer.shuffleModeEnabled
        )
    }

    // Clean up resources
    fun release() {
        updateJob?.cancel()
        exoPlayer.release()
    }
}