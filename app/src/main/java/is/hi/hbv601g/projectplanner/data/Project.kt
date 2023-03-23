package `is`.hi.hbv601g.projectplanner.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Project(
    @PrimaryKey val id: Long,
    val ownerId: Long,
    val title: String,
    val description: String,
    val groupName: String = "Group Members"
)
