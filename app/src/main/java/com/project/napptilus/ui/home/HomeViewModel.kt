package com.project.napptilus.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.domain.DataWrapper
import com.project.domain.Error
import com.project.domain.OompaLoompa
import com.project.napptilus.ui.home.HomeFragment.Companion.ALL
import com.project.usecases.GetOompaLoompasByPageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getOompaLoompasByPageUseCase: GetOompaLoompasByPageUseCase): ViewModel(){

    private var items: MutableList<OompaLoompa>? = mutableListOf()
    private var totalPages = 1
    private var currentPage = 0
    private var lastGenderSelected: String = ALL
    private var lastProfessionSelected: String = ALL

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        loadOompaLoompaItems()
    }

    fun loadOompaLoompaItems() {
        viewModelScope.launch {
            loading(true)
            if(currentPage == totalPages) return@launch
            getOompaLoompasByPageUseCase(++currentPage).fold(::onError, ::onSuccess)
        }
    }

    private fun onError(error: Error) {
        _state.value = _state.value.copy(error = error)
        loading(false)
    }

    private fun onSuccess(data: DataWrapper) {
        data.total?.let { totalPages = it }
        items?.addAll(data.results!!)
        setupSpinner()
        onFilterSelected()
    }

    private fun setupSpinner() {
        var spinnerItems = setOf(ALL)
        items?.forEach { item -> item.profession?.let { spinnerItems = spinnerItems.plus(it) } }
        _state.value = _state.value.copy(spinnerItems = spinnerItems, error = null)
    }

    fun onFilterSelected(gender: String? = null, profession: String? = null) {
        gender?.let { lastGenderSelected = it }
        profession?.let { lastProfessionSelected = it }

        val itemsFiltered = items?.filter { item ->
            (lastProfessionSelected == ALL || item.profession == lastProfessionSelected) &&
                    (lastGenderSelected == ALL || item.gender == lastGenderSelected)
        }

        _state.value = _state.value.copy(items = itemsFiltered)
        loading(false)
    }


    private fun loading(isLoading: Boolean) {
         _state.value = _state.value.copy(isLoading = isLoading)
    }

    data class UiState(
        val isLoading: Boolean = true,
        val items: List<OompaLoompa>? = null,
        val spinnerItems: Set<String>? = null,
        val error: Error? = null,
    )
}