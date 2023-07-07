package com.project.data.interceptors

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

/**
 * [Interceptor] that adds a cache [okhttp3.internal.http2.Header] to the server response for 60 seconds
 * in case the [Request] is of type GET ([.isGet])
 */
class OnlineInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        return if (isGet(request)) {
            chain.proceed(request)
                .newBuilder()
                .header("Cache-Control", "public, max-age= $MAX_AGE")
                .removeHeader("Pragma")
                .build()
        } else chain.proceed(request)
    }

    /**
     * @param request [Request]
     * @return true if the request method is GET
     */
    private fun isGet(request: Request): Boolean {
        return request.method.equals("get", ignoreCase = true)
    }

    companion object {
        /**
         * The maximum age of the cache in seconds.
         * 60 seconds * 10 = 600 seconds (10 minutes)
         */
        const val MAX_AGE = 60 * 10
    }
}
