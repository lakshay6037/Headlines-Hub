package com.example.headlinejetpackcompose.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.headlinejetpackcompose.domain.manager.LocalUserManager
import com.example.headlinejetpackcompose.util.Constants
import com.example.headlinejetpackcompose.util.Constants.USER_SETTINGS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class LocalUserManagerImpl(
    private val context: Context
) : LocalUserManager {
    override suspend fun saveEntry() {
        context.dataStore.edit{
            settings ->
            settings[APP_KEY] = true

        }
    }

    override suspend fun readAppEntry(): Flow<Boolean> {
        return context.dataStore.data.map {
            preferences ->
            preferences[APP_KEY] ?: false

        }
    }
}


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_SETTINGS)

private val APP_KEY = booleanPreferencesKey(Constants.APP_ENTRY)