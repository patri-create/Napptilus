package com.project.data.repositories

import com.project.data.datasource.PreferencesDataSource
import javax.inject.Inject

class PreferencesRepository @Inject constructor(private val preferencesDataSource: PreferencesDataSource){

    fun save(key: String, value: String) {
        preferencesDataSource.save(key, value)
    }

    fun find(key: String): String {
        return preferencesDataSource.find(key)
    }
}