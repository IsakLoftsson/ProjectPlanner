package `is`.hi.hbv601g.projectplanner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import `is`.hi.hbv601g.projectplanner.data.Comment

class CommentsAdapter() : ListAdapter<Comment, CommentsAdapter.CommentsViewHolder>(CommentsDiffCallback){
    class CommentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val commenterName: TextView = itemView.findViewById(R.id.commenter)
        private val commentText: TextView = itemView.findViewById(R.id.comment_text)
        private var currentComment: Comment? = null

        fun bind(comment: Comment) {
            currentComment = comment
            commenterName.text = comment.commenter
            commentText.text = comment.text
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

object CommentsDiffCallback : DiffUtil.ItemCallback<Comment>() {
    override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem.id == newItem.id
    }
}