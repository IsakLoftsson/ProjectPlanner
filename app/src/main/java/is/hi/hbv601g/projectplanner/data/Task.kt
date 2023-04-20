package `is`.hi.hbv601g.projectplanner.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

data class Task(
    val id: Long,
    val projectId: Long,
    val name: String,
    val description: String,
    val deadline: String,
    val ownerId: Long,
    val status: String
)
