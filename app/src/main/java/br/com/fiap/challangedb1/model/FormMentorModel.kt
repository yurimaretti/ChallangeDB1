package br.com.fiap.challangedb1.model

import com.google.gson.annotations.SerializedName

data class FormMentorModel(
    @SerializedName("formMentorId") val formMentorId: Int,
    @SerializedName("nivelFormMentor") val nivelFormMentor: String,
    @SerializedName("cursoMentor") val cursoMentor: String,
    @SerializedName("instMentor") val instMentor: String,
    @SerializedName("emailMentor") val emailMentor: String
)
