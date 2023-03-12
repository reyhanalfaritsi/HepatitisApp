package com.example.hepatitisapp

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.hepatitisapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        playAnimation()
        binding.cardHepatitis.setOnClickListener {
            startActivity(Intent(this, InformationHepatitisActivity::class.java))
        }

        binding.cardGejala.setOnClickListener {
            startActivity(Intent(this, GejalaActivity::class.java))
        }

        binding.cardPredict.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun playAnimation() {
        val cardHepatitisAnim = ObjectAnimator.ofFloat(binding.cardHepatitis, View.ALPHA, 1f).setDuration(500)
        val cardGejalaAnim = ObjectAnimator.ofFloat(binding.cardGejala, View.ALPHA, 1f).setDuration(500)
        val cardPredictAnim = ObjectAnimator.ofFloat(binding.cardPredict, View.ALPHA, 1f).setDuration(500)


        AnimatorSet().apply {
            playSequentially(cardHepatitisAnim, cardGejalaAnim, cardPredictAnim)
            start()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
        finish()
    }
}