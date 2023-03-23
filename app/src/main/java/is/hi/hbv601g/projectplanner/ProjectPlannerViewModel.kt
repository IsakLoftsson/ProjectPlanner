package `is`.hi.hbv601g.projectplanner

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import `is`.hi.hbv601g.projectplanner.data.Datasource
import `is`.hi.hbv601g.projectplanner.data.GroupMembers
import `is`.hi.hbv601g.projectplanner.data.Project
import `is`.hi.hbv601g.projectplanner.data.Task
import kotlin.random.Random

class ProjectPlannerViewModel : ViewModel() {
    private val datasource = Datasource()
    val projectsLiveData = datasource.getProjectList()
    val tasksLiveData = datasource.getTaskList()
    val groupMembersLiveData = datasource.getGroupMembersList()

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

    fun addTask(projectId: Long, name: String, description: String, deadline: String, ownerId: Long) {
        val newTask = Task(
            Random.nextLong(),
            projectId,
            name,
            description,
            deadline,
            ownerId

        )
        println(projectId)
        println(name)
        println(deadline)
        datasource.addTask(newTask)
    }

    fun getGroupMembersByProjectId(id:Long): LiveData<List<GroupMembers>> {
        val filteredList = groupMembersLiveData.value?.filter{ groupMembers -> groupMembers.projectId == id}
        return MutableLiveData(filteredList)
    }


}