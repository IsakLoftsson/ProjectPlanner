package `is`.hi.hbv601g.projectplanner

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class TaskOwnerDialogFragment(val projectId: Long?) : DialogFragment() {

    private lateinit var listener: TaskOwnerDialogListener
    private val projectPlannerViewModel = ProjectPlannerViewModel()

    interface TaskOwnerDialogListener {
        fun onTaskOwnerDialogPositiveClick(ownerId: Long)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.dialog_edit_task_owner,null)
            val okButton = view.findViewById<Button>(R.id.ok_button)


            val taskOwnerSpinner: Spinner = view.findViewById<Spinner>(R.id.task_owner)
            var task_owner_list: ArrayList<String> = projectPlannerViewModel.getGroupMembersNameByProjectId(projectId)
            println(task_owner_list.joinToString(" "))

            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                task_owner_list
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                taskOwnerSpinner.adapter = adapter
            }

            okButton.setOnClickListener {
                //val owner = view.findViewById<Spinner>(R.id.task_owner)
                //val ownerString = owner.selectedItem.toString()
                //listener.onTaskOwnerDialogPositiveClick(taskOwnerSpinner.getSelectedItem().toString())
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

