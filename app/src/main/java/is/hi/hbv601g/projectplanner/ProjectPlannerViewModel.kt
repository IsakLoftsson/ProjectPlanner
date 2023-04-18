package `is`.hi.hbv601g.projectplanner

import android.app.Application
import androidx.lifecycle.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import `is`.hi.hbv601g.projectplanner.data.*
import kotlin.random.Random

class ProjectPlannerViewModel(application: Application) : AndroidViewModel(application) {
    private val projectPlannerRepository = ProjectPlannerRepository(getDatabase(application))
    val projectsLiveData = projectPlannerRepository.projectsLiveData
    val tasksLiveData = projectPlannerRepository.tasksLiveData
    val groupMembersLiveData = projectPlannerRepository.groupMembersLiveData
    val commentsLiveData = projectPlannerRepository.commentsLiveData
    val curTaskLiveData = projectPlannerRepository.curTaskLiveData
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

    fun getCommentsByTaskId(id:Long) {
        projectPlannerRepository.setComments(id)
    }

    fun getCurTask(id:Long) {
        projectPlannerRepository.setCurTask(id)
    }

    suspend fun getUserById(id:Long): AppUser? {
        return projectPlannerRepository.getUserById(id)
    }

    suspend fun getUserByEmail(email: String): AppUser? {
        return projectPlannerRepository.getUserByEmail(email)
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

    fun addTask(projectId: Long, name: String, description: String, deadline: String, ownerId: Long, status: String) {
        val newTask = Task(
            Random.nextLong(),
            projectId,
            name,
            description,
            deadline,
            ownerId,
            status
        )
        println("------------ ADD TASK ---------------")
        println("projectId: " + projectId)
        println("name: " + name)
        println("description: " + description)
        println("deadline: " + deadline)
        println("ownerId: " + ownerId)
        println("status: " + status)
        projectPlannerRepository.addTask(newTask)
    }

    fun editTask(taskId: Long, projectId: Long, name: String, description: String, deadline: String, ownerId: Long, status: String) {
        val newTask = Task(
            taskId,
            projectId,
            name,
            description,
            deadline,
            ownerId,
            status
        )
        println("------------ EDIT TASK ---------------")
        println("taskID: "+taskId)
        println("projectId: "+projectId)
        println("name: "+name)
        println("description: "+description)
        println("deadline: "+deadline)
        println("ownerId: "+ownerId)
        println("status: "+status)
        projectPlannerRepository.addTask(newTask)
    }

    fun addProjectMember(userId: Long, projectId: Long) {
        val newProjectMember = ProjectMembers(
            Random.nextLong(),
            userId,
            projectId
        )
        projectPlannerRepository.addProjectMember(newProjectMember)
    }

    fun registerUser(firstName: String, lastName: String, email: String, password: String, phoneNumber: Int) {
        val newUser = AppUser(
            Random.nextLong(),
            firstName,
            lastName,
            email,
            password,
            phoneNumber
        )
        projectPlannerRepository.registerUser(newUser)
    }

    suspend fun loginUser(email: String, password: String): AppUser? {
        return projectPlannerRepository.loginUser(email,password)
    }


    fun successLogin() {
        loginLiveData.postValue(true)
    }

    fun addComment(taskId: Long, commenter: String, text: String) {
        val newComment = Comment(
            Random.nextLong(),
            taskId,
            commenter,
            text
        )
        println("------------ ADD COMMENT ---------------")
        println(commenter)
        println(text)
        projectPlannerRepository.addComment(newComment)
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