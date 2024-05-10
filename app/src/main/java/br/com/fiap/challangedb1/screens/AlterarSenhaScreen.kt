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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.challangedb1.R
import br.com.fiap.challangedb1.components.Botao
import br.com.fiap.challangedb1.components.CardTemplate
import br.com.fiap.challangedb1.components.InputBox
import br.com.fiap.challangedb1.components.MensagemErro
import br.com.fiap.challangedb1.components.TemplateScreen
import br.com.fiap.challangedb1.model.AprendizModel
import br.com.fiap.challangedb1.model.MentorModel
import br.com.fiap.challangedb1.service.RetrofitInstance
import br.com.fiap.challangedb1.util.validation.validacaoDropdown
import br.com.fiap.challangedb1.util.validation.validacaoEmail
import br.com.fiap.challangedb1.util.validation.validacaoNome
import br.com.fiap.challangedb1.util.validation.validacaoSenha
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun AlterarSenhaScreen(navController: NavController, tipoCadastro: String, email: String) {

    TemplateScreen(nomeTela = "Alterar Senha $tipoCadastro") {

        var nome by remember {
            mutableStateOf("")
        }
        var genero by remember {
            mutableStateOf("")
        }
        var senha1 by remember {
            mutableStateOf("")
        }
        var senha2 by remember {
            mutableStateOf("")
        }
        var erroCadastro by remember {
            mutableStateOf(false)
        }
        val context = LocalContext.current
        val apiService = RetrofitInstance.apiService
        var aprendiz by remember {
            mutableStateOf(AprendizModel(email, nome, genero, senha1, null))
        }
        var mentor by remember {
            mutableStateOf(MentorModel(email, nome, genero, senha1, null))
        }
        var aprendizAtualizado = AprendizModel(email, nome, genero, senha1, null)
        var mentorAtualizado = MentorModel(email, nome, genero, senha1, null)

        CardTemplate {
            Text(text = "Alteração de Senha",
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))

            //Formulário

            Column(modifier = Modifier.padding(horizontal = 12.dp)) {

                //Input para alteração da SENHA

                InputBox(
                    label = "Nova Senha",
                    placeholder = "Informe sua nova senha",
                    value = senha1,
                    kayboardType = KeyboardType.Password,
                    modifier = Modifier
                        .fillMaxWidth(),
                    updateValue = { senha1 = it },
                    visualTransformation = PasswordVisualTransformation(),
                    isError = false
                )
                if (!validacaoSenha(senha1) && erroCadastro) {
                    MensagemErro(
                        mensagem = "Senha deve possuir de 6 a 15 caracteres",
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.Normal,
                        spacer = 4.dp
                    )
                }

                //Input para confirmação da SENHA

                Spacer(modifier = Modifier.height(16.dp))
                InputBox(
                    label = "Confirme a Senha",
                    placeholder = "Informe novamente sua nova senha",
                    value = senha2,
                    kayboardType = KeyboardType.Password,
                    modifier = Modifier
                        .fillMaxWidth(),
                    updateValue = { senha2 = it },
                    visualTransformation = PasswordVisualTransformation(),
                    isError = false
                )
                if (!(senha2 == senha1) && erroCadastro) {
                    MensagemErro(
                        mensagem = "Senha deve ser idêntica ao campo anterior",
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.Normal,
                        spacer = 4.dp
                    )
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
                ){}
                Botao(
                    onClick = {
                        if (tipoCadastro == "Aprendiz") {
                            if (validacaoSenha(senha1) &&
                                senha1 == senha2
                            ) {
                                val call = apiService.getAprendizPorEmail(email)

                                call.enqueue(object : Callback<AprendizModel> {
                                    override fun onResponse(call: Call<AprendizModel>, response: Response<AprendizModel>) {
                                        if (response.isSuccessful) {
                                            aprendiz = response.body()!!
                                            aprendiz?.let { aprendiz ->
                                                nome = aprendiz.nomeAprdz
                                                genero = aprendiz.generoAprdz
                                            };
                                            aprendizAtualizado = AprendizModel(email, nome, genero, senha1, null)

                                            val callUpdate = apiService.atualizarAprdz(email, aprendizAtualizado)
                                            callUpdate.enqueue(object : Callback<AprendizModel> {
                                                override fun onResponse(callUpdate: Call<AprendizModel>, response: Response<AprendizModel>) {
                                                    if (response.isSuccessful) {
                                                        Toast.makeText(context, "Dados atualizados!", Toast.LENGTH_LONG).show();
                                                        navController.navigate("editarPerfil/$tipoCadastro/$email")
                                                    } else {
                                                        val errorBody = response.errorBody()?.string()
                                                        Log.e("TAG", "Erro na chamada à API: $errorBody")
                                                        Toast.makeText(context, "Ops, algo deu errado... Pode tentar de novo?", Toast.LENGTH_LONG).show();
                                                    }
                                                }

                                                override fun onFailure(call: Call<AprendizModel>, t: Throwable) {
                                                    Log.e("TAG", "Erro na chamada à API: ${t.message}")
                                                }
                                            })
                                        } else {
                                            val errorBody = response.errorBody()?.string()
                                            Toast.makeText(context, "Por favor tente novamente mais tarde.", Toast.LENGTH_LONG).show();
                                            Log.e("TAG", "Erro na chamada à API: $errorBody")
                                        }
                                    }
                                    override fun onFailure(call: Call<AprendizModel>, t: Throwable) {
                                        Toast.makeText(context, "Por favor tente novamente mais tarde.", Toast.LENGTH_LONG).show();
                                        Log.e("TAG", "Erro na chamada à API: ${t.message}")
                                    }
                                })
                            } else {
                                erroCadastro = true
                            }
                        } else if (tipoCadastro == "Mentor") {
                            if (validacaoSenha(senha1) &&
                                senha1 == senha2
                            ) {
                                val call = apiService.getMentorPorEmail(email)

                                call.enqueue(object : Callback<MentorModel> {
                                    override fun onResponse(call: Call<MentorModel>, response: Response<MentorModel>) {
                                        if (response.isSuccessful) {
                                            mentor = response.body()!!
                                            mentor?.let { mentor ->
                                                nome = mentor.nomeMentor
                                                genero = mentor.generoMentor
                                            };
                                            mentorAtualizado = MentorModel(email, nome, genero, senha1, null)

                                            val callUpdate = apiService.atualizarMentor(email, mentorAtualizado)
                                            callUpdate.enqueue(object : Callback<MentorModel> {
                                                override fun onResponse(callUpdate: Call<MentorModel>, response: Response<MentorModel>) {
                                                    if (response.isSuccessful) {
                                                        Toast.makeText(context, "Dados atualizados!", Toast.LENGTH_LONG).show();
                                                        navController.navigate("editarPerfil/$tipoCadastro/$email")
                                                    } else {
                                                        val errorBody = response.errorBody()?.string()
                                                        Log.e("TAG", "Erro na chamada à API: $errorBody")
                                                        Toast.makeText(context, "Ops, algo deu errado... Pode tentar de novo?", Toast.LENGTH_LONG).show();
                                                    }
                                                }

                                                override fun onFailure(call: Call<MentorModel>, t: Throwable) {
                                                    Log.e("TAG", "Erro na chamada à API: ${t.message}")
                                                }
                                            })
                                        } else {
                                            val errorBody = response.errorBody()?.string()
                                            Toast.makeText(context, "Por favor tente novamente mais tarde.", Toast.LENGTH_LONG).show();
                                            Log.e("TAG", "Erro na chamada à API: $errorBody")
                                        }
                                    }
                                    override fun onFailure(call: Call<MentorModel>, t: Throwable) {
                                        Toast.makeText(context, "Por favor tente novamente mais tarde.", Toast.LENGTH_LONG).show();
                                        Log.e("TAG", "Erro na chamada à API: ${t.message}")
                                    }
                                })
                            } else {
                                erroCadastro = true
                            }
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

            //Validação do formulário

            if (erroCadastro == true) {
                MensagemErro(
                    mensagem = "Preencha todos os campos do formulário",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    spacer = 36.dp
                )
            }
            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AlterarSenhaAprdzPreview() {
    AlterarSenhaScreen(rememberNavController(), "Mentor", "")
}