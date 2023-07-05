package com.project.data.datasource

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class PreferencesStorage @Inject constructor(context: Context) {

    val prefs: SharedPreferences = context.getSharedPreferences(
        PREFERENCES_FILE,
        Context.MODE_PRIVATE
    )

    companion object {
        const val PREFERENCES_FILE = "com.project.mytemplate.prefs"
    }
}