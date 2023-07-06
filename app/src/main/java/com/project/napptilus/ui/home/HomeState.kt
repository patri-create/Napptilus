package com.project.napptilus.ui.home

import android.content.Context
import androidx.navigation.NavController
import com.project.domain.Error
import com.project.domain.OompaLoompa
import com.project.napptilus.R

class HomeState(private val context: Context, private val navController: NavController) {

    fun onItemClick(item: OompaLoompa) {
        val action = HomeFragmentDirections.actionHomeDestToDetailDest(item)
        navController.navigate(action)
    }

    fun errorToString(error: Error) = when (error) {
        Error.Connectivity -> context.getString(R.string.error_connectivity)
        is Error.Server -> context.getString(R.string.error_server)
        is Error.Unknown -> context.getString(R.string.error_unknown)
    }
}