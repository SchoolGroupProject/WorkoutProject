import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import androidx.fragment.app.Fragment
import com.example.test.R

class CalendarFragment : Fragment() {

    private lateinit var calendarView: CalendarView
    private lateinit var infoView: TextView
    private lateinit var addPlanButton: Button // Declare the Button
    private lateinit var databaseReference: DatabaseReference
    private var selectedDate: String = "" // Declare selectedDate as a class-level property

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Initialize Firebase in onCreate or onViewCreated, depending on your setup
        databaseReference = FirebaseDatabase.getInstance().getReference("Plans")
        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        calendarView = view.findViewById(R.id.calendar)
        infoView = view.findViewById(R.id.date_view)
        addPlanButton = view.findViewById(R.id.addPlanButton)

        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            selectedDate = "${year}-${(month + 1).toString().padStart(2, '0')}-${dayOfMonth.toString().padStart(2, '0')}"
            fetchPlanForDate(selectedDate)
        }

        addPlanButton.setOnClickListener {
            if (selectedDate.isNotEmpty()) {
                addPlanForDate(selectedDate)
            } else {
                Toast.makeText(activity, "Please select a date first.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addPlanForDate(date: String) {
        val intent = Intent(activity, AddPlanActivity::class.java).apply {
            putExtra("selectedDate", date)
        }
        startActivity(intent)
    }

    private fun fetchPlanForDate(date: String) {
        databaseReference.child(date).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val workout = dataSnapshot.child("workout").getValue(String::class.java) ?: "No workout planned"
                val mealPlan = dataSnapshot.child("mealPlan").getValue(String::class.java) ?: "No meal plan set"
                infoView.text = "Workout: $workout\nMeal Plan: $mealPlan"
            }

            override fun onCancelled(databaseError: DatabaseError) {
                //Fill in later
            }
        })
    }
}
