package com.example.hepatitisapp.util

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ResultPreferences private constructor(private val dataStore: DataStore<Preferences>) {

    private val DIE = doublePreferencesKey("die")
    private val LIVE = doublePreferencesKey("live")


    val diePrediction: Flow<Double?>
        get() = dataStore.data.map { preferences ->
            preferences[DIE] ?: null
        }

    val livePrediction: Flow<Double?>
        get() = dataStore.data.map { preferences ->
            preferences[LIVE] ?: null
        }


    suspend fun saveMortalityPrediction(die: Double, live: Double) {
        dataStore.edit { preferences ->
            preferences[DIE] = die
            preferences[LIVE] = live
        }
    }


    suspend fun clearPrediction() {
        dataStore.edit { preferences ->
            preferences.remove(DIE)
            preferences.remove(LIVE)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ResultPreferences? = null

        fun getInstance(dataStore: DataStore<Preferences>): ResultPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = ResultPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}