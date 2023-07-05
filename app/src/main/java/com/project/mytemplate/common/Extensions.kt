package com.project.mytemplate.common

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.project.mytemplate.R
import com.project.mytemplate.base.MainActivity
import com.project.mytemplate.base.MainState
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@FlowPreview
inline fun <T> Flow<NetworkStatus.NetworkStatusState>.networkMap(
    crossinline onUnavailable: suspend () -> T,
    crossinline onAvailable: suspend () -> T,
): Flow<T> = map { status ->
    when (status) {
        NetworkStatus.NetworkStatusState.Unavailable -> onUnavailable()
        NetworkStatus.NetworkStatusState.Available -> onAvailable()
    }
}


//region StateFlow
fun <T> LifecycleOwner.launchAndCollect(
    flow: Flow<T>,
    state: Lifecycle.State = Lifecycle.State.STARTED,
    body: (T) -> Unit
) {
    lifecycleScope.launch {
        this@launchAndCollect.repeatOnLifecycle(state) {
            flow.collect(body)
        }
    }
}
//endregion

fun MainActivity.buildMainState(
    activity: MainActivity = this,
    navController: NavController =
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment)
            .navController
) = MainState(activity, navController)