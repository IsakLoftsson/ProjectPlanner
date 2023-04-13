package `is`.hi.hbv601g.projectplanner.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.random.Random

class ProjectPlannerRepository(private val database: ProjectPlannerDatabase) {
    val projectsLiveData = MutableLiveData<List<Project>>()
    val tasksLiveData = MutableLiveData<List<Task>>()
    val groupMembersLiveData = MutableLiveData<List<AppUser>>()
    val commentsLiveData = MutableLiveData<List<Comment>>()
    val curTaskLiveData = MutableLiveData<Task>()

    fun addProject(project: Project) {
        CoroutineScope(Dispatchers.IO).launch {
            database.projectDao().insertProject(project)
            database.projectMembersDao().insertProjectMember(ProjectMembers(Random.nextLong(),project.ownerId, project.id))
        }
        val curList = projectsLiveData.value
        if (curList == null) {
            projectsLiveData.postValue(listOf(project))
        }
        else {
            val updList = curList.toMutableList()
            updList.add(0,project)
            projectsLiveData.postValue(updList)
        }
    }

    fun addTask(task: Task) {
        CoroutineScope(Dispatchers.IO).launch {
            database.taskDao().insertTask(task)
            setCurTask(task.id)
        }
        val curList = tasksLiveData.value
        if (curList == null) {
            tasksLiveData.postValue(listOf(task))
        }
        else {
            val updList = curList.toMutableList()
            updList.add(0,task)
            tasksLiveData.postValue(updList)
        }
    }

    fun addProjectMember(projectMembers: ProjectMembers) {
        CoroutineScope(Dispatchers.IO).launch {
            database.projectMembersDao().insertProjectMember(projectMembers)
            setGroupMembers(projectMembers.projectId)
        }
    }

    fun addComment(comment: Comment) {
        CoroutineScope(Dispatchers.IO).launch {
            database.commentDao().insertComment(comment)
        }
        val curList = commentsLiveData.value
        if (curList == null) {
            commentsLiveData.postValue(listOf(comment))
        }
        else {
            val updList = curList.toMutableList()
            updList.add(0,comment)
            commentsLiveData.postValue(updList)
        }
    }

    fun registerUser(user: AppUser) {
        CoroutineScope(Dispatchers.IO).launch {
            database.appUserDao().insertUser(user)
        }
    }

    suspend fun loginUser(email: String, password: String): AppUser? = suspendCoroutine {continuation ->
            val user = database.appUserDao().getByEmailAndPassword(email,password)
            continuation.resume(user)
        }


    fun setTasks(projectId: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            val curList = database.taskDao().getByProjectId(projectId)
            tasksLiveData.postValue(curList)
        }
    }

    fun setCurTask(id: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            val curTask = database.taskDao().getById(id)
            curTaskLiveData.postValue(curTask)
        }
    }

    fun setProjects(userId: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            val listOfIds = database.projectMembersDao().getByUserId(userId)
            val curList = database.projectDao().getAllByIds(listOfIds)
            projectsLiveData.postValue(curList)
        }
    }

    fun setGroupMembers(projectId: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            val listOfIds = database.projectMembersDao().getByProjectId(projectId)
            val curList = database.appUserDao().getAllByIds(listOfIds)
            groupMembersLiveData.postValue(curList)
        }
    }

    fun setComments(taskId: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            val curList = database.commentDao().getByTaskId(taskId)
            commentsLiveData.postValue(curList)
        }
    }

    suspend fun getUserById(id: Long): AppUser? = suspendCoroutine {continuation ->
        val user = database.appUserDao().getById(id)
        continuation.resume(user)
    }

    suspend fun getUserByEmail(email: String): AppUser? = suspendCoroutine {continuation ->
        val user = database.appUserDao().getByEmail(email)
        continuation.resume(user)
    }
}