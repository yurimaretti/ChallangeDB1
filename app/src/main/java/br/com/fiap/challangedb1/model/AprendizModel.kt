package br.com.fiap.challangedb1.model

import com.google.gson.annotations.SerializedName

data class AprendizModel(
    @SerializedName("emailAprendiz") val emailAprdz: String,
    @SerializedName("nomeAprendiz") val nomeAprdz: String,
    @SerializedName("generoAprendiz") val generoAprdz: String,
    @SerializedName("senhaAprendiz") val senhaAprdz: String,
    @SerializedName("formacaoAprendiz") val formacaoAprdz: List<FormAprdzModel>?,
    @SerializedName("interesse") val interesse: List<InteresseModel>?,
    @SerializedName("match") val match: List<MatchModel>?
)
