package br.com.fiap.challangedb1.model

import com.google.gson.annotations.SerializedName

data class AprendizModel(
    @SerializedName("email_aprdz") val emailAprdz: String,
    @SerializedName("nome_aprdz") val nomeAprdz: String,
    @SerializedName("genero_aprdz") val generoAprdz: String,
    @SerializedName("senha_aprdz") val senhaAprdz: String
)
