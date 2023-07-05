package com.project.data.datasource

import androidx.core.content.edit
import javax.inject.Inject

class PreferencesDataSourceImp @Inject constructor(private val preferencesStorage: PreferencesStorage) :
    PreferencesDataSource {
    override fun save(key: String, value: String) {
        preferencesStorage.prefs.edit { putString(key, value) }
    }

    override fun find(key: String): String {
        return preferencesStorage.prefs.getString(key, "") ?: ""
    }

    override fun delete(key: String) {
        preferencesStorage.prefs.edit { remove(key) }
    }

    override fun clear() {
        preferencesStorage.prefs.edit { clear() }
    }
}