package `is`.hi.hbv601g.projectplanner.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Comment(
    @SerializedName("id")
    val id: Long,
    @SerializedName("taskId")
    val taskId: Long,
    @SerializedName("userName")
    val commenter: String,
    @SerializedName("text")
    val text: String
)
