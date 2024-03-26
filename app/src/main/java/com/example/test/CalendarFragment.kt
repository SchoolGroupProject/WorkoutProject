import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.test.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter


data class DailyPlan(
    val exercise: String,
    val repetitions: String,
    val meal: String
)
class CalendarFragment : Fragment() {

    private lateinit var calendarView: CalendarView
    private lateinit var infoView: TextView
    private lateinit var addPlanButton: Button

    @RequiresApi(Build.VERSION_CODES.O)
    private var selectedDate: String = LocalDate.now().format(DateTimeFormatter.ISO_DATE)

    // Use a map to associate each day with its plan
    private val dailyPlans = mutableMapOf<String, DailyPlan>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calendarView = view.findViewById(R.id.calendar)
        infoView = view.findViewById(R.id.date_view) // This must happen before you use infoView
        addPlanButton = view.findViewById(R.id.addPlanButton)

        initializeDefaultPlans()

        // Display today's plan
        displayPlanForDate(selectedDate)

        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            selectedDate = "$year-${month + 1}-$dayOfMonth"
            displayPlanForDate(selectedDate)

            addPlanButton.setOnClickListener {
                showAddPlanDialog()
            }

        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun showAddPlanDialog() {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_add_plan, null)
        val exerciseInput = dialogView.findViewById<EditText>(R.id.editTextExercise)
        val repetitionsInput = dialogView.findViewById<EditText>(R.id.editTextRepetitions)
        val mealInput = dialogView.findViewById<EditText>(R.id.editTextMeal)

        AlertDialog.Builder(context)
            .setView(dialogView)
            .setTitle("Add/Update Plan")
            .setPositiveButton("Save") { _, _ ->
                val exercise = exerciseInput.text.toString()
                val repetitions = repetitionsInput.text.toString()
                val meal = mealInput.text.toString()

                updatePlanForDate(selectedDate, exercise, repetitions, meal)
                displayPlanForDate(selectedDate) // Refresh the displayed plan
            }
            .setNegativeButton("Cancel", null)
            .create()
            .show()
    }


    private fun displayPlanForDate(date: String) {
        dailyPlans[date]?.let {
            infoView.text = "Exercise: ${it.exercise}\nRepetitions: ${it.repetitions}\nMeal: ${it.meal}"
        } ?: run {
            infoView.text = "No plan for this day"
        }
    }

    // Default Plans
    @RequiresApi(Build.VERSION_CODES.O)
    private fun initializeDefaultPlans() {
        val today = LocalDate.now().format(DateTimeFormatter.ISO_DATE)
        dailyPlans[today] = DailyPlan("Push-ups", "10 reps", "Chicken Salad")
        // Add more default plans as needed
    }

    // Method to update the plan for a specific day
    fun updatePlanForDate(date: String, exercise: String, repetitions: String, meal: String) {
        dailyPlans[date] = DailyPlan(exercise, repetitions, meal)
    }
}