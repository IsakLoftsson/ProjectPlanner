package `is`.hi.hbv601g.projectplanner

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import `is`.hi.hbv601g.projectplanner.data.Project

class ProjectActivity : AppCompatActivity() {

    private val projectPlannerViewModel = ProjectPlannerViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_projects)

        val projectAdapter = ProjectAdapter()

        val mProjectsRecyclerView = findViewById<RecyclerView>(R.id.project_list)
        mProjectsRecyclerView.adapter = projectAdapter

        projectPlannerViewModel.projectsLiveData.observe(this, {
            it?.let {
                projectAdapter.submitList(it as MutableList<Project>)
            }
        })
    }
}