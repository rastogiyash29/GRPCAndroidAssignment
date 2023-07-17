package com.example.grpcandroidproject.repository

import io.grpc.Metadata
import io.grpc.ClientInterceptor
import io.grpc.ClientCall
import io.grpc.MethodDescriptor
import io.grpc.CallOptions
import io.grpc.Channel
import io.grpc.ForwardingClientCall
import io.grpc.ForwardingClientCallListener

class BearerTokenInterceptor(private val bearerToken: String) : ClientInterceptor {
    override fun <ReqT, RespT> interceptCall(
        method: MethodDescriptor<ReqT, RespT>,
        callOptions: CallOptions,
        next: Channel
    ): ClientCall<ReqT, RespT> {
        return object : ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(
            next.newCall(method, callOptions)
        ) {
            override fun start(responseListener: Listener<RespT>, headers: Metadata) {
                headers.put(Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER), "Bearer $bearerToken")
                super.start(object : ForwardingClientCallListener.SimpleForwardingClientCallListener<RespT>(responseListener) {}, headers)
            }
        }
    }
}
