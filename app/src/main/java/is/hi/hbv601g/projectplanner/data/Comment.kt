package `is`.hi.hbv601g.projectplanner.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Comment(
    @PrimaryKey val id: Long,
    val taskId: Long,
    val commenter: String,
    val text: String
)
