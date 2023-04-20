package `is`.hi.hbv601g.projectplanner.data

import `is`.hi.hbv601g.projectplanner.data.requests.AddTaskRequest
import retrofit2.Response
import retrofit2.http.*

interface TaskApi {
    @POST("/api/addtask/{id}")
    suspend fun addTask(@Body addTaskRequest: AddTaskRequest, @Path("id") projectId: Long): Response<Boolean>

    @POST("/api/edittask")
    suspend fun editTask(@Body task: Task): Response<Boolean>

    @GET("/api/tasks/{id}")
    suspend fun getTasks(@Path("id") projectId: Long): Response<List<Task>>

    @GET("/api/task/{id}")
    suspend fun getTaskById(@Path("id") taskId: Long): Response<Task>

}