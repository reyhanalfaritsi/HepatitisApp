package com.example.hepatitisapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.*
import kotlin.concurrent.schedule

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()
        Timer().schedule(2000) {
            IntentToDashboard()
        }
    }

    private fun IntentToDashboard(){
        startActivity(Intent(this, HomeActivity::class.java))
    }
}