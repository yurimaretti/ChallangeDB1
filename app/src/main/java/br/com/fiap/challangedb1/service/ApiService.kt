package br.com.fiap.challangedb1.service

import br.com.fiap.challangedb1.model.AprendizModel
import br.com.fiap.challangedb1.model.MentorModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

//Endpoints para a tabela Aprendiz - T_DB1_APRENDIZ

    @POST("/api/Aprendiz")
    fun incluirAprdz(@Body dados: AprendizModel): Call<AprendizModel>

//Endpoints para a tabela Mentor - T_DB1_MENTOR

    @POST("/api/Mentor")
    fun incluirMentor(@Body dados: MentorModel): Call<MentorModel>
}