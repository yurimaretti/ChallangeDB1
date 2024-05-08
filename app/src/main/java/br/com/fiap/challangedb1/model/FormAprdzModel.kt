package br.com.fiap.challangedb1.model

import com.google.gson.annotations.SerializedName

data class FormAprdzModel(
    @SerializedName("formAprdzId") val formAprdzId: Int,
    @SerializedName("nivelFormAprdz") val nivelFormAprdz: String,
    @SerializedName("cursoAprdz") val cursoAprdz: String,
    @SerializedName("instAprdz") val instAprdz: String,
    @SerializedName("emailAprendiz") val emailAprendiz: String
)
