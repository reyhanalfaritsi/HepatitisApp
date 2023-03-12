package com.example.hepatitisapp.util

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.hepatitisapp.R

class ProgressButton(context: Context, view: View) {
    var layout = view.findViewById<ConstraintLayout>(R.id.layout)
    var text = view.findViewById<TextView>(R.id.textView)
    var progress = view.findViewById<ProgressBar>(R.id.progressBar)

    fun ActiveButton(){
        progress.visibility = View.VISIBLE
        text.text = "Mohon Tunggu......."
    }



}