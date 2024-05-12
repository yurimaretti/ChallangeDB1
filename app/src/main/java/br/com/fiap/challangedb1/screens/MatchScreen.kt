package br.com.fiap.challangedb1.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.challangedb1.R
import br.com.fiap.challangedb1.components.Botao
import br.com.fiap.challangedb1.components.CardTemplate
import br.com.fiap.challangedb1.components.TemplateScreen
import br.com.fiap.challangedb1.model.AprendizModel
import br.com.fiap.challangedb1.model.MentorModel
import br.com.fiap.challangedb1.service.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun MatchScreen(navController: NavController, tipoCadastro: String, email: String) {

    val context = LocalContext.current
    val apiService = RetrofitInstance.apiService

    var mentores by remember {
        mutableStateOf(listOf<MentorModel>())
    }
    var aprendizes by remember {
        mutableStateOf(listOf<AprendizModel>())
    }

    TemplateScreen(nomeTela = "Matches do $tipoCadastro") {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
        ) {

            //Botão de consulta aos Mentores/Aprendizes

            if (tipoCadastro == "Aprendiz") {
                Botao(
                    onClick = {
                        val call = apiService.getMentor()

                        call.enqueue(object : Callback<List<MentorModel>> {
                            override fun onResponse(call: Call<List<MentorModel>>, response: Response<List<MentorModel>>) {
                                mentores = response.body()!!
                            }
                            override fun onFailure(call: Call<List<MentorModel>>, t: Throwable) {
                                Toast.makeText(context, "Por favor tente novamente mais tarde.", Toast.LENGTH_LONG).show();
                                Log.e("TAG", "Erro na chamada à API: ${t.message}")
                            }
                        })
                    },
                    texto = "Consultar Mentores",
                    cor = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.teal_700)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    enabled = true
                ) {
                    Image(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Perfil",
                        colorFilter = ColorFilter.tint(Color.White),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            } else if (tipoCadastro == "Mentor") {
                Botao(
                    onClick = {
                        val call = apiService.getAprendiz()

                        call.enqueue(object : Callback<List<AprendizModel>> {
                            override fun onResponse(call: Call<List<AprendizModel>>, response: Response<List<AprendizModel>>) {
                                aprendizes = response.body()!!
                            }
                            override fun onFailure(call: Call<List<AprendizModel>>, t: Throwable) {
                                Toast.makeText(context, "Por favor tente novamente mais tarde.", Toast.LENGTH_LONG).show();
                                Log.e("TAG", "Erro na chamada à API: ${t.message}")
                            }
                        })
                    },
                    texto = "Consultar Aprendizes",
                    cor = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.teal_700)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    enabled = true
                ) {
                    Image(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Perfil",
                        colorFilter = ColorFilter.tint(Color.White),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            //LazyRows dos Aprendizes / Mentores

            if (tipoCadastro == "Aprendiz") {
                LazyRow(modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                ){
                    items(mentores) {
                        CardMatchMentor(it)
                    }
                }
            } else if (tipoCadastro == "Mentor") {
                LazyRow(modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                ){
                    items(aprendizes) {
                        CardMatchAprdz(it)
                    }
                }
            }

            //Retorno à tela anterior

            Botao(
                onClick = { navController.navigate("inicio/$tipoCadastro/$email") },
                texto = "Voltar",
                cor = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.black)),
                modifier = Modifier.fillMaxWidth(),
                enabled = true
            ) {}
        }

    }
}

//Templates dos cards de Match dos Aprendizes e Mentores

