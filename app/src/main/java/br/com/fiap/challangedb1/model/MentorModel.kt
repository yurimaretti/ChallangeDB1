package br.com.fiap.challangedb1.model

import com.google.gson.annotations.SerializedName

data class MentorModel(
    @SerializedName("emailMentor") val emailMentor: String,
    @SerializedName("nomeMentor") val nomeMentor: String,
    @SerializedName("generoMentor") val generoMentor: String,
    @SerializedName("senhaMentor") val senhaMentor: String,
    @SerializedName("formacaoMentor") val formacaoMentor: List<FormMentorModel>?,
    @SerializedName("habilidade") val habilidade: List<HabilidadeModel>?,
    @SerializedName("match") val match: List<MatchModel>?
)
