package com.project.vacancypromobile.utils

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.project.vacancypromobile.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

// Manager responsable de la gestion du Token d'authentification
class TokenManager @Inject constructor(val context: Context) {
    companion object {
        private val TOKEN_KEY = stringPreferencesKey("jwt_token")
    }

    fun getToken(): Flow<String?> {
        return context.dataStore.data.map { pre -> pre[TOKEN_KEY] }
    }

    suspend fun saveToken(token: String) {
        context.dataStore.edit { pre -> pre[TOKEN_KEY] = token }
    }

    suspend fun deleteToken(token: String) {
        context.dataStore.edit { pre -> pre.remove(TOKEN_KEY) }
    }
}