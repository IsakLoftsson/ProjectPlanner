package `is`.hi.hbv601g.projectplanner

import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.r0adkll.slidr.Slidr
import `is`.hi.hbv601g.projectplanner.data.AppUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TaskActivity : FragmentActivity(), TaskDeadlineDialogFragment.TaskDeadlineDialogListener,
    TaskStatusDialogFragment.TaskStatusDialogListener, TaskOwnerDialogFragment.TaskOwnerDialogListener {
    private val viewModel: ProjectPlannerViewModel by lazy {
        ViewModelProvider(this,ProjectPlannerViewModel.Factory(this.application)).get(ProjectPlannerViewModel::class.java)
    }
    var currentProjectId: Long? = null
    var currentTaskId: Long? = null
    var currentTaskName: String? = null
    var currentTaskDescription: String? = null
    var currentTaskDeadline: String? = null
    var currentTaskOwner: Long? = null
    var currentTaskStatus: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)
        Slidr.attach(this)

        val taskName: TextView = findViewById(R.id.task_name)
        val taskDescription: TextView = findViewById(R.id.task_description)
        val taskDeadline: TextView = findViewById(R.id.task_deadline)
        val taskOwner: TextView = findViewById(R.id.task_owner)
        val taskStatus: TextView = findViewById(R.id.task_status)

        taskDeadline.setOnClickListener {
            showTaskDeadlineDialogFragment()
        }

        taskStatus.setOnClickListener {
            showTaskStatusDialogFragment()
        }

        taskOwner.setOnClickListener {
            showTaskOwnerDialogFragment()
        }

        val commentsAdapter = CommentsAdapter()

        val commentsList: RecyclerView = findViewById(R.id.comments_list)
        commentsList.adapter = commentsAdapter

        val bundle: Bundle? = intent.extras

        if (bundle != null) {
            currentTaskId = bundle.getLong("id")
            currentProjectId = bundle.getLong("projectId")
            currentTaskName = bundle.getString("name")
            currentTaskDescription = bundle.getString("description")
            currentTaskDeadline = bundle.getString("deadline")
            currentTaskOwner = bundle.getLong("ownerId")
            currentTaskStatus = bundle.getString("status")
        }

        println("Whatever: "+currentTaskId)

        currentTaskId?.let {
            CoroutineScope(Dispatchers.IO).launch {
                println("Setting task texts")
                val owner = currentTaskOwner?.let { it1 -> viewModel.getUserById(it1) }
                val ownerName = (owner?.firstName ?: "John") +" "+ (owner?.lastName ?: "Doe")
                withContext(Dispatchers.Main) {
                    taskName.text = currentTaskName
                    taskDescription.text = currentTaskDescription
                    taskDeadline.text = currentTaskDeadline
                    taskOwner.text = ownerName
                    taskStatus.text = currentTaskStatus
                }
            }
        }
    }

    private fun showTaskDeadlineDialogFragment() {
        val dialog = TaskDeadlineDialogFragment()
        dialog.show(supportFragmentManager, "TaskDeadlineDialogFragment")
    }

    private fun showTaskStatusDialogFragment() {
        val dialog = TaskStatusDialogFragment()
        dialog.show(supportFragmentManager, "TaskStatusDialogFragment")
    }

    private fun showTaskOwnerDialogFragment() {
        val dialog = TaskOwnerDialogFragment(currentProjectId)
        dialog.show(supportFragmentManager, "TaskOwnerDialogFragment")
    }

    override fun onTaskDeadlineDialogPositiveClick(deadline: String) {
        currentTaskId?.let { viewModel.editTask(currentTaskId!!,currentProjectId!!,currentTaskName.toString(), currentTaskDescription.toString(), deadline, currentTaskOwner!!, currentTaskStatus.toString()) }
    }

    override fun onTaskStatusDialogPositiveClick(status: String) {
        currentTaskId?.let { viewModel.editTask(currentTaskId!!,currentProjectId!!,currentTaskName.toString(), currentTaskDescription.toString(), currentTaskDeadline.toString(), currentTaskOwner!!, status) }
    }

    override fun onTaskOwnerDialogPositiveClick(owner: AppUser) {
        currentTaskId?.let { viewModel.editTask(currentTaskId!!,currentProjectId!!,currentTaskName.toString(), currentTaskDescription.toString(), currentTaskDeadline.toString(), owner.id, currentTaskStatus.toString()) }
    }
}