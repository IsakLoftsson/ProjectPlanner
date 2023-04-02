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
    val groupMembersLiveData = projectPlannerRepository.groupMembersLiveData
    val loginLiveData = MutableLiveData<Boolean>(false)

    fun getProjectsByUserId(id:Long) {
        projectPlannerRepository.setProjects(id)
    }

    fun getTasksByProjectId(id:Long) {
        projectPlannerRepository.setTasks(id)
    }

    fun getGroupMembersByProjectId(id:Long) {
        projectPlannerRepository.setGroupMembers(id)
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

    fun registerUser(firstName: String, lastName: String, email: String, password: String) {
        val newUser = AppUser(
            Random.nextLong(),
            firstName,
            lastName,
            email,
            password
        )
        projectPlannerRepository.registerUser(newUser)
    }

    suspend fun loginUser(email: String, password: String): AppUser? {
        return projectPlannerRepository.loginUser(email,password)
    }

    fun successLogin() {
        loginLiveData.postValue(true)
    }

    fun getProject(id:Long): Project? {
        println(datasource.getProject(id)?.id)
        return datasource.getProject(id)
    }

    fun getTask(id:Long): Task? {
        return datasource.getTask(id)
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