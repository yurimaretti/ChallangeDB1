package br.com.fiap.challangedb1.service

import br.com.fiap.challangedb1.model.AprendizModel
import br.com.fiap.challangedb1.model.FormAprdzModel
import br.com.fiap.challangedb1.model.FormMentorModel
import br.com.fiap.challangedb1.model.HabilidadeModel
import br.com.fiap.challangedb1.model.InteresseModel
import br.com.fiap.challangedb1.model.MentorModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
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

//Endpoints para a tabela Formação Aprendiz - T_DB1_FORM_APRDZ

    @POST("/api/FormacaoAprendiz")
    fun incluirFormAprdz(@Body dados: FormAprdzModel): Call<FormAprdzModel>

    @GET("/api/FormacaoAprendiz/{emailAprendiz}")
    fun getFormAprdzPorEmail(@Path("emailAprendiz") emailAprendiz: String): Call<List<FormAprdzModel>>

    @DELETE("/api/FormacaoAprendiz/{id}")
    fun excluirFormAprdz(@Path("id") id: Int): Call<Void>

//Endpoints para a tabela Formação Mentor - T_DB1_FORM_MENTOR

    @POST("/api/FormacaoMentor")
    fun incluirFormMentor(@Body dados: FormMentorModel): Call<FormMentorModel>

    @GET("/api/FormacaoMentor/{emailMentor}")
    fun getFormMentorPorEmail(@Path("emailMentor") emailAprendiz: String): Call<List<FormMentorModel>>

    @DELETE("/api/FormacaoMentor/{id}")
    fun excluirFormMentor(@Path("id") id: Int): Call<Void>

//Endpoints para a tabela Habilidade - T_DB1_HABILIDADES

    @POST("/api/Habilidade")
    fun incluirHabilidade(@Body dados: HabilidadeModel): Call<HabilidadeModel>

    @GET("/api/Habilidade/{emailMentor}")
    fun getHabilidadePorEmail(@Path("emailMentor") emailMentor: String): Call<HabilidadeModel>

    @PUT("/api/Habilidade/{habilidadeId}")
    fun atualizarHabilidade(@Path("habilidadeId") habilidadeId: Int, @Body dados: HabilidadeModel): Call<HabilidadeModel>

//Endpoints para a tabela Interesse - T_DB1_INTERESSES

    @POST("/api/Interesse")
    fun incluirInteresse(@Body dados: InteresseModel): Call<InteresseModel>

    @GET("/api/Interesse/{emailAprdz}")
    fun getInteressePorEmail(@Path("emailAprdz") emailAprdz: String): Call<InteresseModel>

    @PUT("/api/Interesse/{interesseId}")
    fun atualizarInteresse(@Path("interesseId") interesseId: Int, @Body dados: InteresseModel): Call<InteresseModel>
}