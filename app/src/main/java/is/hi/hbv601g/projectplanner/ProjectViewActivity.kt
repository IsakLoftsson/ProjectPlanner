package `is`.hi.hbv601g.projectplanner

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.r0adkll.slidr.Slidr
import `is`.hi.hbv601g.projectplanner.data.Task

class ProjectViewActivity : FragmentActivity(), CreateTaskDialogFragment.CreateTaskDialogListener {
    private val viewModel: ProjectPlannerViewModel by lazy {
        ViewModelProvider(this,ProjectPlannerViewModel.Factory(this.application)).get(ProjectPlannerViewModel::class.java)
    }
    var currentProjectId: Long? = null
    var currentProjectTitle: String? = null
    var currentProjectDescription: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_view)
        Slidr.attach(this);

        val taskAdapter = TaskAdapter {task -> adapterOnClick(task)}

        val projectTitle: TextView = findViewById(R.id.project_title)
        val projectDescription: TextView = findViewById(R.id.project_description)

        val GroupMembersTextView = findViewById<TextView>(R.id.group_members)
        val groupMembers = findViewById<TextView>(R.id.group_members)
        groupMembers.setOnClickListener {
            val myIntent = Intent(this, GroupMembersActivity::class.java)
            myIntent.putExtra("id", currentProjectId)
            startActivity(myIntent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        }

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

        GroupMembersTextView.setOnClickListener {
            val myIntent = Intent(this, GroupMembersActivity::class.java)
            myIntent.putExtra("id", currentProjectId)
            startActivity(myIntent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        }


        currentProjectId?.let {
            viewModel.getTasksByProjectId(currentProjectId!!)
            projectTitle.text = currentProjectTitle
            projectDescription.text = currentProjectDescription

            viewModel.tasksLiveData.observe(this, {
                it?.let {
                    taskAdapter.submitList(it as MutableList<Task>)
                }
            })
        }
    }

    private fun adapterOnClick(task: Task) {
        val intent = Intent(this,TaskActivity()::class.java)
        println("Clicked: "+task.id)
        intent.putExtra("projectId", task.projectId)
        intent.putExtra("id",task.id)
        intent.putExtra("name",task.name)
        intent.putExtra("description",task.description)
        intent.putExtra("deadline",task.deadline)
        intent.putExtra("ownerId",task.ownerId)
        intent.putExtra("status",task.status)
        startActivity(intent)
    }

    private fun showCreateTaskDialog() {
        val dialog = CreateTaskDialogFragment()
        dialog.show(supportFragmentManager,"CreateProjectDialogFragment")
    }

    override fun onDialogPositiveClick(name: String, description: String, deadline: String, status: String) {
        currentProjectId?.let { viewModel.addTask(it,name, description, deadline, 2, status) }
    }
}