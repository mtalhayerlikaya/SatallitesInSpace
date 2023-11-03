package com.example.satellitesinspace.common

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import timber.log.Timber
import java.net.URL


class MockInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response: Response

        val responseString: String
        val requestURL = chain.request().url.toString()
        Timber.d("Mock Request URL: $requestURL")
        val path = URL(requestURL).path
        val trimmedPath = path.replaceFirst("/".toRegex(), "")
        val fileName = trimmedPath.replace("/", "-")
        responseString = MockUtil.getMockResponse(fileName)
        response = Response.Builder()
            .code(200)
            .message(responseString)
            .request(chain.request())
            .protocol(Protocol.HTTP_1_0)
            .body(
            responseString
                .toByteArray()
                .toResponseBody("application/json".toMediaTypeOrNull()))
            .addHeader("content-type", "application/json")
            .build()

        return response
    }
}