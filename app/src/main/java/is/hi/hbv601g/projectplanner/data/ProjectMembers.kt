package `is`.hi.hbv601g.projectplanner.data

import androidx.room.Entity
import androidx.room.PrimaryKey

data class ProjectMembers(
    val userId: Long,
    val projectId: Long
)
