package `is`.hi.hbv601g.projectplanner.data.requests

data class AddTaskRequest(
    val name: String,
    val description: String,
    val deadline: String,
    val ownerUserId: Long,
    val status: String
)
