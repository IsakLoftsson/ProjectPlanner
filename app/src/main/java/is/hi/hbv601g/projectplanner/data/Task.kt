package `is`.hi.hbv601g.projectplanner.data

data class Task(
    val id: Long,
    val projectId: Long,
    val name: String,
    val description: String,
    val deadline: String,
    val ownerId: Long,
    val status: String
)
