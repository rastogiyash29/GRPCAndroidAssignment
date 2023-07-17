package com.example.grpcandroidproject.repository

import com.example.grpcandroidproject.utils.Constants
import com.kotlang.social.FeedResponse
import com.kotlang.social.GetFeedRequest
import com.kotlang.social.UserPostGrpc
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder

class GrpcRepository private constructor(
    private val channel: ManagedChannel
) : Repository {
    private val stub = UserPostGrpc.newBlockingStub(channel)

    override fun getFeed(request: GetFeedRequest): FeedResponse {
        return stub.getFeed(request)
    }

    // ...

    companion object {
        @Volatile
        private var INSTANCE: GrpcRepository? = null

        fun getInstance(): GrpcRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: GrpcRepository(
                    ManagedChannelBuilder.forAddress(Constants.SOCIAL_MODEL_SERVER_BASE_ADDRESS,Constants.SOCIAL_MODEL_SERVER_PORT)
                        .usePlaintext()
                        .intercept(BearerTokenInterceptor(Constants.BEARER_TOKEN))
                        .build()
                ).also { INSTANCE = it }
            }
        }
    }
}
