package `is`.hi.hbv601g.projectplanner.data

data class Project(
    val id: Long,
    val ownerId: Long,
    val title: String,
    val description: String,
    val groupName: String = "Group Members"
)
