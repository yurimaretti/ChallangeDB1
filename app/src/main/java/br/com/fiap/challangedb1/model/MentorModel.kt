package br.com.fiap.challangedb1.model

import com.google.gson.annotations.SerializedName

data class MentorModel(
    @SerializedName("email_mentor") val emailMentor: String,
    @SerializedName("nome_mentor") val nomeMentor: String,
    @SerializedName("genero_mentor") val generoMentor: String,
    @SerializedName("senha_mentor") val senhaMentor: String
)
