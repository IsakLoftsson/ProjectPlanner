package `is`.hi.hbv601g.projectplanner

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import `is`.hi.hbv601g.projectplanner.data.AppUser
import com.google.android.material.snackbar.Snackbar
import com.r0adkll.slidr.Slidr
import `is`.hi.hbv601g.projectplanner.data.Project
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

        val groupMembersAdapter = GroupMembersAdapter {appUser -> adapterOnClick(appUser)}
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

    private fun adapterOnClick(appUser: AppUser) {
        //val intent = Intent(Intent.ACTION_INSERT)
        val intent = Intent(ContactsContract.Intents.Insert.ACTION)
        intent.setType(ContactsContract.RawContacts.CONTENT_TYPE)
        //intent.putExtra("id",appUser.id)
        //intent.putExtra("firstName",appUser.firstName)
        //intent.putExtra("lastName",appUser.lastName)
        //intent.putExtra("lastName",appUser.email)
        //intent.putExtra(ContactsContract.Intents.Insert.NAME, appUser.firstName.getText().toString());

        intent.putExtra(ContactsContract.Intents.Insert.NAME, appUser.firstName+ " " +appUser.lastName)
        intent.putExtra(ContactsContract.Intents.Insert.PHONE, appUser.phoneNumber.toString())
        intent.putExtra(ContactsContract.Intents.Insert.EMAIL, appUser.email)
        startActivity(intent)
        //else{
        //    Toast.makeText(MainActivity.this, "There is no app that support this action", Toast.LENGTH_SHORT).show();
        //}
    }

    override fun onDialogPositiveClick(email: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val user = viewModel.getUserByEmail(email)
            if (user != null){
                viewModel.addProjectMember(user.id,currentProjectId!!)
            }
        }
    }
}