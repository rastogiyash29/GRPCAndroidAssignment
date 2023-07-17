package com.example.grpcandroidproject.model
data class Comment(
    val user: User,
    val text: String,
    val timestamp: String
)
