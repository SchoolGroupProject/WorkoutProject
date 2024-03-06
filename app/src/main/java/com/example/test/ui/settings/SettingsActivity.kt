package com.example.test.ui.settings

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.example.test.ProfileActivity
import com.example.test.R

class SettingsActivity : AppCompatActivity() {

    var notificationsEnabled = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        findViewById<Button>(R.id.Metric).setOnClickListener {
        }

        findViewById<Button>(R.id.DarkMode).setOnClickListener {
            toggleDarkMode()
        }

        findViewById<Button>(R.id.Profile).setOnClickListener {
            openProfile()
        }

        findViewById<Button>(R.id.Notifications).setOnClickListener {
            toggleNotifications()
        }

        findViewById<Button>(R.id.BuildInfo).setOnClickListener {
        }
    }

    private fun toggleDarkMode() {
        val nightModeFlags =
            resources.configuration.uiMode and android.content.res.Configuration.UI_MODE_NIGHT_MASK
        when (nightModeFlags) {
            android.content.res.Configuration.UI_MODE_NIGHT_YES -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                Toast.makeText(this, "Switched to Light Mode", Toast.LENGTH_SHORT).show()
            }

            android.content.res.Configuration.UI_MODE_NIGHT_NO -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                Toast.makeText(this, "Switched to Dark Mode", Toast.LENGTH_SHORT).show()
            }

            android.content.res.Configuration.UI_MODE_NIGHT_UNDEFINED -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                Toast.makeText(this, "Switched to Dark Mode", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun openProfile(){
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
    }

    private fun toggleNotifications(){
        notificationsEnabled = !notificationsEnabled
        val message = if (notificationsEnabled) "Notifications turned on" else "Notifications turned off"
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}