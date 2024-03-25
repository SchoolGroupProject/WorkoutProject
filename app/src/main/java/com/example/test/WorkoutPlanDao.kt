package com.example.test

import WorkoutPlan
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutPlanDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(workoutPlan: WorkoutPlan)

    @Query("SELECT * FROM workoutPlans")
    fun getAllWorkoutPlans(): Flow<List<WorkoutPlan>>
}
