package com.example.starfood.message

data class Replay(
    val id: String,
    val messageId: String,
    val readState: String,
    val replayContent: String,
    val replayDate: String,
    val replayHijriDate: String
)