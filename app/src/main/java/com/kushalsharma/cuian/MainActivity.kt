package com.kushalsharma.cuian

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tapadoo.alerter.Alerter

class MainActivity() : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
        navView.itemIconTintList = null


        if (!netConnect(applicationContext)) {
            showBanner(
                R.color.red, "You are not connected to internet.",
                5000, R.drawable.ic_baseline_wifi_off_24
            )
        }

    }

    fun showBanner(resourseColor: Int, text: String, delay: Int, drawable: Int) {
        Alerter.create(this)
            .setTitle(text)
            .setBackgroundColorRes(resourseColor)
            .setIcon(drawable)
            .setDuration(delay.toLong())
            .enableSwipeToDismiss()
            .show()
    }

    fun netConnect(ctx: Context): Boolean {
        val cm: ConnectivityManager
        var info: NetworkInfo? = null
        try {
            cm = ctx.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            info = cm.activeNetworkInfo
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return info != null


    }


}

