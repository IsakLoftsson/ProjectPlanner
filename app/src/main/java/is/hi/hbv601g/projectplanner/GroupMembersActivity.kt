package `is`.hi.hbv601g.projectplanner

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.r0adkll.slidr.Slidr
import `is`.hi.hbv601g.projectplanner.data.GroupMembers
import `is`.hi.hbv601g.projectplanner.data.Task

class GroupMembersActivity : FragmentActivity() {
    private val projectPlannerViewModel = ProjectPlannerViewModel()
    var currentProjectId: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_members)
        Slidr.attach(this);

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
            projectPlannerViewModel.getGroupMembersByProjectId(it).observe(this) {
                it?.let {
                    groupMembersAdapter.submitList(it as MutableList<GroupMembers>)
                }
            }
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