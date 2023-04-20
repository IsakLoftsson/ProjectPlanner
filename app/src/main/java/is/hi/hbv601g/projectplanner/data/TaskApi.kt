package `is`.hi.hbv601g.projectplanner.data

import `is`.hi.hbv601g.projectplanner.data.requests.AddTaskRequest
import retrofit2.Response
import retrofit2.http.*

interface TaskApi {
    @POST("/addtask/{id}")
    suspend fun addTask(@Body addTaskRequest: AddTaskRequest, @Path("id") projectId: Long): Response<Boolean>

    @GET("/tasks/{id}")
    suspend fun getTasks(@Path("id") projectId: Long): Response<List<Task>>

}