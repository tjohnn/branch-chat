package com.example.branchchat.ui.chatthreads

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.branchchat.R
import com.example.branchchat.databinding.FragmentChatThreadsBinding
import com.example.branchchat.presentation.chatthreads.ChatThreadsViewEvent
import com.example.branchchat.presentation.messages.MessagesViewEvent
import com.example.branchchat.presentation.chatthreads.ChatThreadsViewModel
import com.example.branchchat.presentation.chatthreads.ChatThreadsViewState
import com.example.branchchat.presentation.messages.MessagesViewState
import com.example.branchchat.ui.BaseFragment
import com.example.branchchat.ui.login.LoginFragmentDirections
import com.example.branchchat.ui.messages.MessagesAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatThreadsFragment : BaseFragment<ChatThreadsViewState, ChatThreadsViewEvent, FragmentChatThreadsBinding>() {

    override val viewModel: ChatThreadsViewModel by viewModels()

    private val chatsAdapter by lazy {
        ChatsAdapter().apply {
            onItemClickedListener = {
                viewModel.onChatItemClickedAction(it)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireAppCompatActivity().setTitle(R.string.chats)
        viewModel.onViewCreated()
        requireBinding().chatsListView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = chatsAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        requireAppCompatActivity().supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentChatThreadsBinding.inflate(inflater)

    override fun renderViewState(viewState: ChatThreadsViewState) {
        chatsAdapter.items = viewState.chatThreads
        with(requireBinding()) {
            chatsListView.isVisible = !viewState.isLoading
            loaderView.isVisible = viewState.isLoading
        }
    }

    override fun handleEvent(viewEvent: ChatThreadsViewEvent) {
        when (viewEvent) {
            is ChatThreadsViewEvent.OpenMessagesScreen -> {
                try {
                    requireView().findNavController().navigate(
                        ChatThreadsFragmentDirections.threadsFragmentToMessagesFragment(viewEvent.threadId)
                    )
                } catch (throwable: Throwable){}
            }
            ChatThreadsViewEvent.DisplayLoadErrorSnackBar -> renderErrorSnackBar()
        }
    }

    private fun renderErrorSnackBar() {
        Snackbar.make(
            requireView(),
            R.string.error_loading_chats_message,
            Snackbar.LENGTH_INDEFINITE
        ).setAction(R.string.retry){
            viewModel.onViewCreated()
        }.show()
    }
}