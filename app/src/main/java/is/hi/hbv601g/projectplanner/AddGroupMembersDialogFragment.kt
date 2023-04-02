package `is`.hi.hbv601g.projectplanner

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class AddGroupMembersDialogFragment : DialogFragment() {

    internal lateinit var listener: AddGroupMembersDialogListener

    interface AddGroupMembersDialogListener {
        fun onDialogPositiveClick(email: String)
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.dialog_add_group_member, null)
            //val cancelButton = view.findViewById<Button>(R.id.cancel_button)
            val okButton = view.findViewById<Button>(R.id.ok_button)

            /*
            cancelButton.setOnClickListener {
                this.dismiss()
            }
            */

            okButton.setOnClickListener {
                val email = view.findViewById<EditText>(R.id.group_member_email)
                if (email.getText().toString().isEmpty()) {
                    email.setError("This field can not be blank");
                }
                if (email.text.toString().isNotEmpty()) {
                    val checkEmail = email.text.toString()
                    if (isValidEmail(checkEmail) == true) {
                        listener.onDialogPositiveClick(email.text.toString())
                        this.dismiss()
                    } else {
                        email.setError("Email is not valid");
                    }
                }
            }
            builder.setView(view)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as AddGroupMembersDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(("$context must implement AddGroupMembersDialogListener"))
        }
    }

    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"
    fun isValidEmail(email: String): Boolean {
        return email.matches(emailRegex.toRegex())
    }
}