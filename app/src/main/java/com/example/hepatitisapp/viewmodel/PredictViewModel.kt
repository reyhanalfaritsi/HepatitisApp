package com.example.hepatitisapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hepatitisapp.network.ApiConfig
import com.example.hepatitisapp.response.PredictMortalityResponse
import com.example.hepatitisapp.util.ResultPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PredictViewModel(private var pref: ResultPreferences) : ViewModel(){


    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isTravel = MutableLiveData<Boolean>()
    val isTravel: LiveData<Boolean> = _isTravel

    private val _responseMessage = MutableLiveData<String>()
    val responseMessage: LiveData<String> = _responseMessage


    fun predictMortality(age: String, sex: String, steroid: String, antivirals: String, fatigue: String, spiders: String, varices: String, ascites: String, bilirubin: String, alk_phosphate: String, sgot: String, albumin: String, protime: String, histology: String){

        _isLoading.value = true
        val client = ApiConfig.getApiService().postPredictMortality(age, sex, steroid, antivirals, fatigue, spiders, varices, ascites, bilirubin, alk_phosphate, sgot, albumin, protime, histology)
        client.enqueue(object : Callback<PredictMortalityResponse> {
            override fun onResponse(
                call: Call<PredictMortalityResponse>,
                response: Response<PredictMortalityResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful){
                    saveMortalityPrediction(response.body()?.die, response.body()?.live)
                } else{
                    Log.e(TAG, "onFailure: ${response.message()}")
                    _responseMessage.value = "Gagal Melakukan Prediksi"
                }
            }

            override fun onFailure(call: Call<PredictMortalityResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG,"onFailure: ${t.message.toString()}")
            }
        })
    }
    fun saveMortalityPrediction(die: Double?, live: Double?) {
        viewModelScope.launch(Dispatchers.IO) {
            if (die != null && live != null) {
                pref.saveMortalityPrediction(die, live)
            }
        }
    }
    companion object{
        private const val TAG = "PredictViewModel"
    }

}