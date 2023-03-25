package `is`.hi.hbv601g.projectplanner

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.r0adkll.slidr.Slidr
import `is`.hi.hbv601g.projectplanner.data.Datasource
import `is`.hi.hbv601g.projectplanner.data.GroupMembers

class TaskActivity : FragmentActivity() {
    private val datasource = Datasource()
    val groupMembersLiveData = datasource.getGroupMembersList()
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
            DeadlineDialogFragment()
        }

        val bundle: Bundle? = intent.extras

        if (bundle != null) {
            currentTaskId = bundle.getLong("id")
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
}