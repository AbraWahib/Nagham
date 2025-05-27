package com.abra.nagham.presentation.artists

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.abra.nagham.R
import com.abra.nagham.domain.model.music.Artist
import com.abra.nagham.domain.model.music.ArtistWithDetails

@Composable
fun ArtistItem(artistWithDetails: ArtistWithDetails, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClick() }
    ) {
        AsyncImage(
            model = artistWithDetails.imageUri,
            placeholder = painterResource(R.drawable.artist_place_holder),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(100))
        )
        Column(Modifier.padding(horizontal = 8.dp), verticalArrangement = Arrangement.SpaceAround) {
            Text(artistWithDetails.artist.name, style = MaterialTheme.typography.titleMedium)
            Text(
                "Songs: ${artistWithDetails.songCount}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

