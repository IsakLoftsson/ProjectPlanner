package `is`.hi.hbv601g.projectplanner.data

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Comment(
    val id: Long,
    val taskId: Long,
    val commenter: String,
    val text: String
)
