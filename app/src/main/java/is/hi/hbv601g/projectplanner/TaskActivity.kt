package `is`.hi.hbv601g.projectplanner

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.r0adkll.slidr.Slidr
import `is`.hi.hbv601g.projectplanner.data.AppUser
import `is`.hi.hbv601g.projectplanner.data.Comment
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

        val commentEdit: EditText = findViewById(R.id.comment)
        val commentSubmit: Button = findViewById(R.id.submit_comment)

        commentSubmit.setOnClickListener {
            val sharedPref = this.getSharedPreferences("prefs", Context.MODE_PRIVATE)
            val commenter = sharedPref.getString("userFirstName","Anonymous")
            val comment = commentEdit.text.toString()
            if (comment.isNotEmpty()) {
                viewModel.addComment(currentTaskId!!,commenter!!,comment)
                commentEdit.text.clear()
            }
            else {
                commentEdit.error = "Cannot have empty comment"
            }
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

        viewModel.getCommentsByTaskId(currentTaskId!!)
        viewModel.commentsLiveData.observe(this, {
            it?.let {
                commentsAdapter.submitList(it as MutableList<Comment>)
            }
        })

        println("Whatever: "+currentTaskId)
        viewModel.getCurTask(currentTaskId!!)
        viewModel.curTaskLiveData.observe(this, {
            currentTaskName = it.name
            currentTaskDescription = it.description
            currentTaskDeadline = it.deadline
            currentTaskOwner = it.ownerId
            currentTaskStatus = it.status
            taskName.text = it.name
            taskDescription.text = it.description
            taskDeadline.text = it.deadline
            taskStatus.text = it.status
            CoroutineScope(Dispatchers.IO).launch {
                val owner = viewModel.getUserById(it.ownerId)
                val ownerName = (owner?.firstName ?:"Not Assigned")+" "+(owner?.lastName ?: "")
                withContext(Dispatchers.Main) {
                    taskOwner.text = ownerName
                }
            }
        })
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