package com.example.pagerlistapp

import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.provider.CalendarContract.CalendarCache.URI
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private var bottomMenu: BottomNavigationView? = null
    private var navigationView: NavigationView? = null
    private var navHostFragment: NavHostFragment? = null

    var mediaPlayer: MediaPlayer? = null
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindMenus()

        val url = "https://zaycev.net/musicset/dl/aca4339340db9b390126ffdbd793df97/2555349.json?spa=false" // your URL here
       /*mediaPlayer = MediaPlayer().apply {

            setAudioAttributes(
                    AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_UNKNOWN)
                            .setLegacyStreamType(AudioManager.STREAM_MUSIC)
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .build()
            )
            setDataSource(url)
            prepareAsync() // might take long! (for buffering, etc)
            setOnPreparedListener {
                start()
            }
        }*/

    }


    private fun bindMenus() {
        this.bottomMenu = findViewById<BottomNavigationView>(R.id.bottom_menu)
        this.navHostFragment = supportFragmentManager
                .findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        NavigationUI.setupWithNavController(bottomMenu!!,
                this.navHostFragment!!.navController)
    }

}