package com.example.hepatitisapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class InformationHepatitisActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information_hepatitis)

        actionBar?.setDisplayHomeAsUpEnabled(true)
    }
}