package com.example.test;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.test.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddPlanActivity extends AppCompatActivity {

    private EditText editTextWorkoutPlan;
    private Button buttonSaveWorkoutPlan;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plan);

        editTextWorkoutPlan = findViewById(R.id.editTextWorkoutPlan);
        buttonSaveWorkoutPlan = findViewById(R.id.buttonSaveWorkoutPlan);

        // Initialize your Firebase database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("Plans");

        Bundle extras = getIntent().getExtras();
        String selectedDate = extras != null ? extras.getString("selectedDate") : null;
        buttonSaveWorkoutPlan.setOnClickListener(view -> {
            String workoutPlan = editTextWorkoutPlan.getText().toString().trim();
            if (!workoutPlan.isEmpty() && selectedDate != null) {
                saveWorkoutPlan(selectedDate, workoutPlan);
            } else {
                Toast.makeText(AddPlanActivity.this, "Workout plan cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveWorkoutPlan(String date, String plan) {
        databaseReference.child(date).setValue(plan).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(AddPlanActivity.this, "Workout plan saved", Toast.LENGTH_SHORT).show();
                finish(); // Close the activity after saving
            } else {
                Toast.makeText(AddPlanActivity.this, "Failed to save workout plan", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
