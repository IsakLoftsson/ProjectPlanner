package `is`.hi.hbv601g.projectplanner.data.requests

data class AddCommentRequest(
    val taskId: Long,
    val commenter: String,
    val text: String
)
