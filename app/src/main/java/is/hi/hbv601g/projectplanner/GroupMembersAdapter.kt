package `is`.hi.hbv601g.projectplanner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import `is`.hi.hbv601g.projectplanner.data.AppUser

class GroupMembersAdapter(private val onClick: (AppUser) -> Unit) : ListAdapter<AppUser, GroupMembersAdapter.GroupMembersViewHolder>(GroupMembersDiffCallback) {
    class GroupMembersViewHolder(itemView: View, val onClick: (AppUser) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val groupMembersName: TextView = itemView.findViewById(R.id.task_name)
        private var currentGroupMembers: AppUser? = null

        init {
            itemView.setOnClickListener {
                currentGroupMembers?.let {
                    onClick(it)
                }
            }
        }

        fun bind(groupMembers: AppUser) {
            currentGroupMembers = groupMembers
            val text = groupMembers.firstName + " " + groupMembers.lastName
            groupMembersName.text = text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : GroupMembersAdapter.GroupMembersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item,parent,false)
        return GroupMembersAdapter.GroupMembersViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: GroupMembersAdapter.GroupMembersViewHolder, position: Int) {
        val groupMembers = getItem(position)
        holder.bind(groupMembers)
    }
}

object GroupMembersDiffCallback : DiffUtil.ItemCallback<AppUser>() {
    override fun areItemsTheSame(oldItem: AppUser, newItem: AppUser): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: AppUser, newItem: AppUser): Boolean {
        return oldItem.id == newItem.id
    }
}