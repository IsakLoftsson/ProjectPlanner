package `is`.hi.hbv601g.projectplanner.data

import `is`.hi.hbv601g.projectplanner.data.requests.LoginRequest
import `is`.hi.hbv601g.projectplanner.data.requests.RegistrationRequest
import retrofit2.Response
import retrofit2.http.*

interface AppUserApi {
    @POST("/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest) : Response<AppUser>

    @POST("/register")
    suspend fun registerUser(@Body registrationRequest: RegistrationRequest) : Response<String>

    @GET("/user/{id}")
    suspend fun getUserById(@Path("id") userId: Long) : Response<AppUser>
}