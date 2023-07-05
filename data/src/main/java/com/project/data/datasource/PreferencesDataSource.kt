package com.project.data.datasource

interface PreferencesDataSource {
    fun save(key: String, value: String)
    fun find(key: String): String
    fun delete(key: String)
    fun clear()
}