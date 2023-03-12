package com.example.hepatitisapp.response

import com.google.gson.annotations.SerializedName

data class PredictMortalityResponse(

	@field:SerializedName("Die")
	val die: Double,

	@field:SerializedName("Live")
	val live: Double
)
