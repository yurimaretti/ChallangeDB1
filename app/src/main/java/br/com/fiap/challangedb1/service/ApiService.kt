package br.com.fiap.challangedb1.service

import br.com.fiap.challangedb1.model.AprendizModel
import br.com.fiap.challangedb1.model.MentorModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    //APIs para cadastro do Aprendiz

    @POST("/api/v1/cadastroAprdz")
    fun incluirAprdz(@Body dados: AprendizModel): Call<AprendizModel>

    //APIs para cadastro do Mentor

    @POST("/api/v1/cadastroMentor")
    fun incluirMentor(@Body dados: MentorModel): Call<MentorModel>
}