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
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import br.com.fiap.challangedb1.model.FormAprdzModel
import br.com.fiap.challangedb1.model.FormMentorModel
import br.com.fiap.challangedb1.service.RetrofitInstance
import br.com.fiap.challangedb1.service.RetrofitInstance.apiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarFormacaoScreen(navController: NavController, tipoCadastro: String, email: String) {

    TemplateScreen(nomeTela = "Editar Formação $tipoCadastro") {

        val context = LocalContext.current
        val apiService = RetrofitInstance.apiService
        var formAprdz by remember {
            mutableStateOf(listOf<FormAprdzModel>())
        }
        var formMentor by remember {
            mutableStateOf(listOf<FormMentorModel>())
        }

        //Botão de voltar

        Botao(
            onClick = { navController.navigate("editarPerfil/$tipoCadastro/$email") },
            texto = "Voltar",
            cor = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.black)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            enabled = true
        ) {}

        //Botão para incluir nova Formação

        Botao(
            onClick = { navController.navigate("formacao/$tipoCadastro/$email") },
            texto = "Incluir Nova Formação",
            cor = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.black)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            enabled = true
        ) {
            Image(
                imageVector = Icons.Default.AddCircle,
                contentDescription = "Formação",
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        //Botão de consulta às Formações

        if (tipoCadastro == "Aprendiz") {
            Botao(
                onClick = {
                    val call = apiService.getFormAprdzPorEmail(email)

                    call.enqueue(object : Callback<List<FormAprdzModel>> {
                        override fun onResponse(call: Call<List<FormAprdzModel>>, response: Response<List<FormAprdzModel>>) {
                            formAprdz = response.body()!!
                        }
                        override fun onFailure(call: Call<List<FormAprdzModel>>, t: Throwable) {
                            Toast.makeText(context, "Por favor tente novamente mais tarde.", Toast.LENGTH_LONG).show();
                            Log.e("TAG", "Erro na chamada à API: ${t.message}")
                        }
                    })
                },
                texto = "Consultar Formações",
                cor = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.teal_700)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
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
                    val call = apiService.getFormMentorPorEmail(email)

                    call.enqueue(object : Callback<List<FormMentorModel>> {
                        override fun onResponse(call: Call<List<FormMentorModel>>, response: Response<List<FormMentorModel>>) {
                            formMentor = response.body()!!
                        }
                        override fun onFailure(call: Call<List<FormMentorModel>>, t: Throwable) {
                            Toast.makeText(context, "Por favor tente novamente mais tarde.", Toast.LENGTH_LONG).show();
                            Log.e("TAG", "Erro na chamada à API: ${t.message}")
                        }
                    })
                },
                texto = "Consultar Formações",
                cor = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.teal_700)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
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

        //Lista de Formações Cadastradas

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
        ) {
            if (tipoCadastro == "Aprendiz") {
                LazyRow(modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                ) {
                    items(formAprdz) {
                        CardFormacaoAprdz(it, navController, email, tipoCadastro)
                    }
                }
            } else if (tipoCadastro == "Mentor") {
                LazyRow(modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                ) {
                    items(formMentor) {
                        CardFormacaoMentor(it, navController, email, tipoCadastro)
                    }
                }
            }
        }
    }
}

@Composable
fun CardFormacaoAprdz(formAprendiz: FormAprdzModel, navController: NavController, email: String, tipoCadastro: String) {

    val context = LocalContext.current

    Column(modifier = Modifier
        .width(330.dp)
    ) {
        CardTemplate() {
            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = formAprendiz.instAprdz,
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
                                text = "Nível de Ensino",
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 12.dp)
                            )
                            Text(text = formAprendiz.nivelFormAprdz, modifier = Modifier.padding(bottom = 12.dp))
                        }
                        Column() {
                            Text(
                                text = "Curso",
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 12.dp)
                            )
                            Text(text = formAprendiz.cursoAprdz, modifier = Modifier.padding(bottom = 12.dp))
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Botao(
                            onClick = {
                                val call = apiService.excluirFormAprdz(formAprendiz.formAprdzId)
                                call.enqueue(object : Callback<Void> {
                                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                                        if (response.isSuccessful) {
                                            navController.navigate("editarFormacao/$tipoCadastro/$email")
                                            Toast.makeText(context, "Formação excluída!", Toast.LENGTH_LONG).show();
                                        } else {
                                            val errorBody = response.errorBody()?.string()
                                            Log.e("TAG", "Erro na chamada à API: $errorBody")
                                            Toast.makeText(context, "Ops, algo deu errado... Pode tentar de novo?", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                    override fun onFailure(call: Call<Void>, t: Throwable) {
                                        Toast.makeText(context, "Por favor tente novamente mais tarde.", Toast.LENGTH_LONG).show();
                                        Log.e("TAG", "Erro na chamada à API: ${t.message}")
                                    }
                                })
                            },
                            texto = "Excluir Formação",
                            cor = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.danger)),
                            modifier = Modifier.fillMaxWidth(),
                            enabled = true
                        ) {
                            Image(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Excluir",
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
fun CardFormacaoMentor(formMentor: FormMentorModel, navController: NavController, email: String, tipoCadastro: String) {

    val context = LocalContext.current

    Column(modifier = Modifier
        .width(330.dp)
    ) {
        CardTemplate() {
            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = formMentor.instMentor,
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
                                text = "Nível de Ensino",
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 12.dp)
                            )
                            Text(text = formMentor.nivelFormMentor, modifier = Modifier.padding(bottom = 12.dp))
                        }
                        Column() {
                            Text(
                                text = "Curso",
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 12.dp)
                            )
                            Text(text = formMentor.cursoMentor, modifier = Modifier.padding(bottom = 12.dp))
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Botao(
                            onClick = {
                                val call = apiService.excluirFormMentor(formMentor.formMentorId)
                                call.enqueue(object : Callback<Void> {
                                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                                        if (response.isSuccessful) {
                                            navController.navigate("editarFormacao/$tipoCadastro/$email")
                                            Toast.makeText(context, "Formação excluída!", Toast.LENGTH_LONG).show();
                                        } else {
                                            val errorBody = response.errorBody()?.string()
                                            Log.e("TAG", "Erro na chamada à API: $errorBody")
                                            Toast.makeText(context, "Ops, algo deu errado... Pode tentar de novo?", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                    override fun onFailure(call: Call<Void>, t: Throwable) {
                                        Toast.makeText(context, "Por favor tente novamente mais tarde.", Toast.LENGTH_LONG).show();
                                        Log.e("TAG", "Erro na chamada à API: ${t.message}")
                                    }
                                })
                            },
                            texto = "Excluir Formação",
                            cor = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.danger)),
                            modifier = Modifier.fillMaxWidth(),
                            enabled = true
                        ) {
                            Image(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Excluir",
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
fun EditarFormacaoPreview() {
    EditarFormacaoScreen(rememberNavController(), "Mentor", "")
}