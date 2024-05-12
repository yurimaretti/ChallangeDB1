package br.com.fiap.challangedb1.model

import com.google.gson.annotations.SerializedName

data class MatchModel(
    @SerializedName("matchId") val matchId: Int,
    @SerializedName("curtidaAprendiz") val curtidaAprendiz: Int,
    @SerializedName("curtidaMentor") val curtidaMentor: Int,
    @SerializedName("emailAprdz") val emailAprdz: String,
    @SerializedName("emailMentor") val emailMentor: String
)
