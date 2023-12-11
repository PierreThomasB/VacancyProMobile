package com.project.vacancypromobile.services

import com.project.vacancypromobile.utils.TokenManager
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(val tokenManager: TokenManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val result = runBlocking {
            val token = getToken()
            val request = chain.request()
            if (!token.isNullOrEmpty()) {
                val newRequest = request.newBuilder().header("Authorization", "Bearer $token").build()
                return@runBlocking chain.proceed(newRequest)
            }
            return@runBlocking chain.proceed(request)
        }
        return result
    }

    suspend fun getToken(): String? = coroutineScope {
        val token = async {
            return@async tokenManager.getToken().first()
        }
        return@coroutineScope token.await()
    }
}