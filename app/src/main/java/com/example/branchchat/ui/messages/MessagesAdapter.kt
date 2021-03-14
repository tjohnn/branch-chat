package com.example.branchchat.ui.messages

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.branchchat.R
import com.example.branchchat.data.model.MessageModel
import com.example.branchchat.data.model.Sender
import com.example.branchchat.utils.toReadableDateTime

class MessagesAdapter : RecyclerView.Adapter<MessagesAdapter.ViewHolder>() {

    var items = listOf<MessageModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_message, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(items[position])
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val titleView by lazy { view.findViewById<TextView>(R.id.title_view) }
        private val subtitleView by lazy { view.findViewById<TextView>(R.id.subtitle_view) }
        private val timeView by lazy { view.findViewById<TextView>(R.id.time_view) }

        fun bindData(item: MessageModel) {
            titleView.text = item.sender.label(itemView.resources)
            subtitleView.text = item.body
            timeView.text = item.timeCreated.toReadableDateTime()
        }
    }
}
