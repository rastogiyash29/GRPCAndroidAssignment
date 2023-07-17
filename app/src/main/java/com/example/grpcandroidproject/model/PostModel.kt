package com.example.grpcandroidproject.model
data class PostModel(
    val user: User,
    val timeStamp: String,
    val postText: String,
    val postImages: List<Int>,
    val likesCount: Int,
    val commentsCount: Int,
    val liked:Boolean
)
