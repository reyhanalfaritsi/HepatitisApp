package com.example.hepatitisapp


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.example.hepatitisapp.databinding.ActivityMainBinding
import com.example.hepatitisapp.util.ProgressButton
import com.example.hepatitisapp.util.ResultPreferences
import com.example.hepatitisapp.viewmodel.PredictViewModel
import com.example.hepatitisapp.viewmodel.PredictViewModelFactory
import kotlinx.coroutines.launch
import java.util.*
import kotlin.concurrent.schedule


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "result")

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: PredictViewModel
    private lateinit var pref: ResultPreferences
    private lateinit var view: View

    private var age: String? = null
    private var sex: String? = null
    private var steroid: String? = null
    private var antivirals: String? = null
    private var fatigue: String? = null
    private var spiders: String? = null
    private var varices: String? = null
    private var ascites: String? = null
    private var bilirubin: String? = null
    private var alk_phosphate: String? = null
    private var sgot: String? = null
    private var albumin: String? = null
    private var protime: String? = null
    private var histology: String? = null
    private var die: Double? = null
    private var live: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBar?.setDisplayHomeAsUpEnabled(true)

        pref = ResultPreferences.getInstance(dataStore)


        viewModel =  ViewModelProvider(this, PredictViewModelFactory(pref)).get(
            PredictViewModel::class.java
        )

        view = findViewById(R.id.btn_predict)

        validateForm()
        view.setOnClickListener {

            val valueAge = binding.edtUmur.text.toString().trim()
            val checkedJenisKelamin = binding.rgJk.checkedRadioButtonId
            if (checkedJenisKelamin != -1){
                val radioButtonJK: RadioButton = binding.root.findViewById(checkedJenisKelamin)
                if (radioButtonJK.text.equals("Pria")){
                    sex = "1"
                }else if ((radioButtonJK.text.equals("Wanita"))){
                    sex = "2"
                }else{
                    sex = ""
                }
            }else{
                Toast.makeText(this, "Mohon pilih salah satu", Toast.LENGTH_SHORT).show()
            }
            val checkedSteroid = binding.rgSteroid.checkedRadioButtonId
            if (checkedSteroid != -1){
                val radioButtonST: RadioButton = binding.root.findViewById(checkedSteroid)
                if (radioButtonST.text.equals("Ya")){
                    steroid = "1"
                }else if(radioButtonST.text.equals("Tidak")){
                    steroid = "2"
                }else{
                    steroid = ""
                }
            }else{
                Toast.makeText(this, "Mohon pilih salah satu", Toast.LENGTH_SHORT).show()
            }
            val checkedAntivirals = binding.rgAntivirals.checkedRadioButtonId
            if (checkedAntivirals != -1){
                val radioButtonAT: RadioButton = binding.root.findViewById(checkedAntivirals)
                if (radioButtonAT.text.equals("Ya")){
                    antivirals = "1"
                }else if (radioButtonAT.text.equals("Tidak")){
                    antivirals = "2"
                }else{
                    antivirals = ""
                }
            }else{
                Toast.makeText(this, "Mohon pilih salah satu", Toast.LENGTH_SHORT).show()
            }
            val checkedFatigue = binding.rgFatigue.checkedRadioButtonId
            if (checkedFatigue != -1){
                val radioButtonFG: RadioButton = binding.root.findViewById(checkedFatigue)
                if (radioButtonFG.text.equals("Ya")){
                    fatigue = "1"
                }else if (radioButtonFG.text.equals("Tidak")){
                    fatigue = "2"
                }else{
                    fatigue = ""
                }
            }else{
                Toast.makeText(this, "Mohon pilih salah satu", Toast.LENGTH_SHORT).show()
            }
            val checkedSpiders = binding.rgSpider.checkedRadioButtonId
            if (checkedSpiders != -1){
                val radioButtonSP: RadioButton = binding.root.findViewById(checkedSpiders)
                if (radioButtonSP.text.equals("Ya")){
                    spiders = "1"
                }else if (radioButtonSP.text.equals("Tidak")){
                    spiders = "2"
                }else{
                    spiders = ""
                }
            }else{
                Toast.makeText(this, "Mohon pilih salah satu", Toast.LENGTH_SHORT).show()
            }
            val checkedVarices = binding.rgVarices.checkedRadioButtonId
            if (checkedVarices != -1){
                val radioButtonVA: RadioButton = binding.root.findViewById(checkedVarices)
                if (radioButtonVA.text.equals("Ya")){
                    varices = "1"
                }else if (radioButtonVA.text.equals("Tidak")){
                    varices = "2"
                }else{
                    varices = ""
                }
            }else{
                Toast.makeText(this, "Mohon pilih salah satu", Toast.LENGTH_SHORT).show()
            }
            val checkedAscites = binding.rgAscites.checkedRadioButtonId
            if (checkedAscites != -1){
                val radioButtonAS: RadioButton = binding.root.findViewById(checkedAscites)
                if (radioButtonAS.text.equals("Ya")){
                    ascites = "1"
                }else if (radioButtonAS.text.equals("Tidak")){
                    ascites = "2"
                }else{
                    ascites = ""
                }
            }else{
                Toast.makeText(this, "Mohon pilih salah satu", Toast.LENGTH_SHORT).show()
            }
            val valueBilirubin = binding.edtBilirubin.text.toString().trim()
            val valueAlkalinePhosphate = binding.edtAlkphospate.text.toString().trim()
            val valueSgot = binding.edtSgot.text.toString().trim()
            val valueAlbumin = binding.edtAlbumin.text.toString().trim()
            val valueProtombine = binding.edtProtime.text.toString().trim()
            val checkedHistology = binding.rgHistology.checkedRadioButtonId
            if (checkedHistology !=-1){
                val radioButtonHI: RadioButton = binding.root.findViewById(checkedHistology)
                if (radioButtonHI.text.equals("Ya")){
                    histology = "1"
                }else if (radioButtonHI.text.equals("Tidak")){
                    histology = "2"
                }else{
                    histology = ""
                }
            }else{
                Toast.makeText(this, "Mohon pilih salah satu", Toast.LENGTH_SHORT).show()
            }

            age = valueAge
            bilirubin = valueBilirubin
            alk_phosphate = valueAlkalinePhosphate
            sgot = valueSgot
            albumin = valueAlbumin
            protime = valueProtombine

            if(valueAge.isNotEmpty() &&  sex != "" && steroid != "" && antivirals != "" && fatigue != "" && spiders!= "" && varices!= "" && ascites!= "" && valueBilirubin.isNotEmpty() && valueAlkalinePhosphate.isNotEmpty() && valueSgot.isNotEmpty() && valueAlbumin.isNotEmpty() && valueProtombine.isNotEmpty() && histology!= ""){
                predictMortality(age!!,sex!!,steroid!!,antivirals!!,fatigue!!,spiders!!,varices!!,ascites!!,bilirubin!!,alk_phosphate!!,sgot!!,albumin!!,protime!!,histology!!)

                pref.diePrediction.asLiveData().observe(this, {
                    if (it != null){
                        die = it
                    }
                })

                pref.livePrediction.asLiveData().observe(this, {
                    if (it != null){
                        live = it
                    }
                })
                val pb = ProgressButton(this, view)
                pb.ActiveButton()
                Timer().schedule(2500) {
                    IntentToResult()
                }
            }else{
                Toast.makeText(this, "Harap isi data dengan baik dan benar!!!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateUmur(): Boolean{
        var errorMessage: String? = null
        val value: String = binding.edtUmur.text.toString()
        if (value.isEmpty()){
            errorMessage = "Harus di isi!!!"
        }
        if(errorMessage!= null){
            binding.notifUmur.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }

    private fun validateBilirubin(): Boolean{
        var errorMessage: String? = null
        val value: String = binding.edtBilirubin.text.toString()
        if (value.isEmpty()){
            errorMessage = "Harus di isi!!!"
        }
        if(errorMessage!= null){
            binding.notifBilirubin.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }

    private fun validateAlkphosphate(): Boolean{
        var errorMessage: String? = null
        val value: String = binding.edtAlkphospate.text.toString()
        if (value.isEmpty()){
            errorMessage = "Harus di isi!!!"
        }
        if(errorMessage!= null){
            binding.notifAk.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }

    private fun validateSgot(): Boolean{
        var errorMessage: String? = null
        val value: String = binding.edtSgot.text.toString()
        if (value.isEmpty()){
            errorMessage = "Harus di isi!!!"
        }
        if(errorMessage!= null){
            binding.notifSgot.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }

    private fun validateAlbumin(): Boolean{
        var errorMessage: String? = null
        val value: String = binding.edtAlbumin.text.toString()
        if (value.isEmpty()){
            errorMessage = "Harus di isi!!!"
        }
        if(errorMessage!= null){
            binding.notifAlbumin.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }

    private fun validateProtime(): Boolean{
        var errorMessage: String? = null
        val value: String = binding.edtProtime.text.toString()
        if (value.isEmpty()){
            errorMessage = "Harus di isi!!!"
        }
        if(errorMessage!= null){
            binding.notifProtime.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }

    private fun validateForm(){
        binding.edtUmur.setOnFocusChangeListener { _, focus ->
            if(focus){
                if(binding.notifUmur.isErrorEnabled){
                    binding.notifUmur.isErrorEnabled = false
                }
            }else{
                validateUmur()
            }
        }
        binding.edtBilirubin.setOnFocusChangeListener { _, focus ->
            if(focus){
                if(binding.notifBilirubin.isErrorEnabled){
                    binding.notifBilirubin.isErrorEnabled = false
                }
            }else{
                validateBilirubin()
            }
        }
        binding.edtAlkphospate.setOnFocusChangeListener { _, focus ->
            if(focus){
                if(binding.notifAk.isErrorEnabled){
                    binding.notifAk.isErrorEnabled = false
                }
            }else{
                validateAlkphosphate()
            }
        }
        binding.edtSgot.setOnFocusChangeListener { _, focus ->
            if(focus){
                if(binding.notifSgot.isErrorEnabled){
                    binding.notifSgot.isErrorEnabled = false
                }
            }else{
                validateSgot()
            }
        }
        binding.edtAlbumin.setOnFocusChangeListener { _, focus ->
            if(focus){
                if(binding.notifAlbumin.isErrorEnabled){
                    binding.notifAlbumin.isErrorEnabled = false
                }
            }else{
                validateAlbumin()
            }
        }
        binding.edtProtime.setOnFocusChangeListener { _, focus ->
            if(focus){
                if(binding.notifProtime.isErrorEnabled){
                    binding.notifProtime.isErrorEnabled = false
                }
            }else{
                validateProtime()
            }
        }
    }

    private fun predictMortality(age: String, sex: String, steroid: String, antivirals: String, fatigue: String, spiders: String, varices: String, ascites: String, bilirubin: String, alk_phosphate: String, sgot: String, albumin: String, protime: String, histology: String){
        return viewModel.predictMortality(age, sex, steroid, antivirals, fatigue, spiders, varices, ascites, bilirubin, alk_phosphate, sgot, albumin, protime, histology)
    }

    private fun IntentToResult(){
        if(die !=null && live !=null){
            Intent(this, ResultPredictionActivity::class.java).also {
                it.putExtra(ResultPredictionActivity.EXTRA_DIE, die)
                it.putExtra(ResultPredictionActivity.EXTRA_LIVE, live)
                startActivity(it)
            }
        } else {
            Toast.makeText(this, "Gagal memprediksi, silahkan coba lagi", Toast.LENGTH_SHORT).show()
        }
    }
    /*
    private fun setButtonEnable(){
        view = findViewById(R.id.btn_predict)
        val umur = binding.edtUmur.text.toString()
        val bilirubin = binding.edtBilirubin.text.toString()
        val alkalinePhosphate = binding.edtAlkphospate.text.toString()
        val sgot = binding.edtSgot.text.toString()
        val albumin = binding.edtAlbumin.text.toString()
        val protombine = binding.edtProtime.text.toString()
        view.isEnabled = umur.isNotEmpty() && sex != "" && steroid != "" && antivirals != "" && fatigue != "" && spiders != "" && varices != "" && ascites != "" && bilirubin.isNotEmpty() && alkalinePhosphate.isNotEmpty() && sgot.isNotEmpty() && albumin.isNotEmpty() && protombine.isNotEmpty() && histology != ""
    }

     */

}


