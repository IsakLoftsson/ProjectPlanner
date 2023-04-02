package `is`.hi.hbv601g.projectplanner

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import `is`.hi.hbv601g.projectplanner.data.*
import kotlin.random.Random

class ProjectPlannerViewModel : ViewModel() {
    private val datasource = Datasource()
    val projectsLiveData = datasource.getProjectList()
    val tasksLiveData = datasource.getTaskList()
    val groupMembersLiveData = datasource.getGroupMembersList()
    val UsersLiveData = datasource.getUsersList()

    fun getProjectsByUserId(id:Long): LiveData<List<Project>> {
        val filteredList = projectsLiveData.value?.filter{ project -> project.ownerId == id}
        return MutableLiveData(filteredList)
    }

    fun getTasksByProjectId(id:Long): LiveData<List<Task>> {
        val filteredList = tasksLiveData.value?.filter{ task -> task.projectId == id}
        return MutableLiveData(filteredList)
    }

    fun addProject(ownerId: Long,title: String,description: String) {

        val newProject = Project(
            Random.nextLong(),
            ownerId,
            title,
            description
        )

        datasource.addProject(newProject)
    }

    fun getProject(id:Long): Project? {
        println(datasource.getProject(id)?.id)
        return datasource.getProject(id)
    }

    fun getTask(id:Long): Task? {
        return datasource.getTask(id)
    }

    fun getGroupMemberId(id:Long): Long? {
        return datasource.getGroupMember(id)!!.id
    }

    fun getGroupMemberName(id:Long): String? {
        return datasource.getGroupMember(id)!!.name
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
        datasource.addTask(newTask)
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
        datasource.addTask(newTask)
    }

    fun getGroupMembersByProjectId(id:Long): LiveData<List<GroupMembers>> {
        val filteredList = groupMembersLiveData.value?.filter{ groupMembers -> groupMembers.projectId == id}
        return MutableLiveData(filteredList)
    }

    fun getGroupMembersNameByProjectId(id:Long?): ArrayList<String> {
        var groupMembersNames = ArrayList<String>()
        if (id != null) {
            val groupMembersList: List<GroupMembers> = getGroupMembersByProjectId(id).value!!
            for (item in groupMembersList)
                groupMembersNames += item.name
        }
        return groupMembersNames
    }

    fun getUsersByEmail(email: String?): Users? {
        UsersLiveData.value?.let {users ->
            return users.firstOrNull{it.email == email}
        }
        return null
    }

    fun addGroupMember(id: Long, name: String, email: String, projectId: Long) {
        val newGroupMember = GroupMembers(
            id,
            name,
            email,
            projectId
        )
        println("------------ ADD GROUP MEMBER ---------------")
        println("id: " + id)
        println("name: " + name)
        println("email: " + email)
        println("projectId: " + projectId)
        datasource.addGroupMembers(newGroupMember)
    }
}