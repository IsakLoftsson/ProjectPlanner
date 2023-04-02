package `is`.hi.hbv601g.projectplanner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.LayoutRes
import `is`.hi.hbv601g.projectplanner.data.AppUser

class GroupMembersSpinnerAdapter(context: Context, @LayoutRes private val layoutResource: Int, private val members: List<AppUser>) :
    ArrayAdapter<AppUser>(context, layoutResource, members) {

    override fun getItem(position: Int): AppUser {
        return members[position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createViewFromResource(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createViewFromResource(position, convertView, parent)
    }

    private fun createViewFromResource(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: TextView = convertView as TextView? ?: LayoutInflater.from(context).inflate(layoutResource, parent, false) as TextView
        val name = members[position].firstName+" "+members[position].lastName
        view.text = name
        return view
    }
}