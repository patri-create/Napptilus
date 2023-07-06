package com.project.napptilus.ui.detail

import android.content.Context
import androidx.navigation.NavController
import com.project.domain.Error
import com.project.napptilus.R

class DetailState (private val context: Context, private val navController: NavController) {

    fun errorToString(error: Error) = when (error) {
        Error.Connectivity -> context.getString(R.string.error_connectivity)
        is Error.Server -> context.getString(R.string.error_server)
        is Error.Unknown -> context.getString(R.string.error_unknown)
    }
}