package `is`.hi.hbv601g.projectplanner.data

import java.io.FileDescriptor
import java.sql.Date

data class Task(
    val id: Long,
    val projectId: Long,
    val name: String,
    val description: String,
    val deadline: String,
    val userId: Long
)
