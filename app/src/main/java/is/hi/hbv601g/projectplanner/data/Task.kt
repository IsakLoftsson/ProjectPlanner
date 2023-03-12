package `is`.hi.hbv601g.projectplanner.data

import java.sql.Date

data class Task(
    val id: Long,
    val projectId: Long,
    val name: String
)
