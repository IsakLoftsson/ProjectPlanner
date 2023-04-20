package `is`.hi.hbv601g.projectplanner.data

import androidx.room.Entity
import androidx.room.PrimaryKey

data class AppUser(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String
)
