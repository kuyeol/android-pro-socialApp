package com.ung.feedback.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ung.feedback.R
import com.ung.feedback.model.Post
import com.ung.feedback.viewmodel.ListAdapter.PostViewHolder

class ListAdapter : ListAdapter<Post, PostViewHolder>(PostS_COMPARATOR) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
    return PostViewHolder.create(parent)
  }

  override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
    val current = getItem(position)
    holder.bind(current.title)
  }

  class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val PostItemView: TextView = itemView.findViewById(R.id.textView)

    fun bind(text: String?) {
      PostItemView.text = text
    }

    companion object {
      fun create(parent: ViewGroup): PostViewHolder {
        val view: View = LayoutInflater.from(parent.context)
          .inflate(R.layout.recyclerview_item, parent, false)
        return PostViewHolder(view)
      }
    }
  }

  companion object {
    private val PostS_COMPARATOR = object : DiffUtil.ItemCallback<Post>() {
      override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem === newItem
      }

      override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.content == newItem.content
      }
    }
  }
}
