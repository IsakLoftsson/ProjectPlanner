package `is`.hi.hbv601g.projectplanner

import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.r0adkll.slidr.Slidr
import `is`.hi.hbv601g.projectplanner.data.GroupMembers

class GroupMembersActivity : FragmentActivity(), AddGroupMembersDialogFragment.AddGroupMembersDialogListener {
    private val projectPlannerViewModel = ProjectPlannerViewModel()
    var currentProjectId: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_members)
        Slidr.attach(this);

        val groupMembersAdapter = GroupMembersAdapter()
        val groupMembersList: RecyclerView = findViewById(R.id.group_members_list)
        groupMembersList.adapter = groupMembersAdapter
        val mAddGroupMembersListButton = findViewById<Button>(R.id.add_group_member)
        mAddGroupMembersListButton.isEnabled = true
        mAddGroupMembersListButton.isClickable = true

        mAddGroupMembersListButton.setOnClickListener {
            showAddGroupMembersDialog()
        }


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

    private fun showAddGroupMembersDialog() {
        val dialog = AddGroupMembersDialogFragment()
        dialog.show(supportFragmentManager,"AddGroupMembersDialogFragment")
    }

    override fun onDialogPositiveClick(email: String) {
        val user = projectPlannerViewModel.getUsersByEmail(email)
        if (user == null) {

        } else {
            projectPlannerViewModel.addGroupMember(user.id,user.name,user.email,currentProjectId!!)
        }
    }
}