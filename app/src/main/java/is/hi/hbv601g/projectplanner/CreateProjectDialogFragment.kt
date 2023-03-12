package `is`.hi.hbv601g.projectplanner

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class CreateProjectDialogFragment : DialogFragment() {

    internal lateinit var listener: CreateProjectDialogListener

    interface CreateProjectDialogListener {
        fun onDialogPositiveClick(title: String, description: String)
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.dialog_create_project, null)
            val cancelButton = view.findViewById<Button>(R.id.cancel_button)
            val okButton = view.findViewById<Button>(R.id.ok_button)
            cancelButton.setOnClickListener {
                this.dismiss()
            }
            okButton.setOnClickListener {
                val title = view.findViewById<EditText>(R.id.title)
                val description = view.findViewById<EditText>(R.id.description)
                if (title.text.toString().isNotEmpty()) {
                    listener.onDialogPositiveClick(title.text.toString(),description.text.toString())
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
            listener = context as CreateProjectDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(("$context must implement CreateProjectDialogListener"))
        }
    }
}