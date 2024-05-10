package br.com.fiap.challangedb1.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.challangedb1.R
import br.com.fiap.challangedb1.components.Botao
import br.com.fiap.challangedb1.components.CardTemplate
import br.com.fiap.challangedb1.components.InputBox
import br.com.fiap.challangedb1.components.MensagemErro
import br.com.fiap.challangedb1.components.TemplateScreen
import br.com.fiap.challangedb1.enums.Generos
import br.com.fiap.challangedb1.model.AprendizModel
import br.com.fiap.challangedb1.model.MentorModel
import br.com.fiap.challangedb1.service.RetrofitInstance
import br.com.fiap.challangedb1.util.validation.validacaoDropdown
import br.com.fiap.challangedb1.util.validation.validacaoNome
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarPerfilScreen(navController: NavController, tipoCadastro: String, email: String) {

    TemplateScreen(nomeTela = "Perfil $tipoCadastro") {

        var nome by remember {
            mutableStateOf("")
        }
        var genero by remember {
            mutableStateOf("")
        }
        var senha by remember {
            mutableStateOf("")
        }
        var area by remember {
            mutableStateOf("")
        }
        var isExpanded by remember {
            mutableStateOf(false)
        }
        val lista = Generos.entries
        var erroCadastro by remember {
            mutableStateOf(false)
        }
        val context = LocalContext.current
        val apiService = RetrofitInstance.apiService
        var aprendiz by remember {
            mutableStateOf(AprendizModel(email, nome, genero, senha, null, null))
        }
        var mentor by remember {
            mutableStateOf(MentorModel(email, nome, genero, senha, null, null))
        }
        var aprendizAtualizado = AprendizModel(email, nome, genero, senha, null, null)
        var mentorAtualizado = MentorModel(email, nome, genero, senha, null, null)

        CardTemplate {
            //Botão de consulta aos Mentores/Aprendizes

            if (tipoCadastro == "Aprendiz") {
                Botao(
                    onClick = {
                        val call = apiService.getAprendizPorEmail(email)

                        call.enqueue(object : Callback<AprendizModel> {
                            override fun onResponse(call: Call<AprendizModel>, response: Response<AprendizModel>) {
                                if (response.isSuccessful) {
                                    aprendiz = response.body()!!
                                    aprendiz?.let { aprendiz ->
                                        nome = aprendiz.nomeAprdz
                                        genero = aprendiz.generoAprdz
                                        senha = aprendiz.senhaAprdz
                                    };
                                    val interesses = aprendiz.interesse
                                    if (!interesses.isNullOrEmpty()) {
                                        for (interesse in interesses) {
                                            area = interesse.areaInteresse
                                        }
                                    } else {
                                        // Caso não haja interesses
                                        Log.d("TAG", "Não há interesses associados ao aprendiz")
                                    }
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
                    },
                    texto = "Consultar dados para edição",
                    cor = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.teal_700)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
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
                        val call = apiService.getMentorPorEmail(email)

                        call.enqueue(object : Callback<MentorModel> {
                            override fun onResponse(call: Call<MentorModel>, response: Response<MentorModel>) {
                                if (response.isSuccessful) {
                                    mentor = response.body()!!
                                    mentor?.let { mentor ->
                                        nome = mentor.nomeMentor
                                        genero = mentor.generoMentor
                                        senha = mentor.senhaMentor
                                    };
                                    val habilidades = mentor.habilidade
                                    if (!habilidades.isNullOrEmpty()) {
                                        for (habilidade in habilidades) {
                                            area = habilidade.areaHabilidade
                                        }
                                    } else {
                                        // Caso não haja interesses
                                        Log.d("TAG", "Não há interesses associados ao aprendiz")
                                    }
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
                    },
                    texto = "Consultar dados para edição",
                    cor = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.teal_700)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
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

            //Formulário

            Column(modifier = Modifier.padding(horizontal = 12.dp)) {

                //Input NOME e validação

                InputBox(
                    label = "Nome",
                    placeholder = "Informe seu nome",
                    value = nome,
                    kayboardType = KeyboardType.Text,
                    modifier = Modifier
                        .fillMaxWidth(),
                    updateValue = { nome = it },
                    visualTransformation = VisualTransformation.None,
                    isError = false
                )
                if (!validacaoNome(nome) && erroCadastro) {
                    MensagemErro(
                        mensagem = "Nome deve possuir de 2 a 70 caracteres",
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.Normal,
                        spacer = 4.dp
                    )
                }

                //Input GÊNERO e validação

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Gênero",
                    modifier = Modifier.padding(start = 8.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                ExposedDropdownMenuBox(
                    expanded = isExpanded,
                    onExpandedChange = { isExpanded = it },
                    modifier = Modifier.background(Color.White)
                ) {
                    OutlinedTextField(
                        value = genero,
                        onValueChange = {},
                        trailingIcon = {
                            ExposedDropdownMenuDefaults
                                .TrailingIcon(expanded = isExpanded)
                        },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth(),
                        readOnly = true,
                        colors = ExposedDropdownMenuDefaults.textFieldColors(),
                        shape = RoundedCornerShape(16.dp),
                        isError = false
                    )
                    ExposedDropdownMenu(
                        expanded = isExpanded,
                        onDismissRequest = { isExpanded = !isExpanded },
                        modifier = Modifier
                            .background(Color.White)
                    ) {
                        lista.forEach{
                            DropdownMenuItem(
                                text = {
                                    Text(text = it.genero)
                                },
                                onClick = {
                                    genero = it.genero
                                    isExpanded = !isExpanded
                                }
                            )
                        }
                    }
                }
                if (!validacaoDropdown(genero) && erroCadastro) {
                    MensagemErro(
                        mensagem = "Genêro deve ser preenchido",
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.Normal,
                        spacer = 4.dp
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                if (tipoCadastro == "Aprendiz") {
                    Text(
                        text = "Interesses",
                        modifier = Modifier.padding(start = 8.dp)
                    )
                } else if (tipoCadastro == "Mentor") {
                    Text(
                        text = "Habilidades",
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = area,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            //Botão para editar HABILIDADES/INTERESSES

            Spacer(modifier = Modifier.height(32.dp))

            if (tipoCadastro == "Aprendiz") {
                Botao(
                    onClick = { navController.navigate("interesses/$tipoCadastro/$email") },
                    texto = "Editar Interesses",
                    cor = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.black)),
                    modifier = Modifier.fillMaxWidth(),
                    enabled = true
                ) {
                    Image(
                        imageVector = Icons.Default.AddCircle,
                        contentDescription = "Interesses",
                        colorFilter = ColorFilter.tint(Color.White),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            } else if (tipoCadastro == "Mentor") {
                Botao(
                    onClick = { navController.navigate("interesses/$tipoCadastro/$email") },
                    texto = "Editar Habilidades",
                    cor = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.black)),
                    modifier = Modifier.fillMaxWidth(),
                    enabled = true
                ) {
                    Image(
                        imageVector = Icons.Default.AddCircle,
                        contentDescription = "Interesses",
                        colorFilter = ColorFilter.tint(Color.White),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }

            //Botão para editar FORMAÇÃO

            Spacer(modifier = Modifier.height(16.dp))
            Botao(
                onClick = { navController.navigate("editarFormacao/$tipoCadastro/$email") },
                texto = "Editar Formação",
                cor = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.black)),
                modifier = Modifier.fillMaxWidth(),
                enabled = true
            ) {
                Image(
                    imageVector = Icons.Default.Create,
                    contentDescription = "Formação",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            //Botão para editar SENHA

            Spacer(modifier = Modifier.height(16.dp))
            Botao(
                onClick = { navController.navigate("alterarSenha/$tipoCadastro/$email") },
                texto = "Alterar Senha",
                cor = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.black)),
                modifier = Modifier.fillMaxWidth(),
                enabled = true
            ) {
                Image(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Senha",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(48.dp))

            //Botões para voltar e salvar

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Botao(
                    onClick = { navController.navigate("inicio/$tipoCadastro/$email") },
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
                            if (validacaoNome(nome) &&
                                validacaoDropdown(genero)
                            ) {
                                aprendizAtualizado = AprendizModel(email, nome, genero, senha, null, null)

                                val call = apiService.atualizarAprdz(email, aprendizAtualizado)
                                call.enqueue(object : Callback<AprendizModel> {
                                    override fun onResponse(call: Call<AprendizModel>, response: Response<AprendizModel>) {
                                        if (response.isSuccessful) {
                                            Toast.makeText(context, "Dados atualizados!", Toast.LENGTH_LONG).show();
                                            navController.navigate("inicio/$tipoCadastro/$email")
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
                                erroCadastro = true
                            }
                        } else if (tipoCadastro == "Mentor") {
                            if (validacaoNome(nome) &&
                                validacaoDropdown(genero)
                            ) {
                                mentorAtualizado = MentorModel(email, nome, genero, senha, null, null)

                                val call = apiService.atualizarMentor(email, mentorAtualizado)
                                call.enqueue(object : Callback<MentorModel> {
                                    override fun onResponse(call: Call<MentorModel>, response: Response<MentorModel>) {
                                        if (response.isSuccessful) {
                                            Toast.makeText(context, "Dados atualizados!", Toast.LENGTH_LONG).show();
                                            navController.navigate("inicio/$tipoCadastro/$email")
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
                    mensagem = "Nome e Gênero devem estar preenchidos",
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
fun EditarPerfilAprdzPreview() {
    EditarPerfilScreen(rememberNavController(), "Mentor", "")
}