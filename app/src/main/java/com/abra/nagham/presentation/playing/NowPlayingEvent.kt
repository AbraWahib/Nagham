package com.abra.nagham.presentation.playing

sealed class NowPlayingEvent {
    object Pause: NowPlayingEvent()
    object Play: NowPlayingEvent()
    data class SeekTo(val position:Long): NowPlayingEvent()
    object ToggleShuffle: NowPlayingEvent()
    object Next: NowPlayingEvent()
    object Previous:NowPlayingEvent()
}