package br.com.fiap.challangedb1.service

import br.com.fiap.challangedb1.model.AprendizModel
import br.com.fiap.challangedb1.model.MentorModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

//Endpoints para a tabela Aprendiz - T_DB1_APRENDIZ

    @POST("/api/Aprendiz")
    fun incluirAprdz(@Body dados: AprendizModel): Call<AprendizModel>

    @GET("/api/Aprendiz")
    fun getAprendiz(): Call<List<AprendizModel>>

    @GET("/api/Aprendiz/{emailAprendiz}")
    fun getAprendizPorEmail(@Path("emailAprendiz") emailAprendiz: String): Call<AprendizModel>

    @PUT("/api/Aprendiz/{emailAprendiz}")
    fun atualizarAprdz(@Path("emailAprendiz") emailAprendiz: String, @Body dados: AprendizModel): Call<AprendizModel>

//Endpoints para a tabela Mentor - T_DB1_MENTOR

    @POST("/api/Mentor")
    fun incluirMentor(@Body dados: MentorModel): Call<MentorModel>

    @GET("/api/Mentor")
    fun getMentor(): Call<List<MentorModel>>

    @GET("/api/Mentor/{emailMentor}")
    fun getMentorPorEmail(@Path("emailMentor") emailMentor: String): Call<MentorModel>

    @PUT("/api/Mentor/{emailMentor}")
    fun atualizarMentor(@Path("emailMentor") emailMentor: String, @Body dados: MentorModel): Call<MentorModel>
}