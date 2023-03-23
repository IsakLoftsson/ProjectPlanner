package `is`.hi.hbv601g.projectplanner.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import kotlin.random.Random

class ProjectPlannerRepository(private val database: ProjectPlannerDatabase) {
    val projectsLiveData = MutableLiveData<List<Project>>()
    val tasksLiveData = MutableLiveData<List<Task>>()

    fun addProject(project: Project) {
        database.projectDao().insertProject(project)
        database.projectMembersDao().insertProjectMember(ProjectMembers(Random.nextLong(),project.ownerId, project.id))
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
        database.taskDao().insertTask(task)
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

    fun setTasks(projectId: Long) {
        val curList = database.taskDao().getByProjectId(projectId)
        tasksLiveData.postValue(curList)
    }

    fun setProjects(userId: Long) {
        val listOfIds = database.projectMembersDao().getByUserId(userId)
        val curList = database.projectDao().getAllByIds(listOfIds)
        projectsLiveData.postValue(curList)
    }
}