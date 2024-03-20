import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.test.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddPlanActivity : AppCompatActivity() {

    private lateinit var editTextWorkoutPlan: EditText
    private lateinit var buttonSaveWorkoutPlan: Button
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_plan)

        editTextWorkoutPlan = findViewById(R.id.editTextWorkoutPlan)
        buttonSaveWorkoutPlan = findViewById(R.id.buttonSaveWorkoutPlan)
        databaseReference = FirebaseDatabase.getInstance().getReference("Plans")

        val selectedDate = intent.getStringExtra("selectedDate")
        buttonSaveWorkoutPlan.setOnClickListener {
            val workoutPlan = editTextWorkoutPlan.text.toString().trim()
            if (workoutPlan.isNotEmpty() && selectedDate != null) {
                saveWorkoutPlan(selectedDate, workoutPlan)
            } else {
                Toast.makeText(this, "Workout plan cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveWorkoutPlan(date: String, plan: String) {
        databaseReference.child(date).setValue(plan).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Workout plan saved", Toast.LENGTH_SHORT).show()
                finish() // Close the activity after saving
            } else {
                Toast.makeText(this, "Failed to save workout plan", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
