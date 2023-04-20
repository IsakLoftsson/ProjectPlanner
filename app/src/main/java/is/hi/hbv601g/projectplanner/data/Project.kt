package `is`.hi.hbv601g.projectplanner.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Project(
    @SerializedName("id")
    val id: Long,
    @SerializedName("owner")
    val ownerId: Long,
    @SerializedName("name")
    val title: String,
    @SerializedName("description")
    val description: String
)
