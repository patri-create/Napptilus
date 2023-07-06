package com.project.napptilus.ui.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.project.napptilus.R
import com.project.napptilus.common.buildMainState
import com.project.napptilus.common.launchAndCollect
import com.project.napptilus.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val vm: MainViewModel by viewModels()
    private lateinit var state: MainState
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        instances()
    }

    private fun instances() {
        stateHolder()
        configurations()
        observers()
    }

    private fun stateHolder() {
        state = buildMainState()
    }

    private fun configurations() {
        configureNavigation()
    }

    private fun configureNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun observers() {
        launchAndCollect(vm.state) { uiState ->
            state.handleNetwork(uiState.isConnected)
        }
        listenNetworkConnection()
    }

    private fun listenNetworkConnection() {
        vm.checkNetworkConnection()
    }
}