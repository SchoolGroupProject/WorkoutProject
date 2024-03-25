package com.example.test

import WorkoutPlan
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.test.WorkoutPlanDao

@Database(entities = [WorkoutPlan::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun workoutPlanDao(): WorkoutPlanDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "workout_database"
                ).addCallback(WorkoutDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class WorkoutDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        prepopulateDatabase(database.workoutPlanDao())
                    }
                }
            }
        }

        suspend fun prepopulateDatabase(workoutPlanDao: WorkoutPlanDao) {
            // Initial data
            workoutPlanDao.insert(WorkoutPlan(document = "Chest", field = "Benchpress", number = 30))
            workoutPlanDao.insert(WorkoutPlan(document = "Rest", field = "Rest", number = 0))
        }
    }
}
