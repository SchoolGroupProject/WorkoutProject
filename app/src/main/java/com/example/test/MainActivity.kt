package com.example.test

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.navigation.NavigationView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import com.example.test.Health.HealthActivity
import com.example.test.mealPlanner.FoodActivity
import com.example.test.ui.login.LoginActivity
import com.example.test.ui.settings.SettingsActivity
import com.google.firebase.FirebaseApp


open class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout

    override fun setContentView(view: View?) {
        FirebaseApp.initializeApp(this)

        drawerLayout = layoutInflater.inflate(R.layout.activity_main,null) as DrawerLayout
        val _container : FrameLayout = drawerLayout.findViewById(R.id.activityContainer)
        _container.addView(view)
        super.setContentView(drawerLayout)

        val toolsBar:Toolbar = drawerLayout.findViewById(R.id.toolbar)
        setSupportActionBar(toolsBar)

        val navigationView: NavigationView = drawerLayout.findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val toggle: ActionBarDrawerToggle = ActionBarDrawerToggle(this,drawerLayout,toolsBar,R.string.drawer_open,R.string.drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawerLayout.closeDrawer(GravityCompat.START)
      when(item.itemId)
      {
          R.id.nav_home -> { val intent = Intent(this, MainInterfaceActivity::class.java)
              startActivity(intent)}
          R.id.nav_exercise -> {val intent = Intent(this,WorkoutInterfaceActivity::class.java)
              startActivity(intent)}
          R.id.nav_meal_planner -> {val intent = Intent(this, FoodActivity::class.java)
              startActivity(intent)}
          R.id.nav_health -> {val intent = Intent(this, HealthActivity::class.java)
              startActivity(intent)}
          R.id.nav_calendar -> {val intent = Intent(this, Homepage::class.java)
              startActivity(intent)}
      }
        return false
    }

    protected fun allocateActivityTitle(title:String)
    {
        supportActionBar?.setTitle(title)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_login -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }



}
