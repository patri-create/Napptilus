package com.project.napptilus.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.project.napptilus.R
import com.project.napptilus.common.buildDetailState
import com.project.napptilus.common.launchAndCollect
import com.project.napptilus.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val args: DetailFragmentArgs by navArgs()
    private val vm: DetailViewModel by viewModels()
    private lateinit var state: DetailState
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        instances()
        vm.loadOompaLoompaItem(args.id)
    }

    private fun instances() {
        stateHolder()
        observers()
    }

    private fun stateHolder() {
        state = buildDetailState()
    }

    private fun observers() {
        viewLifecycleOwner.launchAndCollect(vm.state) { uiState ->
            controlShimmer(uiState.isLoading)
            uiState.item?.let {
                binding.item = it
            }
            binding.error = uiState.error?.let(state::errorToString)
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
}