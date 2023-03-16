package `is`.hi.hbv601g.projectplanner.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class Datasource {
    private val initialProjectList = loadProjects()
    private val projectsLiveData = MutableLiveData(initialProjectList)
    private val initialTaskList = loadTasks()
    private val tasksLiveData = MutableLiveData(initialTaskList)
    private val initialGroupMembersList = loadGroupMembers()
    private val groupMembersLiveData = MutableLiveData(initialGroupMembersList)

    fun getProjectList(): LiveData<List<Project>> {
        return projectsLiveData
    }

    fun getTaskList(): LiveData<List<Task>> {
        return tasksLiveData
    }

    fun getProject(id: Long): Project? {
        projectsLiveData.value?.let {project ->
            return project.firstOrNull{it.id == id}
        }
        return null
    }

    fun addProject(project: Project) {
        println(project.id)
        println(project.title)
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

    fun getTask(id:Long): Task? {
        tasksLiveData.value?.let {task ->
            return task.firstOrNull{it.id == id}
        }
        return null
    }

    fun getGroupMembersList(): LiveData<List<GroupMembers>> {
        return groupMembersLiveData
    }


}