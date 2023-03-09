package `is`.hi.hbv601g.projectplanner

import androidx.lifecycle.ViewModel
import `is`.hi.hbv601g.projectplanner.data.Datasource

class ProjectPlannerViewModel : ViewModel() {
    private val datasource = Datasource()
    val projectsLiveData = datasource.getProjectList()
}