package `is`.hi.hbv601g.projectplanner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import `is`.hi.hbv601g.projectplanner.data.Project

class ProjectAdapter(private val onClick: (Project) -> Unit) : ListAdapter<Project, ProjectAdapter.ProjectViewHolder>(ProjectDiffCallback){
    class ProjectViewHolder(itemView: View, val onClick: (Project) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val projectTitle: TextView = itemView.findViewById(R.id.project_title)
        private var currentProject: Project? = null

        init {
            itemView.setOnClickListener {
                currentProject?.let {
                    onClick(it)
                }
            }
        }

        fun bind(project: Project) {
            currentProject = project
            projectTitle.text = project.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ProjectViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.project_item,parent,false)
        return ProjectViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        val project = getItem(position)
        holder.bind(project)
    }
}

object ProjectDiffCallback : DiffUtil.ItemCallback<Project>() {
    override fun areItemsTheSame(oldItem: Project, newItem: Project): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Project, newItem: Project): Boolean {
        return oldItem.id == newItem.id
    }
}