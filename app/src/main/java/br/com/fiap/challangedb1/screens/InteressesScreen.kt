package br.com.fiap.challangedb1.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import br.com.fiap.challangedb1.enums.AreaConhecimento
import br.com.fiap.challangedb1.model.AprendizModel
import br.com.fiap.challangedb1.model.FormMentorModel
import br.com.fiap.challangedb1.model.HabilidadeModel
import br.com.fiap.challangedb1.model.InteresseModel
import br.com.fiap.challangedb1.service.RetrofitInstance
import br.com.fiap.challangedb1.util.validation.validacaoDropdown
import br.com.fiap.challangedb1.util.validation.validacaoNome
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun InteressesScreen(navController: NavController, tipoCadastro: String, email: String) {

    //Função para nome variável da Tela

    fun nomeTela(): String {
        var nome = ""

        if (tipoCadastro == "Aprendiz") {
            nome = "Interesses"
        } else if (tipoCadastro == "Mentor") {
            nome = "Habilidades"
        }
        return nome
    }

    //Demais variáveis

    var areasSelecionadas by remember {
        mutableStateOf(emptySet<AreaConhecimento>())
    }
    val areasSelecionadasString = areasSelecionadas.joinToString(", ") { it.area }
    var interesse = InteresseModel(0, areasSelecionadasString, email)
    var habilidade = HabilidadeModel(0, areasSelecionadasString, email)
    val context = LocalContext.current
    val apiService = RetrofitInstance.apiService

    TemplateScreen(nomeTela = "${nomeTela()}") {
        CardTemplate {
            Text(
                text = "Editar ${nomeTela()}",
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))

            //Formulário

            Column(modifier = Modifier.padding(horizontal = 4.dp)) {
                AreaConhecimento.entries.forEach { area ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = areasSelecionadas.contains(area),
                            onCheckedChange = { isChecked ->
                                areasSelecionadas = if (isChecked) {
                                    areasSelecionadas + area
                                } else {
                                    areasSelecionadas - area
                                }
                            },
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Text(text = area.area)
                    }
                }
                Spacer(modifier = Modifier.height(48.dp))

                //Botões para voltar e salvar

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Botao(
                        onClick = { navController.navigate("editarPerfil/$tipoCadastro/$email") },
                        texto = "Cancelar",
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                            .width(120.dp)
                            .height(70.dp),
                        cor = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.danger)),
                        enabled = true
                    ) {}
                    Botao(
                        onClick = {
                            if (tipoCadastro == "Aprendiz") {
                                val call = apiService.getInteressePorEmail(email)

                                call.enqueue(object : Callback<InteresseModel> {
                                    override fun onResponse(call: Call<InteresseModel>, response: Response<InteresseModel>) {
                                        if (response.isSuccessful) {
                                            val respostaInteresse = response.body()
                                            var interesseAtualizado = InteresseModel(respostaInteresse!!.interesseId, areasSelecionadasString, email)
                                            val atualizar = apiService.atualizarInteresse(respostaInteresse!!.interesseId, interesseAtualizado)

                                            atualizar.enqueue(object : Callback<InteresseModel> {
                                                override fun onResponse(atualizar: Call<InteresseModel>, response: Response<InteresseModel>) {
                                                    if (response.isSuccessful) {
                                                        Toast.makeText(context, "Interesses atualizados!", Toast.LENGTH_LONG).show();
                                                        navController.navigate("editarPerfil/$tipoCadastro/$email")
                                                    } else {
                                                        val errorBody = response.errorBody()?.string()
                                                        Log.e("TAG", "Erro na chamada à API: $errorBody")
                                                        Toast.makeText(context, "Ops, algo deu errado... Pode tentar de novo?", Toast.LENGTH_LONG).show();
                                                    }
                                                }
                                                override fun onFailure(atualizar: Call<InteresseModel>, t: Throwable) {
                                                    Log.e("TAG", "Erro na chamada à API: ${t.message}")
                                                    Toast.makeText(context, "Ops, algo deu errado... Pode tentar de novo?", Toast.LENGTH_LONG).show();
                                                }
                                            })
                                        } else {
                                            val incluir = apiService.incluirInteresse(interesse)

                                            incluir.enqueue(object : Callback<InteresseModel> {
                                                override fun onResponse(incluir: Call<InteresseModel>, response: Response<InteresseModel>) {
                                                    if (response.isSuccessful) {
                                                        Toast.makeText(context, "Interesses incluídos!", Toast.LENGTH_LONG).show();
                                                        navController.navigate("editarPerfil/$tipoCadastro/$email")
                                                    } else {
                                                        val errorBody = response.errorBody()?.string()
                                                        Log.e("TAG", "Erro na chamada à API: $errorBody")
                                                        Toast.makeText(context, "Ops, algo deu errado... Pode tentar de novo?", Toast.LENGTH_LONG).show();
                                                    }
                                                }
                                                override fun onFailure(incluir: Call<InteresseModel>, t: Throwable) {
                                                    Log.e("TAG", "Erro na chamada à API: ${t.message}")
                                                    Toast.makeText(context, "Ops, algo deu errado... Pode tentar de novo?", Toast.LENGTH_LONG).show();
                                                }
                                            })
                                        }
                                    }
                                    override fun onFailure(call: Call<InteresseModel>, t: Throwable) {
                                        // Erro na chamada à API
                                        Log.e("TAG", "Erro na chamada à API: ${t.message}")
                                        Toast.makeText(context, "Erro na chamada à API: ${t.message}", Toast.LENGTH_LONG).show()
                                    }
                                })
                            } else if (tipoCadastro == "Mentor") {
                                val call = apiService.getHabilidadePorEmail(email)

                                call.enqueue(object : Callback<HabilidadeModel> {
                                    override fun onResponse(call: Call<HabilidadeModel>, response: Response<HabilidadeModel>) {
                                        if (response.isSuccessful) {
                                            val respostaHabilidade = response.body()
                                            var habilidadeAtualizado = HabilidadeModel(respostaHabilidade!!.habilidadeId, areasSelecionadasString, email)
                                            val atualizar = apiService.atualizarHabilidade(respostaHabilidade!!.habilidadeId, habilidadeAtualizado)

                                            atualizar.enqueue(object : Callback<HabilidadeModel> {
                                                override fun onResponse(atualizar: Call<HabilidadeModel>, response: Response<HabilidadeModel>) {
                                                    if (response.isSuccessful) {
                                                        Toast.makeText(context, "Habilidades atualizadas!", Toast.LENGTH_LONG).show();
                                                        navController.navigate("editarPerfil/$tipoCadastro/$email")
                                                    } else {
                                                        val errorBody = response.errorBody()?.string()
                                                        Log.e("TAG", "Erro na chamada à API: $errorBody")
                                                        Toast.makeText(context, "Ops, algo deu errado... Pode tentar de novo?", Toast.LENGTH_LONG).show();
                                                    }
                                                }
                                                override fun onFailure(atualizar: Call<HabilidadeModel>, t: Throwable) {
                                                    Log.e("TAG", "Erro na chamada à API: ${t.message}")
                                                    Toast.makeText(context, "Ops, algo deu errado... Pode tentar de novo?", Toast.LENGTH_LONG).show();
                                                }
                                            })
                                        } else {
                                            val incluir = apiService.incluirHabilidade(habilidade)

                                            incluir.enqueue(object : Callback<HabilidadeModel> {
                                                override fun onResponse(incluir: Call<HabilidadeModel>, response: Response<HabilidadeModel>) {
                                                    if (response.isSuccessful) {
                                                        Toast.makeText(context, "Habilidades incluídas!", Toast.LENGTH_LONG).show();
                                                        navController.navigate("editarPerfil/$tipoCadastro/$email")
                                                    } else {
                                                        val errorBody = response.errorBody()?.string()
                                                        Log.e("TAG", "Erro na chamada à API: $errorBody")
                                                        Toast.makeText(context, "Ops, algo deu errado... Pode tentar de novo?", Toast.LENGTH_LONG).show();
                                                    }
                                                }
                                                override fun onFailure(incluir: Call<HabilidadeModel>, t: Throwable) {
                                                    Log.e("TAG", "Erro na chamada à API: ${t.message}")
                                                    Toast.makeText(context, "Ops, algo deu errado... Pode tentar de novo?", Toast.LENGTH_LONG).show();
                                                }
                                            })
                                        }
                                    }
                                    override fun onFailure(call: Call<HabilidadeModel>, t: Throwable) {
                                        // Erro na chamada à API
                                        Log.e("TAG", "Erro na chamada à API: ${t.message}")
                                        Toast.makeText(context, "Erro na chamada à API: ${t.message}", Toast.LENGTH_LONG).show()
                                    }
                                })
                            }
                        },
                        texto = "Salvar",
                        cor = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.black)),
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                            .width(120.dp)
                            .height(70.dp),
                        enabled = true
                    ) {}
                }
                Spacer(modifier = Modifier.height(48.dp))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun InteressesPreview() {
    InteressesScreen(rememberNavController(), "Aprendiz", "")
}