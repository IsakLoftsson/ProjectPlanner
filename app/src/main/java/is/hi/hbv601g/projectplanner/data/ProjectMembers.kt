package `is`.hi.hbv601g.projectplanner.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProjectMembers(
    @PrimaryKey val id: Long,
    val userId: Long,
    val projectId: Long
)
