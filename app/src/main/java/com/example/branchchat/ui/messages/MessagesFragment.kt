package com.example.branchchat.ui.messages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.branchchat.R
import com.example.branchchat.databinding.FragmentMessagesBinding
import com.example.branchchat.presentation.messages.MessagesViewEvent
import com.example.branchchat.presentation.messages.MessagesViewModel
import com.example.branchchat.presentation.messages.MessagesViewState
import com.example.branchchat.ui.BaseFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MessagesFragment : BaseFragment<MessagesViewState, MessagesViewEvent, FragmentMessagesBinding>() {

    override val viewModel: MessagesViewModel by viewModels()

    private val args by navArgs<MessagesFragmentArgs>()

    private val messagesAdapter by lazy {
        MessagesAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        requireAppCompatActivity().supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireAppCompatActivity().title = getString(R.string.messages_thread_x, args.threadId)
        viewModel.onViewCreated(args.threadId)
        requireBinding().chatsListView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = messagesAdapter
        }
        requireBinding().sendButton.setOnClickListener {
            viewModel.sendNewMessage(
                args.threadId,
                requireBinding().messageTextBox.text.toString()
            )
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            requireView().findNavController().popBackStack()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentMessagesBinding.inflate(inflater)

    override fun renderViewState(viewState: MessagesViewState) {
        messagesAdapter.items = viewState.messages
        requireBinding().chatsListView.scrollToPosition(messagesAdapter.items.size - 1)
        with(requireBinding()) {
            chatsListView.isVisible = !viewState.isLoading
            loaderView.isVisible = viewState.isLoading
        }
    }

    override fun handleEvent(viewEvent: MessagesViewEvent) {
        when (viewEvent) {
            MessagesViewEvent.DisplayLoadErrorSnackBar -> renderErrorSnackBar()
            MessagesViewEvent.DisplayNewMessageErrorSnackBar -> renderMessageErrorSnackBar()
            MessagesViewEvent.OnMessageSent -> {
                requireBinding().messageTextBox.setText("")
            }
        }
    }

    private fun renderErrorSnackBar() {
        Snackbar.make(
            requireView(),
            R.string.error_loading_chats_message,
            Snackbar.LENGTH_INDEFINITE
        ).setAction(R.string.retry){
            viewModel.onViewCreated(args.threadId)
        }.show()
    }

    private fun renderMessageErrorSnackBar() {
        Snackbar.make(
            requireView(),
            R.string.error_sending_message,
            Snackbar.LENGTH_LONG
        ).show()
    }
}