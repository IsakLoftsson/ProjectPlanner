package `is`.hi.hbv601g.projectplanner.data

import android.content.Context
import androidx.room.*

/*@Dao
interface AppUserDao {
    @Query("SELECT * FROM AppUser WHERE id == :id")
    fun getById(id: Long): AppUser

    @Query("SELECT * FROM AppUser WHERE id IN (:ids)")
    fun getAllByIds(ids: List<Long>): List<AppUser>

    @Query("SELECT * FROM AppUser WHERE email LIKE :email")
    fun getByEmail(email: String): AppUser?

    @Query("SELECT * FROM AppUser WHERE email LIKE :email AND password LIKE :password")
    fun getByEmailAndPassword(email: String, password: String): AppUser?

    @Insert
    fun insertUser(user: AppUser)
}

@Dao
interface ProjectMembersDao {
    @Query("SELECT projectId FROM ProjectMembers WHERE userId == :id")
    fun getByUserId(id: Long): List<Long>

    @Query("SELECT userId FROM ProjectMembers WHERE projectId == :id")
    fun getByProjectId(id: Long): List<Long>

    @Insert
    fun insertProjectMember(projectMember: ProjectMembers)
}

@Dao
interface TaskDao {
    @Query("SELECT * FROM Task WHERE projectId == :id")
    fun getByProjectId(id: Long): List<Task>

    @Query("SELECT * FROM Task WHERE id == :id")
    fun getById(id: Long): Task

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(task: Task)
}

@Dao
interface ProjectDao {
    @Query("SELECT * FROM Project WHERE id in (:ids)")
    fun getAllByIds(ids: List<Long>): List<Project>

    @Query("SELECT * FROM Project WHERE id == :id")
    fun getById(id: Long): Project

    @Insert
    fun insertProject(project: Project)
}

@Dao
interface CommentDao {
    @Query("SELECT * FROM Comment WHERE taskId == :taskId")
    fun getByTaskId(taskId: Long): List<Comment>

    @Insert
    fun insertComment(comment: Comment)
}

@Database(entities = [AppUser::class,Project::class,Task::class,ProjectMembers::class,Comment::class], version = 1)
abstract class ProjectPlannerDatabase: RoomDatabase() {
    abstract fun appUserDao(): AppUserDao
    abstract fun projectMembersDao(): ProjectMembersDao
    abstract fun taskDao(): TaskDao
    abstract fun projectDao(): ProjectDao
    abstract fun commentDao(): CommentDao
}

private lateinit var INSTANCE: ProjectPlannerDatabase

fun getDatabase(context: Context): ProjectPlannerDatabase {
    synchronized(ProjectPlannerDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                ProjectPlannerDatabase::class.java,
                "projectplanner").build()
        }
    }
    return INSTANCE
}*/