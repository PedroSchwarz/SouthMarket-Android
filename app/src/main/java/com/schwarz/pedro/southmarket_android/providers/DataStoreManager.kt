package com.schwarz.pedro.southmarket_android.providers

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class DataStoreManager(private val context: Context) {

    private val Context.dataStore by preferencesDataStore(name = "SouthMarket-Android")

    suspend fun setTokenAndExpireDate(token: String, expiresIn: Long) {
        context.dataStore.edit { prefs ->
            prefs[TOKEN_KEY] = token
            prefs[EXPIRES_KEY] = expiresIn
        }
    }

    fun getToken(): Flow<String> = context.dataStore.data
        .catch { exception ->
            throw exception
        }.map { prefs ->
            prefs[TOKEN_KEY] ?: ""
        }

    fun getExpiresIn(): Flow<Long> = context.dataStore.data
        .catch { exception ->
            throw exception
        }.map { prefs ->
            prefs[EXPIRES_KEY] ?: 0
        }

    companion object {
        private val TOKEN_KEY = stringPreferencesKey("token")
        private val EXPIRES_KEY = longPreferencesKey("expires_in")
    }
}