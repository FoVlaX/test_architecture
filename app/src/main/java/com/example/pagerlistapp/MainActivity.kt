package com.example.pagerlistapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private var bottomMenu: BottomNavigationView? = null
    private var navigationView: NavigationView? = null
    private var navHostFragment: NavHostFragment? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindMenus()
    }

    private fun bindMenus() {
        this.bottomMenu = findViewById<BottomNavigationView>(R.id.bottom_menu)
        this.navHostFragment = supportFragmentManager
                .findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        NavigationUI.setupWithNavController(bottomMenu!!,
                this.navHostFragment!!.navController)


    }

}