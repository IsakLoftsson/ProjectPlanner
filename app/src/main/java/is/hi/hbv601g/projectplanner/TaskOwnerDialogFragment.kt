package `is`.hi.hbv601g.projectplanner

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import `is`.hi.hbv601g.projectplanner.data.AppUser

class TaskOwnerDialogFragment(val projectId: Long?) : DialogFragment() {

    private lateinit var listener: TaskOwnerDialogListener
    private val viewModel: ProjectPlannerViewModel by lazy {
        ViewModelProvider(this,ProjectPlannerViewModel.Factory(requireActivity().application)).get(ProjectPlannerViewModel::class.java)
    }

    interface TaskOwnerDialogListener {
        fun onTaskOwnerDialogPositiveClick(owner: AppUser)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.dialog_edit_task_owner,null)
            val okButton = view.findViewById<Button>(R.id.ok_button)

            viewModel.getGroupMembersByProjectId(projectId!!)
            val taskOwnerSpinner: Spinner = view.findViewById<Spinner>(R.id.task_owner)
            viewModel.groupMembersLiveData.observe(this, {
                it?.let {
                    val spinnerAdapter = GroupMembersSpinnerAdapter(requireActivity(),R.layout.task_item,it as MutableList<AppUser>)
                    taskOwnerSpinner.adapter = spinnerAdapter
                }
            })


            okButton.setOnClickListener {
                val owner = taskOwnerSpinner.selectedItem
                listener.onTaskOwnerDialogPositiveClick(owner as AppUser)
                this.dismiss()
            }
            builder.setView(view)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as TaskOwnerDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(("$context must implement TaskOwnerDialogListener"))
        }
    }
}

