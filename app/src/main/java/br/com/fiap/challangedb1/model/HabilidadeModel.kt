package br.com.fiap.challangedb1.model

import com.google.gson.annotations.SerializedName

data class HabilidadeModel(
    @SerializedName("habilidadeId") val habilidadeId: Int,
    @SerializedName("areaHabilidade") val areaHabilidade: String,
    @SerializedName("emailMentor") val emailMentor: String
)
