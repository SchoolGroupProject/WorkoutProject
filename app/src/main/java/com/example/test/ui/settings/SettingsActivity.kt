package com.example.test.ui.settings

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.test.R

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        findViewById<Button>(R.id.Metric).setOnClickListener {
        }

        findViewById<Button>(R.id.DarkMode).setOnClickListener {
        }

        findViewById<Button>(R.id.Profile).setOnClickListener {
        }

        findViewById<Button>(R.id.Notifications).setOnClickListener {
        }

        findViewById<Button>(R.id.BuildInfo).setOnClickListener {
        }
    }
}