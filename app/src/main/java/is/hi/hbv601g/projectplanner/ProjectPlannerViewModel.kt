package `is`.hi.hbv601g.projectplanner

import android.app.Application
import androidx.lifecycle.*
import `is`.hi.hbv601g.projectplanner.data.*
import kotlin.random.Random

class ProjectPlannerViewModel(application: Application) : AndroidViewModel(application) {
    private val datasource = Datasource()
    private val projectPlannerRepository = ProjectPlannerRepository(getDatabase(application))
    val projectsLiveData = projectPlannerRepository.projectsLiveData
    val tasksLiveData = projectPlannerRepository.tasksLiveData
    val groupMembersLiveData = datasource.getGroupMembersList()

    fun getProjectsByUserId(id:Long) {
        projectPlannerRepository.setProjects(id)
    }

    fun getTasksByProjectId(id:Long) {
        projectPlannerRepository.setTasks(id)
    }

    fun addProject(ownerId: Long,title: String,description: String) {

        val newProject = Project(
            Random.nextLong(),
            ownerId,
            title,
            description
        )

        projectPlannerRepository.addProject(newProject)
    }

    fun getProject(id:Long): Project? {
        println(datasource.getProject(id)?.id)
        return datasource.getProject(id)
    }

    fun getTask(id:Long): Task? {
        return datasource.getTask(id)
    }

    fun addTask(projectId: Long, name: String) {
        val newTask = Task(
            Random.nextLong(),
            projectId,
            name
        )
        println(projectId)
        println(name)
        projectPlannerRepository.addTask(newTask)
    }

    fun getGroupMembersByProjectId(id:Long): LiveData<List<GroupMembers>> {
        val filteredList = groupMembersLiveData.value?.filter{ groupMembers -> groupMembers.projectId == id}
        return MutableLiveData(filteredList)
    }

    class Factory(val app: Application): ViewModelProvider.Factory {
        override fun <T: ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ProjectPlannerViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ProjectPlannerViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}