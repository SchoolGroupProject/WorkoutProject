import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.TextView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import com.example.test.R

class CalendarFragment : Fragment() {

    private lateinit var calendarView: CalendarView
    private lateinit var infoView: TextView // Assuming this is the TextView for displaying plans
    private lateinit var databaseReference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        databaseReference = FirebaseDatabase.getInstance().getReference("Plans")

        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        calendarView = view.findViewById(R.id.calendar)
        infoView = view.findViewById(R.id.date_view)

        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val date = "${year}-${(month + 1).toString().padStart(2, '0')}-${dayOfMonth.toString().padStart(2, '0')}"
            fetchPlanForDate(date)
        }
    }

    private fun fetchPlanForDate(date: String) {
        databaseReference.child(date).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val workout = dataSnapshot.child("workout").getValue(String::class.java) ?: "No workout planned"
                val mealPlan = dataSnapshot.child("mealPlan").getValue(String::class.java) ?: "No meal plan set"
                infoView.text = "Workout: $workout\nMeal Plan: $mealPlan"
            }

            override fun onCancelled(databaseError: DatabaseError) {
            }
        })
    }
}
