package `is`.hi.hbv601g.projectplanner.data

import `is`.hi.hbv601g.projectplanner.data.requests.AddCommentRequest
import retrofit2.Response
import retrofit2.http.*

interface CommentApi {
    @POST("/addcomment")
    suspend fun addComment(@Body addCommentRequest: AddCommentRequest): Response<Boolean>

    @GET("/comments/{id}")
    suspend fun getComments(@Path("id") taskId: Long): Response<List<Comment>>
}