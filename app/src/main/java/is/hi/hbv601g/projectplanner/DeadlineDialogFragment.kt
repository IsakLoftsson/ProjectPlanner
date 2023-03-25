package `is`.hi.hbv601g.projectplanner

import android.app.Dialog
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class DeadlineDialogFragment : DialogFragment() {

    internal lateinit var listener: DeadlineDialogListener

    interface DeadlineDialogListener {
        fun onDialogPositiveClick(deadline: String)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.dialog_edit_deadline,null)
            val okButton = view.findViewById<Button>(R.id.ok_button)

            okButton.setOnClickListener {
                val deadline = view.findViewById<DatePicker>(R.id.task_deadline)
                val calendar = Calendar.getInstance()
                calendar.set(deadline.year,deadline.month,deadline.dayOfMonth)
                val dateStringFormat = SimpleDateFormat("dd-MM-yyy")
                val dateString = dateStringFormat.format(calendar.time)
                listener.onDialogPositiveClick(dateString)
                this.dismiss()
            }
            builder.setView(view)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}