package com.example.lab8_google_fit.data

import java.time.LocalDateTime

data class StepsData (
    val timestamp: LocalDateTime,
    val count: Int,
)