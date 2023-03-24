package `is`.hi.hbv601g.projectplanner

import android.app.Dialog
import android.content.Context
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment




class CreateTaskDialogFragment : DialogFragment() {

    internal lateinit var listener: CreateTaskDialogListener

    interface CreateTaskDialogListener {
        fun onDialogPositiveClick(name: String, description: String, deadline: String, status: String)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.dialog_create_task,null)
            val cancelButton = view.findViewById<Button>(R.id.cancel_button)
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

            cancelButton.setOnClickListener {
                this.dismiss()
            }

            okButton.setOnClickListener {
                val name = view.findViewById<EditText>(R.id.task_name)
                val description = view.findViewById<EditText>(R.id.task_description)
                val deadline = view.findViewById<DatePicker>(R.id.task_deadline)
                val status = view.findViewById<Spinner>(R.id.task_status)
                val statusString = status.selectedItem.toString()
                val calendar = Calendar.getInstance()
                calendar.set(deadline.year,deadline.month,deadline.dayOfMonth)
                val dateStringFormat = SimpleDateFormat("dd-MM-yyy")
                val dateString = dateStringFormat.format(calendar.time)

                if (name.text.toString().isNotEmpty() and description.text.toString().isNotEmpty()) {
                    listener.onDialogPositiveClick(name.text.toString(),description.text.toString(),dateString,statusString)
                    this.dismiss()
                }
            }
            builder.setView(view)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as CreateTaskDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(("$context must implement CreateTaskDialogListener"))
        }
    }
}