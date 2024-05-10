package br.com.fiap.challangedb1.model

import com.google.gson.annotations.SerializedName

data class InteresseModel(
    @SerializedName("interesseId") val interesseId: Int,
    @SerializedName("areaInteresse") val areaInteresse: String,
    @SerializedName("emailAprdz") val emailAprdz: String
)
