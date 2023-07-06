package com.project.napptilus.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.project.napptilus.R
import com.project.napptilus.common.MarginItemDecoration
import com.project.napptilus.common.buildHomeState
import com.project.napptilus.common.launchAndCollect
import com.project.napptilus.databinding.FragmentHomeBinding
import com.skydoves.powerspinner.IconSpinnerAdapter
import com.skydoves.powerspinner.IconSpinnerItem
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val vm: HomeViewModel by viewModels()
    private lateinit var state: HomeState
    private val adapter = HomeAdapter { state.onItemClick(it) }
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        instances()
    }

    private fun instances() {
        stateHolder()
        observers()
        prepareRecyclerView()
    }

    private fun stateHolder() {
        state = buildHomeState()
    }

    private fun observers() {
        viewLifecycleOwner.launchAndCollect(vm.state) { uiState ->
            controlShimmer(uiState.isLoading)
            uiState.items?.let {
                binding.items = it
                adapter.notifyDataSetChanged()
            }
            uiState.spinnerItems?.let {
                prepareSpinner(it)
            }
            binding.error = uiState.error?.let(state::errorToString)
        }

        binding.bg.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.b_female -> {
                        vm.onFilterSelected(FEMALE)
                    }

                    R.id.b_male -> {
                        vm.onFilterSelected(MALE)
                    }
                }
            } else {
                if (group.checkedButtonId == View.NO_ID) {
                    vm.onFilterSelected(ALL)
                }
            }
        }

        binding.sv.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, _ ->
            if(!binding.sv.canScrollVertically(1)) {
                Timber.d("setOnScrollChangeListener")
                vm.loadOompaLoompaItems()
            }
        })
    }

    private fun prepareRecyclerView() {
        with(binding) {
            rv.adapter = adapter
            rv.addItemDecoration(MarginItemDecoration(15))
        }
    }

    private fun prepareSpinner(items: Set<String>) {
        with(binding.ps) {
            setSpinnerAdapter(IconSpinnerAdapter(this))
            setItems(
                arrayListOf<IconSpinnerItem>().apply {
                    items.forEach { add(IconSpinnerItem(text = it)) }
                }
            )
            getSpinnerRecyclerView().layoutManager = GridLayoutManager(requireContext(), 1)
            lifecycleOwner = this@HomeFragment

            setOnSpinnerItemSelectedListener<IconSpinnerItem> { _, _, _, value ->
                vm.onFilterSelected(profession = value.text.toString())
            }

            setOnSpinnerOutsideTouchListener { _, _ -> dismiss()}
        }

    }

    private fun controlShimmer(isLoading: Boolean) {
        with(binding) {
            loading = isLoading
            when (isLoading) {
                true -> shimmer.shimmerComponent.startShimmer()
                false -> shimmer.shimmerComponent.stopShimmer()
            }
        }
    }

    companion object {
        const val FEMALE = "F"
        const val MALE = "M"
        const val ALL = "ALL"
    }
}