package com.example.hepatitisapp


import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.example.hepatitisapp.databinding.ActivityResultPredictionBinding
import com.example.hepatitisapp.util.ResultPreferences
import kotlinx.coroutines.launch


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "result")
class ResultPredictionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultPredictionBinding

    private lateinit var pref: ResultPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultPredictionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        pref = ResultPreferences.getInstance(dataStore)

        val diePrediction = intent.getDoubleExtra(EXTRA_DIE, 0.0)
        val livePrediction = intent.getDoubleExtra(EXTRA_LIVE, 0.0)


        val bundle = Bundle()
        bundle.putDouble(EXTRA_DIE, diePrediction)
        bundle.putDouble(EXTRA_LIVE, livePrediction)

        binding.apply {
            if (diePrediction > livePrediction){
                val stringDie = "Berdasarkan hasil prediksi, harapan hidup anda kecil. Mohon segera konsultasikan dengan dokter anda."
                val spannableString = SpannableString(stringDie)
                val red = ForegroundColorSpan(Color.RED)
                spannableString.setSpan(red, 28, 52, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                photoPredict.setImageResource(R.drawable.sadliver)
                resultPredict.setText(spannableString)
            } else{
                val stringLive = "Berdasarkan hasil prediksi, harapan hidup anda masih ada. Dimohon untuk tetap melakukan pengobatan sampai anda dinyatakan sembuh."
                val spannableStringBuilder = SpannableString(stringLive)
                val green = ForegroundColorSpan(Color.GREEN)
                spannableStringBuilder.setSpan(green, 27, 56, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                photoPredict.setImageResource(R.drawable.happyliver)
                resultPredict.setText(spannableStringBuilder)
            }
        }
        Log.e("", "DIE: $diePrediction")
        Log.e("", "LIVE: $livePrediction")
        binding.btnHome.setOnClickListener {
            clearPrediction()
            startActivity(Intent(this, HomeActivity::class.java))
        }

    }
    override fun onBackPressed() {
        super.onBackPressed()
        clearPrediction()
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    fun clearPrediction() =  lifecycleScope.launch{
        pref.clearPrediction()
    }

    companion object{
        const val EXTRA_DIE = "extra_die"
        const val EXTRA_LIVE = "extra_live"
    }
}