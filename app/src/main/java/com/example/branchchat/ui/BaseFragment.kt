package com.example.branchchat.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.example.branchchat.presentation.base.BaseViewModel
import com.example.branchchat.presentation.base.ViewEvent
import com.example.branchchat.presentation.base.ViewState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class BaseFragment<VIEW_STATE: ViewState, VIEW_EVENT: ViewEvent, VIEW_BINDING: ViewBinding>:  Fragment() {

    abstract val viewModel: BaseViewModel<VIEW_STATE, VIEW_EVENT>
    private var binding: VIEW_BINDING? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = createViewBinding(inflater, container, savedInstanceState)
        return binding?.root
    }

    protected fun requireBinding() = requireNotNull(binding)

    protected fun requireAppCompatActivity() = activity as AppCompatActivity

    protected abstract fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): VIEW_BINDING

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewState()
        observeViewEvents()
    }

    open fun renderViewState(viewState: VIEW_STATE){}
    open fun handleEvent(viewEvent: VIEW_EVENT){}

    private fun observeViewState() {
        lifecycleScope.launch {
            viewModel.state.collect { viewState ->
                renderViewState(viewState)
            }
        }
    }

    private fun observeViewEvents() {
        lifecycleScope.launch {
            viewModel.action.collect { viewAction ->
                handleEvent(viewAction)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
