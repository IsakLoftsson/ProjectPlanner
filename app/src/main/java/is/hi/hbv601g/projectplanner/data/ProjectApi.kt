package `is`.hi.hbv601g.projectplanner.data

import `is`.hi.hbv601g.projectplanner.data.requests.AddProjectRequest
import retrofit2.Response
import retrofit2.http.*

interface ProjectApi {
    @POST("/api/addproject/{id}")
    suspend fun addProject(@Body addProjectRequest: AddProjectRequest,@Path("id") userId: Long): Response<Boolean>

    @GET("/api/projects/{id}")
    suspend fun getProjects(@Path("id") userId: Long): Response<List<Project>>
}