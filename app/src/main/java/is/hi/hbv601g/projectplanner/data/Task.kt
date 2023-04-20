package `is`.hi.hbv601g.projectplanner.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.sql.Date

data class Task(
    @SerializedName("id")
    val id: Long,
    @SerializedName("projectId")
    val projectId: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("deadline")
    val deadline: String,
    @SerializedName("ownerUserId")
    val ownerId: Long,
    @SerializedName("status")
    val status: String
)
