package `is`.hi.hbv601g.projectplanner

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.r0adkll.slidr.Slidr
import `is`.hi.hbv601g.projectplanner.data.Project

class ProjectActivity : FragmentActivity(), CreateProjectDialogFragment.CreateProjectDialogListener {

    private val projectPlannerViewModel = ProjectPlannerViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_projects)
        Slidr.attach(this);

        val projectAdapter = ProjectAdapter {project -> adapterOnClick(project)}

        val mProjectsRecyclerView = findViewById<RecyclerView>(R.id.project_list)
        mProjectsRecyclerView.adapter = projectAdapter
        val mCreateProjectButton = findViewById<Button>(R.id.create_project)
        mCreateProjectButton.isEnabled = true
        mCreateProjectButton.isClickable = true

        mCreateProjectButton.setOnClickListener {
            showCreateProjectDialog()
        }

        projectPlannerViewModel.projectsLiveData.observe(this, {
            it?.let {
                projectAdapter.submitList(it as MutableList<Project>)
            }
        })
    }

    private fun adapterOnClick(project: Project) {
        val intent = Intent(this,ProjectViewActivity()::class.java)
        intent.putExtra("id",project.id)
        intent.putExtra("title",project.title)
        intent.putExtra("description",project.description)
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
    }

    private fun showCreateProjectDialog() {
        val dialog = CreateProjectDialogFragment()
        dialog.show(supportFragmentManager,"CreateProjectDialogFragment")
    }

    override fun onDialogPositiveClick(title: String, description: String) {
        projectPlannerViewModel.addProject(1,title,description)
    }
}