@Composable
fun CardMatchAprdz(aprendiz: AprendizModel) {

    val interesses = aprendiz.interesse
    val formacoes = aprendiz.formacaoAprdz

    Column {
        CardTemplate() {
            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .width(240.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = aprendiz.nomeAprdz,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
                Row() {
                    Column() {
                        Column() {
                            Text(
                                text = "Gênero",
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 12.dp)
                            )
                            Text(text = aprendiz.generoAprdz, modifier = Modifier.padding(bottom = 12.dp))
                        }
                        Column() {
                            Text(
                                text = "Email para contato",
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 12.dp)
                            )
                            Text(text = aprendiz.emailAprdz, modifier = Modifier.padding(bottom = 12.dp))
                        }
                        Column() {
                            Text(
                                text = "Interesses",
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 12.dp)
                            )
                            if (!interesses.isNullOrEmpty()) {
                                interesses.forEach { interesse ->
                                    Text(
                                        text = "${interesse.areaInteresse}",
                                        modifier = Modifier.padding(bottom = 12.dp)
                                    )
                                }
                            } else {
                                Text(
                                    text = "Sem interesses registrados",
                                    modifier = Modifier.padding(bottom = 12.dp)
                                )
                            }
                        }
                        Column() {
                            Text(
                                text = "Formação",
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 12.dp)
                            )
                            if (!formacoes.isNullOrEmpty()) {
                                formacoes.forEach { formacao ->
                                    Text(
                                        text = "Nível: ${formacao.nivelFormAprdz}",
                                        modifier = Modifier.padding(bottom = 12.dp)
                                    )
                                    Text(
                                        text = "Curso: ${formacao.cursoAprdz}",
                                        modifier = Modifier.padding(bottom = 12.dp)
                                    )
                                    Text(
                                        text = "Instituição: ${formacao.instAprdz}",
                                        modifier = Modifier.padding(bottom = 12.dp)
                                    )
                                    Divider()
                                }
                            } else {
                                Text(text = "Sem formação registrada")
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Botao(
                            onClick = { /*TODO*/ },
                            texto = "Desfazer Match",
                            cor = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.purple_700)),
                            modifier = Modifier.fillMaxWidth(),
                            enabled = true
                        ) {
                            Image(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Desfazer",
                                colorFilter = ColorFilter.tint(Color.White),
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CardMatchMentor(mentor: MentorModel) {

    val habilidades = mentor.habilidade
    val formacoes = mentor.formacaoMentor

    Column {
        CardTemplate() {
            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .width(240.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = mentor.nomeMentor,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
                Row() {
                    Column() {
                        Column() {
                            Text(
                                text = "Gênero",
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 12.dp)
                            )
                            Text(text = mentor.generoMentor, modifier = Modifier.padding(bottom = 12.dp))
                        }
                        Column() {
                            Text(
                                text = "Email para contato",
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 12.dp)
                            )
                            Text(text = mentor.emailMentor, modifier = Modifier.padding(bottom = 12.dp))
                        }
                        Column() {
                            Text(
                                text = "Habilidades",
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 12.dp)
                            )
                            if (!habilidades.isNullOrEmpty()) {
                                habilidades.forEach { habilidade ->
                                    Text(
                                        text = "${habilidade.areaHabilidade}",
                                        modifier = Modifier.padding(bottom = 12.dp)
                                    )
                                }
                            } else {
                                Text(
                                    text = "Sem habilidades registradas",
                                    modifier = Modifier.padding(bottom = 12.dp)
                                )
                            }
                        }
                        Column() {
                            Text(
                                text = "Formação",
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 12.dp)
                            )
                            if (!formacoes.isNullOrEmpty()) {
                                formacoes.forEach { formacao ->
                                    Text(
                                        text = "Nível: ${formacao.nivelFormMentor}",
                                        modifier = Modifier.padding(bottom = 12.dp)
                                    )
                                    Text(
                                        text = "Curso: ${formacao.cursoMentor}",
                                        modifier = Modifier.padding(bottom = 12.dp)
                                    )
                                    Text(
                                        text = "Instituição: ${formacao.instMentor}",
                                        modifier = Modifier.padding(bottom = 12.dp)
                                    )
                                    Divider()
                                }
                            } else {
                                Text(text = "Sem formação registrada")
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Botao(
                            onClick = { /*TODO*/ },
                            texto = "Desfazer Match",
                            cor = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.purple_700)),
                            modifier = Modifier.fillMaxWidth(),
                            enabled = true
                        ) {
                            Image(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Desfazer",
                                colorFilter = ColorFilter.tint(Color.White),
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MatchAprdzPreview() {
    MatchScreen(rememberNavController(), "Mentor", "")
}