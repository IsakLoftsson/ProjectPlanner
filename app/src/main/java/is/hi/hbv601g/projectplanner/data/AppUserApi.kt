package `is`.hi.hbv601g.projectplanner.data

import `is`.hi.hbv601g.projectplanner.data.requests.AddProjectMemberRequest
import `is`.hi.hbv601g.projectplanner.data.requests.LoginRequest
import `is`.hi.hbv601g.projectplanner.data.requests.RegistrationRequest
import retrofit2.Response
import retrofit2.http.*

interface AppUserApi {
    @POST("/api/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest) : Response<AppUser>

    @POST("/api/register")
    suspend fun registerUser(@Body registrationRequest: RegistrationRequest) : Response<MessageResponse>

    @GET("/api/user/{id}")
    suspend fun getUserById(@Path("id") userId: Long) : Response<AppUser>

    @POST("/api/user")
    suspend fun getUserByEmail(@Body addProjectMemberRequest: AddProjectMemberRequest) : Response<AppUser>
}