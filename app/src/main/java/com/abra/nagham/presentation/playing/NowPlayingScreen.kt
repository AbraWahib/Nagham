package com.abra.nagham.presentation.playing

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.abra.nagham.domain.model.playback.PlaybackState
import com.abra.nagham.presentation.playing.content.CollapsedNowPlayingContent
import com.abra.nagham.presentation.playing.content.ExpandedNowPlayingContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NowPlayingScreen(
    sheetState: SheetState,
    onDismiss: () -> Unit,
    playbackState: PlaybackState,
    onEvent: (NowPlayingEvent)->Unit
) {

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        modifier = Modifier.fillMaxHeight()
    ) {
        if (sheetState.currentValue == SheetValue.Expanded) {
            ExpandedNowPlayingContent(playbackState, onEvent)
        } else {
            CollapsedNowPlayingContent(playbackState, onEvent)
        }
    }
}