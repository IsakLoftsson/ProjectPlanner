package `is`.hi.hbv601g.projectplanner.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import `is`.hi.hbv601g.projectplanner.data.requests.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.random.Random

class ProjectPlannerRepository() {

    val userApi = RetrofitHelper.getInstance().create(AppUserApi::class.java)
    val projectApi = RetrofitHelper.getInstance().create(ProjectApi::class.java)
    val taskApi = RetrofitHelper.getInstance().create(TaskApi::class.java)
    val commentApi = RetrofitHelper.getInstance().create(CommentApi::class.java)
    val projectMembersApi = RetrofitHelper.getInstance().create(ProjectMembersApi::class.java)

    val projectsLiveData = MutableLiveData<List<Project>>()
    val tasksLiveData = MutableLiveData<List<Task>>()
    val groupMembersLiveData = MutableLiveData<List<AppUser>>()
    val commentsLiveData = MutableLiveData<List<Comment>>()
    val curTaskLiveData = MutableLiveData<Task>()

    fun addProject(project: Project) {
        CoroutineScope(Dispatchers.IO).launch {
            projectApi.addProject(AddProjectRequest(project.title,project.description),project.ownerId)
            val newList = projectApi.getProjects(project.ownerId)
            projectsLiveData.postValue(newList.body())
        }
    }

    fun addTask(task: Task) {
        CoroutineScope(Dispatchers.IO).launch {
            taskApi.addTask(AddTaskRequest(task.name,task.description,task.deadline,task.ownerId,task.status),task.projectId)
            val newList = taskApi.getTasks(task.projectId)
            tasksLiveData.postValue(newList.body())
        }
    }

    fun editTask(task: Task) {
        CoroutineScope(Dispatchers.IO).launch {
            taskApi.editTask(task)
            setCurTask(task.id)
        }
    }

    fun addProjectMember(addProjectMemberRequest: AddProjectMemberRequest, projectId: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            projectMembersApi.addProjectMember(addProjectMemberRequest,projectId)
            val newList = projectMembersApi.getProjectMembers(projectId)
            groupMembersLiveData.postValue(newList.body())
        }
    }

    fun addComment(comment: Comment) {
        CoroutineScope(Dispatchers.IO).launch {
            commentApi.addComment(AddCommentRequest(comment.taskId,comment.commenter,comment.text))
            val newList = commentApi.getComments(comment.taskId)
            commentsLiveData.postValue(newList.body())
        }
    }

    fun registerUser(registrationRequest: RegistrationRequest) {
        CoroutineScope(Dispatchers.IO).launch {
            userApi.registerUser(registrationRequest)
        }
    }

    suspend fun loginUser(email: String, password: String): AppUser? = withContext(Dispatchers.IO) {
        return@withContext userApi.loginUser(LoginRequest(email,password)).body()
    }


    fun setTasks(projectId: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            val curList = taskApi.getTasks(projectId).body()
            tasksLiveData.postValue(curList!!)
        }
    }

    fun setCurTask(id: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            val curTask = taskApi.getTaskById(id).body()
            curTaskLiveData.postValue(curTask!!)
        }
    }

    fun setProjects(userId: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            val curList = projectApi.getProjects(userId).body()
            projectsLiveData.postValue(curList!!)
        }
    }

    fun setGroupMembers(projectId: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            val curList = projectMembersApi.getProjectMembers(projectId).body()
            groupMembersLiveData.postValue(curList!!)
        }
    }

    fun setComments(taskId: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            val curList = commentApi.getComments(taskId).body()
            commentsLiveData.postValue(curList!!)
        }
    }

    suspend fun getUserById(id: Long): AppUser? = withContext(Dispatchers.IO) {
        return@withContext userApi.getUserById(id).body()
    }

    suspend fun getUserByEmail(email: String): AppUser? = withContext(Dispatchers.IO) {
        return@withContext userApi.getUserByEmail(AddProjectMemberRequest(email)).body()
    }
}