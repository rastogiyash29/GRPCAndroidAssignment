package com.example.grpcandroidproject.repository

import com.kotlang.social.FeedResponse
import com.kotlang.social.GetFeedRequest

interface Repository {
    fun getFeed(request: GetFeedRequest): FeedResponse
    // ... we can add more functions here
}