package `is`.hi.hbv601g.projectplanner.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class Datasource {
    private val initialProjectList = loadProjects()
    private val projectsLiveData = MutableLiveData(initialProjectList)

    fun getProjectList(): LiveData<List<Project>> {
        return projectsLiveData
    }
}