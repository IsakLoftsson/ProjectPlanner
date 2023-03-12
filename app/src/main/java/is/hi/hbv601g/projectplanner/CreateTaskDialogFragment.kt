package `is`.hi.hbv601g.projectplanner

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class CreateTaskDialogFragment : DialogFragment() {

    internal lateinit var listener: CreateTaskDialogListener

    interface CreateTaskDialogListener {
        fun onDialogPositiveClick(name: String)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.dialog_create_task,null)
            val cancelButton = view.findViewById<Button>(R.id.cancel_button)
            val okButton = view.findViewById<Button>(R.id.ok_button)
            cancelButton.setOnClickListener {
                this.dismiss()
            }
            okButton.setOnClickListener {
                val name = view.findViewById<EditText>(R.id.task_name)
                if (name.text.toString().isNotEmpty()) {
                    listener.onDialogPositiveClick(name.text.toString())
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