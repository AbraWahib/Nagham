package com.abra.nagham.util

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import kotlin.time.Duration.Companion.milliseconds

@SuppressLint("DefaultLocale")
fun Long.formatDuration(): String {
    val duration = this.milliseconds
    val minutes = duration.inWholeMinutes
    val seconds = duration.inWholeSeconds % 60
    return String.format("%02d:%02d", minutes, seconds)
}

fun hasAudioPermission(context: Context): Boolean {
    val permission = if (android.os.Build.VERSION.SDK_INT >= 33) {
        Manifest.permission.READ_MEDIA_AUDIO
    } else {
        Manifest.permission.READ_EXTERNAL_STORAGE
    }
    return ContextCompat.checkSelfPermission(
        context,
        permission
    ) == PackageManager.PERMISSION_GRANTED
}