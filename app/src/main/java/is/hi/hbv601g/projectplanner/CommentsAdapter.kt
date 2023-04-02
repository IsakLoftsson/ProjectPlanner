package `is`.hi.hbv601g.projectplanner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import `is`.hi.hbv601g.projectplanner.data.Comments

class CommentsAdapter() : ListAdapter<Comments, CommentsAdapter.CommentsViewHolder>(CommentsDiffCallback){
    class CommentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val commentsName: TextView = itemView.findViewById(R.id.comments_name)
        private var currentComments: Comments? = null

        fun bind(comments: Comments) {
            currentComments = comments
            commentsName.text = comments.description

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : CommentsAdapter.CommentsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comments_item,parent,false)
        return CommentsAdapter.CommentsViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentsAdapter.CommentsViewHolder, position: Int) {
        val comments = getItem(position)
        holder.bind(comments)
    }
}

object CommentsDiffCallback : DiffUtil.ItemCallback<Comments>() {
    override fun areItemsTheSame(oldItem: Comments, newItem: Comments): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Comments, newItem: Comments): Boolean {
        return oldItem.id == newItem.id
    }
}