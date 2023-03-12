package `is`.hi.hbv601g.projectplanner

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import `is`.hi.hbv601g.projectplanner.data.Project
import `is`.hi.hbv601g.projectplanner.data.Task

class ProjectViewActivity : FragmentActivity(), CreateTaskDialogFragment.CreateTaskDialogListener {
    private val projectPlannerViewModel = ProjectPlannerViewModel()
    var currentProjectId: Long? = null
    var currentProjectTitle: String? = null
    var currentProjectDescription: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_view)

        val taskAdapter = TaskAdapter()

        val projectTitle: TextView = findViewById(R.id.project_title)
        val projectDescription: TextView = findViewById(R.id.project_description)
        val taskList: RecyclerView = findViewById(R.id.task_list)
        taskList.adapter = taskAdapter
        val mCreateTaskButton = findViewById<Button>(R.id.create_task)
        mCreateTaskButton.isEnabled = true
        mCreateTaskButton.isClickable = true

        mCreateTaskButton.setOnClickListener {
            showCreateTaskDialog()
        }


        val bundle: Bundle? = intent.extras

        if (bundle != null) {
            currentProjectId = bundle.getLong("id")
            currentProjectTitle = bundle.getString("title")
            currentProjectDescription = bundle.getString("description")
        }

        currentProjectId?.let {
            projectTitle.text = currentProjectTitle
            projectDescription.text = currentProjectDescription

            projectPlannerViewModel.getTasksByProjectId(it).observe(this, {
                it?.let {
                    taskAdapter.submitList(it as MutableList<Task>)
                }
            })
        }


    }

    private fun showCreateTaskDialog() {
        val dialog = CreateTaskDialogFragment()
        dialog.show(supportFragmentManager,"CreateProjectDialogFragment")
    }

    override fun onDialogPositiveClick(name: String) {
        currentProjectId?.let { projectPlannerViewModel.addTask(it,name) }
    }
}