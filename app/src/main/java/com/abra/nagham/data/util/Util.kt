package com.abra.nagham.data.util

import android.annotation.SuppressLint
import kotlin.time.Duration.Companion.milliseconds

@SuppressLint("DefaultLocale")
fun Long.formatDuration():String{
    val duration = this.milliseconds
    val minutes = duration.inWholeMinutes
    val seconds = duration.inWholeSeconds % 60
    return String.format("%02d:%02d", minutes, seconds)
}