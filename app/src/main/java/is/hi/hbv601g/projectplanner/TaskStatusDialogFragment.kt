package `is`.hi.hbv601g.projectplanner

import android.app.Dialog
import android.content.Context
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class TaskStatusDialogFragment : DialogFragment() {

    private lateinit var listener: TaskStatusDialogListener

    interface TaskStatusDialogListener {
        fun onTaskStatusDialogPositiveClick(status: String)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.dialog_edit_status,null)
            val okButton = view.findViewById<Button>(R.id.ok_button)
            val taskStatusSpinner: Spinner = view.findViewById<Spinner>(R.id.task_status)

            ArrayAdapter.createFromResource(
                requireContext(),
                R.array.task_status,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                taskStatusSpinner.adapter = adapter
            }

            okButton.setOnClickListener {
                val status = view.findViewById<Spinner>(R.id.task_status)
                val statusString = status.selectedItem.toString()
                listener.onTaskStatusDialogPositiveClick(statusString)
                this.dismiss()
            }
            builder.setView(view)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as TaskStatusDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(("$context must implement TaskStatusDialogListener"))
        }
    }
}