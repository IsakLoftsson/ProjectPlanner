package `is`.hi.hbv601g.projectplanner

import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.FragmentActivity

class TaskActivity : FragmentActivity() {

    var currentTaskId: Long? = null
    var currentTaskName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        val taskName: TextView = findViewById(R.id.task_name)


        val bundle: Bundle? = intent.extras

        if (bundle != null) {
            currentTaskId = bundle.getLong("id")
            currentTaskName = bundle.getString("name")
        }

        currentTaskId?.let {
            taskName.text = currentTaskName
        }
    }
}