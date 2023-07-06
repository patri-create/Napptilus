package com.project.data.interceptors

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

/**
 *
 * [Interceptor] que añade a la respuesta del servidor un [okhttp3.internal.http2.Header]
 * de caché para 60segundos en caso de ser la [Request] de tipo GET (([.isGet] y
 * no haber definido el servidor ningún tipo de caché ([.isCacheNotDefined]
 * Ver https://developer.mozilla.org/es/docs/Web/HTTP/Headers/Cache-Control
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
     * @return true si el método de la petición es de tipo GET
     */
    private fun isGet(request: Request): Boolean {
        return request.method.equals("get", ignoreCase = true)
    }


    companion object {
        const val MAX_AGE = 60 * 10
    }

}