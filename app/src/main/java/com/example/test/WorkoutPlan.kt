import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workoutPlans")
data class WorkoutPlan(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val document: String,
    val field: String,
    val number: Int
)
