package com.example.hepatitisapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class GejalaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gejala)

        actionBar?.setDisplayHomeAsUpEnabled(true)
    }
}