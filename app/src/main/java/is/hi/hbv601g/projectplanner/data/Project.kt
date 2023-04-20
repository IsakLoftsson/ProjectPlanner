package `is`.hi.hbv601g.projectplanner.data

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Project(
    val id: Long,
    val ownerId: Long,
    val title: String,
    val description: String
)
