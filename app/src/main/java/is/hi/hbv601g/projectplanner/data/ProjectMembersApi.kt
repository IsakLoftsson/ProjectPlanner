package `is`.hi.hbv601g.projectplanner.data

import `is`.hi.hbv601g.projectplanner.data.requests.AddProjectMemberRequest
import retrofit2.Response
import retrofit2.http.*

interface ProjectMembersApi {
    @POST("/api/addprojectmember/{id}")
    suspend fun addProjectMember(@Body addProjectMemberRequest: AddProjectMemberRequest, @Path("id") projectId: Long) : Response<Boolean>

    @GET("/api/projectmembers/{id}")
    suspend fun getProjectMembers(@Path("id") projectId: Long): Response<List<AppUser>>
}