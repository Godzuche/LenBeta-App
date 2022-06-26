package com.lenbeta.lenbetaapp.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = "on_boarding_pref")

class DatastoreRepository(context: Context) {

    private val dataStore = context.datastore

    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_ONBOARDING_COMPLETED] = completed
        }
    }

    fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.data
            .catch {
                if (it is IOException) {
                    it.printStackTrace()
                    emit(emptyPreferences())
                } else {
                    throw it
                }
            }.map { preferences ->
                // emits false on first run instead of null
                val onBoardingState = preferences[IS_ONBOARDING_COMPLETED] ?: false
                onBoardingState
            }
    }

    companion object PreferenceKey {
        val IS_ONBOARDING_COMPLETED = booleanPreferencesKey("on_boarding_completed")

        @Volatile
        var INSTANCE: DatastoreRepository? = null
        fun getDataStoreRepository(context: Context): DatastoreRepository {
            return INSTANCE ?: synchronized(this) {
                val instance = DatastoreRepository(context)
                INSTANCE = instance
                instance
            }
        }
    }
}