package com.abra.nagham.presentation.playing.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.abra.nagham.R
import com.abra.nagham.domain.model.playback.PlaybackState
import com.abra.nagham.presentation.playing.NowPlayingEvent

@Composable
fun CollapsedNowPlayingContent(state: PlaybackState, onEvent: (NowPlayingEvent) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = state.songTitle ?: "No Song Playing",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
        IconButton(onClick = { onEvent(NowPlayingEvent.Previous) }) {
            Icon(painterResource(R.drawable.skip_back), contentDescription = "Previous")
        }
        IconButton(onClick = { if (state.isPlaying) onEvent(NowPlayingEvent.Pause) else NowPlayingEvent.Play }) {
            Icon(
                painter = if (state.isPlaying) painterResource(R.drawable.pause) else painterResource(
                    R.drawable.play
                ),
                contentDescription = if (state.isPlaying) "Pause" else "Play"
            )
        }
        IconButton(onClick = { onEvent(NowPlayingEvent.Next) }) {
            Icon(painterResource(R.drawable.skip_forward), contentDescription = "Next")
        }
    }
}