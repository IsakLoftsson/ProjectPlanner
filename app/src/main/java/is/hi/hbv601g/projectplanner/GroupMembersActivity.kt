package `is`.hi.hbv601g.projectplanner

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import `is`.hi.hbv601g.projectplanner.data.AppUser
import `is`.hi.hbv601g.projectplanner.data.GroupMembers
import `is`.hi.hbv601g.projectplanner.data.Task

class GroupMembersActivity : FragmentActivity() {
    private val viewModel: ProjectPlannerViewModel by lazy {
        ViewModelProvider(this,ProjectPlannerViewModel.Factory(this.application)).get(ProjectPlannerViewModel::class.java)
    }
    var currentProjectId: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_members)
        val groupMembersAdapter = GroupMembersAdapter()
        val groupMembersList: RecyclerView = findViewById(R.id.group_members_list)
        groupMembersList.adapter = groupMembersAdapter
        val mCreateGroupMembersListButton = findViewById<Button>(R.id.add_group_member)
        mCreateGroupMembersListButton.isEnabled = true
        mCreateGroupMembersListButton.isClickable = true

        /*
        mCreateGroupMembersListButton.setOnClickListener {
            showCreateGroupMembersDialog()
        }
         */


        val bundle: Bundle? = intent.extras


        if (bundle != null) {
            currentProjectId = bundle.getLong("id")
        }

        currentProjectId?.let {
            viewModel.getGroupMembersByProjectId(currentProjectId!!)
            viewModel.groupMembersLiveData.observe(this, {
                it?.let {
                    groupMembersAdapter.submitList(it as MutableList<AppUser>)
                }
            })
        }


    }
/*
    private fun showCreateGroupMembersDialog() {
        val dialog = CreateGroupMembersDialogFragment()
        dialog.show(supportFragmentManager,"CreateProjectDialogFragment")
    }

    override fun onDialogPositiveClick(name: String) {
        currentProjectId?.let { projectPlannerViewModel.addTask(it,name) }
    }
     */
}