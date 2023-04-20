package `is`.hi.hbv601g.projectplanner

import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import `is`.hi.hbv601g.projectplanner.data.AppUser
import com.google.android.material.snackbar.Snackbar
import com.r0adkll.slidr.Slidr
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GroupMembersActivity : FragmentActivity(), AddGroupMembersDialogFragment.AddGroupMembersDialogListener {
    private val viewModel: ProjectPlannerViewModel by lazy {
        ViewModelProvider(this,ProjectPlannerViewModel.Factory(this.application)).get(ProjectPlannerViewModel::class.java)
    }
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
            viewModel.getGroupMembersByProjectId(currentProjectId!!)
            viewModel.groupMembersLiveData.observe(this, {
                it?.let {
                    groupMembersAdapter.submitList(it as MutableList<AppUser>)
                }
            })
        }


    }

    private fun showAddGroupMembersDialog() {
        val dialog = AddGroupMembersDialogFragment()
        dialog.show(supportFragmentManager,"AddGroupMembersDialogFragment")
    }

    override fun onDialogPositiveClick(email: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val user = viewModel.getUserByEmail(email)
            if (user != null){
                viewModel.addProjectMember(email,currentProjectId!!)
            }
        }
    }
}