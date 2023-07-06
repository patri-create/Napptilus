package com.project.napptilus.ui.base

import androidx.navigation.NavController
import com.project.napptilus.R

class MainState(private val activity: MainActivity, private val navController: NavController) {

    fun handleNetwork(isConnected: Boolean) {
        when (isConnected) {
            true -> {
                if (navController.currentDestination?.id == R.id.network_dest)
                    navController.navigateUp()
            }
            false -> {
                navController.navigate(R.id.network_dest)
                activity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
                activity.supportActionBar?.setHomeButtonEnabled(false)
            }
        }
    }
}