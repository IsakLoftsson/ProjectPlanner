package `is`.hi.hbv601g.projectplanner

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.r0adkll.slidr.Slidr
import `is`.hi.hbv601g.projectplanner.data.Project

class ProjectActivity : FragmentActivity(), CreateProjectDialogFragment.CreateProjectDialogListener {

    private val viewModel: ProjectPlannerViewModel by lazy {
        ViewModelProvider(this,ProjectPlannerViewModel.Factory(this.application)).get(ProjectPlannerViewModel::class.java)
    }
    private var currentUserId: Long = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_projects)
        Slidr.attach(this);

        val sharedPref = getSharedPreferences("prefs",Context.MODE_PRIVATE)
        currentUserId = sharedPref.getLong("userId",1)

        val projectAdapter = ProjectAdapter {project -> adapterOnClick(project)}

        val mProjectsRecyclerView = findViewById<RecyclerView>(R.id.project_list)
        mProjectsRecyclerView.adapter = projectAdapter
        val mCreateProjectButton = findViewById<Button>(R.id.create_project)
        mCreateProjectButton.isEnabled = true
        mCreateProjectButton.isClickable = true

        mCreateProjectButton.setOnClickListener {
            showCreateProjectDialog()
        }

        viewModel.getProjectsByUserId(currentUserId)

        viewModel.projectsLiveData.observe(this) {
            it?.let {
                projectAdapter.submitList(it as MutableList<Project>)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val sharedPref = this.getSharedPreferences("prefs",Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.clear()
        editor.apply()
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
        viewModel.addProject(currentUserId,title,description)
    }
}