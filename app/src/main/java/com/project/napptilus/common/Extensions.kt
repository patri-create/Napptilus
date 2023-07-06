package com.project.napptilus.common

import android.content.Context
import android.util.TypedValue
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.project.domain.OompaLoompa
import com.project.napptilus.R
import com.project.napptilus.ui.base.MainActivity
import com.project.napptilus.ui.base.MainState
import com.project.napptilus.ui.detail.DetailState
import com.project.napptilus.ui.home.HomeAdapter
import com.project.napptilus.ui.home.HomeState
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

//region StateHolders
fun MainActivity.buildMainState(
    activity: MainActivity = this,
    navController: NavController =
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment)
            .navController
) = MainState(activity, navController)

fun Fragment.buildHomeState(
    context: Context = requireContext(),
    navController: NavController = findNavController()
) = HomeState(context, navController)

fun Fragment.buildDetailState(
    context: Context = requireContext(),
    navController: NavController = findNavController()
) = DetailState(context, navController)
//endregion

//region DataBindings
@BindingAdapter("loadImage")
fun AppCompatImageView.loadImage(image: String?) {
    if (!image.isNullOrEmpty()) {
        val options = RequestOptions().centerCrop()
        Glide.with(context).load(image).apply(options).into(this)
    }
}

@BindingAdapter("items")
fun RecyclerView.setItems(items: List<OompaLoompa>?) {
    (adapter as? HomeAdapter)?.let {
        it.items = items?: emptyList()
    }
}
//endregion

//region Utils
fun Int.toDp(context: Context): Int {
    val displayMetrics = context.resources.displayMetrics
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), displayMetrics).toInt()
}
//endregion