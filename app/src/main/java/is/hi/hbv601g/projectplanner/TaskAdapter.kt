package `is`.hi.hbv601g.projectplanner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import `is`.hi.hbv601g.projectplanner.data.Task

class TaskAdapter() : ListAdapter<Task, TaskAdapter.TaskViewHolder>(TaskDiffCallback){
    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val taskName: TextView = itemView.findViewById(R.id.task_name)
        private var currentTask: Task? = null

        fun bind(task: Task) {
            currentTask = task
            taskName.text = task.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : TaskAdapter.TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item,parent,false)
        return TaskAdapter.TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskAdapter.TaskViewHolder, position: Int) {
        val task = getItem(position)
        holder.bind(task)
    }
}

object TaskDiffCallback : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.id == newItem.id
    }
}