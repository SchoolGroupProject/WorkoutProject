import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import androidx.fragment.app.Fragment
import com.example.test.AddPlanActivity
import com.example.test.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

// Imports remain the same

class CalendarFragment : Fragment() {

    private lateinit var calendarView: CalendarView
    private lateinit var infoView: TextView
    private lateinit var addPlanButton: Button
    private var selectedDate: String = "" // Used for date selection
    private var workouts = mutableListOf<WorkoutPlan>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calendarView = view.findViewById(R.id.calendar)
        infoView = view.findViewById(R.id.date_view)
        addPlanButton = view.findViewById(R.id.addPlanButton)

        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            selectedDate = "${year}-${(month + 1).toString().padStart(2, '0')}-${
                dayOfMonth.toString().padStart(2, '0')
            }"
            fetchPlanForDate(selectedDate)
        }

        addPlanButton.setOnClickListener {
            addPlanForDate(selectedDate)
        }

        val currentYear = YearMonth.now().year
        val currentMonth = YearMonth.now().monthValue
        fetchMonthlyWorkouts(currentYear, currentMonth)
    }


    private fun addPlanForDate(date: String) {
        val intent = Intent(activity, AddPlanActivity::class.java).apply {
            putExtra("selectedDate", date)
        }
        startActivity(intent)
    }

    private fun fetchPlanForDate(date: String) {
        val db = FirebaseFirestore.getInstance()
        db.collection("workouts").document(date)
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val workout = document.getString("workout") ?: "No workout planned"
                    val mealPlan = document.getString("mealPlan") ?: "No meal plan set"
                    infoView.text = "Workout: $workout\nMeal Plan: $mealPlan"
                } else {
                    Log.d("CalendarFragment", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("CalendarFragment", "get failed with ", exception)
            }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun fetchMonthlyWorkouts(year: Int, month: Int) {
        val db = FirebaseFirestore.getInstance()
        val startOfMonth = LocalDate.of(year, month, 1)
        val endOfMonth = YearMonth.of(year, month).atEndOfMonth()

        db.collection("workouts")
            .whereGreaterThanOrEqualTo("date", startOfMonth.format(DateTimeFormatter.ISO_DATE))
            .whereLessThanOrEqualTo("date", endOfMonth.format(DateTimeFormatter.ISO_DATE))
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val workoutPlan = document.toObject(WorkoutPlan::class.java)
                    workouts.add(workoutPlan)
                }
                workouts.sortBy { it.date }
            }
            .addOnFailureListener { exception ->
                Log.w("CalendarFragment", "Error getting documents: ", exception)
            }
    }
    data class WorkoutPlan(
        var date: String = "",
        var details: String = ""
    )
}