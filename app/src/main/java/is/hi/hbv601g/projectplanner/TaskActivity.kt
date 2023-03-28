package `is`.hi.hbv601g.projectplanner

import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.r0adkll.slidr.Slidr
import `is`.hi.hbv601g.projectplanner.data.Datasource

class TaskActivity : FragmentActivity(), TaskDeadlineDialogFragment.TaskDeadlineDialogListener,
    TaskStatusDialogFragment.TaskStatusDialogListener, TaskOwnerDialogFragment.TaskOwnerDialogListener {
    private val projectPlannerViewModel = ProjectPlannerViewModel()
    private val datasource = Datasource()
    val groupMembersLiveData = datasource.getGroupMembersList()
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
        Slidr.attach(this);

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

        currentTaskId?.let {
            taskName.text = currentTaskName
            taskDescription.text = currentTaskDescription
            taskDeadline.text = currentTaskDeadline
            taskOwner.text = datasource.getGroupMember(currentTaskOwner)?.name//toString()
            taskStatus.text = currentTaskStatus
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
        currentTaskId?.let { projectPlannerViewModel.editTask(currentTaskId!!,it,currentTaskName.toString(), currentTaskDescription.toString(), deadline, it, currentTaskStatus.toString()) }
    }

    override fun onTaskStatusDialogPositiveClick(status: String) {
        currentTaskId?.let { projectPlannerViewModel.editTask(currentTaskId!!,it,currentTaskName.toString(), currentTaskDescription.toString(), currentTaskDeadline.toString(), it, status) }
    }

    override fun onTaskOwnerDialogPositiveClick(ownerId: Long) {
        currentTaskId?.let { projectPlannerViewModel.editTask(currentTaskId!!,it,currentTaskName.toString(), currentTaskDescription.toString(), currentTaskDeadline.toString(), ownerId, currentTaskStatus.toString()) }
    }
}