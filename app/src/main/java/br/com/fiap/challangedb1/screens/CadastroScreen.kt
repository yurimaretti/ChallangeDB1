package br.com.fiap.challangedb1.screens

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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.challangedb1.R
import br.com.fiap.challangedb1.components.Botao
import br.com.fiap.challangedb1.components.BotoesAprendizMentor
import br.com.fiap.challangedb1.components.CardTemplate
import br.com.fiap.challangedb1.components.InputBox
import br.com.fiap.challangedb1.components.MensagemErro
import br.com.fiap.challangedb1.components.SeletorAprdzMentor
import br.com.fiap.challangedb1.components.TemplateScreen
import br.com.fiap.challangedb1.enums.Generos
import br.com.fiap.challangedb1.util.validation.validacaoEmail
import br.com.fiap.challangedb1.util.validation.validacaoGenero
import br.com.fiap.challangedb1.util.validation.validacaoNome
import br.com.fiap.challangedb1.util.validation.validacaoSenha

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CadastroScreen(navController: NavController) {

    TemplateScreen(nomeTela = "Cadastre-se") {

        var nome by remember {
            mutableStateOf("")
        }
        var email by remember {
            mutableStateOf("")
        }
        var genero by remember {
            mutableStateOf("")
        }
        var isExpanded by remember {
            mutableStateOf(false)
        }
        var lista = Generos.values()
        var senha1 by remember {
            mutableStateOf("")
        }
        var senha2 by remember {
            mutableStateOf("")
        }
        var tipoCadastro by remember {
            mutableStateOf("")
        }
        var erroCadastro by remember {
            mutableStateOf(false)
        }

        CardTemplate {
            Text(text = "Cadastro",
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))

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
                    isError = !validacaoNome(nome)
                )
                if (!validacaoNome(nome) && erroCadastro) {
                    MensagemErro(
                        mensagem = "Nome deve possuir de 2 a 70 caracteres",
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.Normal,
                        spacer = 4.dp
                    )
                }

                //Input EMAIL e validação

                Spacer(modifier = Modifier.height(16.dp))
                InputBox(
                    label = "E-mail",
                    placeholder = "Informe seu e-mail",
                    value = email,
                    kayboardType = KeyboardType.Email,
                    modifier = Modifier
                        .fillMaxWidth(),
                    updateValue = { email = it },
                    visualTransformation = VisualTransformation.None,
                    isError = !validacaoEmail(email)
                )
                if (!validacaoEmail(email) && erroCadastro) {
                    MensagemErro(
                        mensagem = "Endereço de e-mail inválido",
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.Normal,
                        spacer = 4.dp
                    )
                }

                //Input GÊNERO (com dropdown) e validação

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
                        isError = !validacaoGenero(genero)
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
                if (!validacaoGenero(genero) && erroCadastro) {
                    MensagemErro(
                        mensagem = "Genêro deve ser preenchido",
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.Normal,
                        spacer = 4.dp
                    )
                }

                //Input SENHA e validação

                Spacer(modifier = Modifier.height(16.dp))
                InputBox(
                    label = "Senha",
                    placeholder = "Informe sua senha",
                    value = senha1,
                    kayboardType = KeyboardType.Password,
                    modifier = Modifier
                        .fillMaxWidth(),
                    updateValue = { senha1 = it },
                    visualTransformation = PasswordVisualTransformation(),
                    isError = !validacaoSenha(senha1)
                )
                if (!validacaoSenha(senha1) && erroCadastro) {
                    MensagemErro(
                        mensagem = "Senha deve possuir de 6 a 15 caracteres",
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.Normal,
                        spacer = 4.dp
                    )
                }

                //Input CONFIRMA SENHA e validação

                Spacer(modifier = Modifier.height(16.dp))
                InputBox(
                    label = "Repita a Senha",
                    placeholder = "Informe novamente sua senha",
                    value = senha2,
                    kayboardType = KeyboardType.Password,
                    modifier = Modifier
                        .fillMaxWidth(),
                    updateValue = { senha2 = it },
                    visualTransformation = PasswordVisualTransformation(),
                    isError = !(senha2 == senha1)
                )
                if (!(senha2 == senha1) && erroCadastro) {
                    MensagemErro(
                        mensagem = "Senha deve ser idêntica ao campo anterior",
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.Normal,
                        spacer = 4.dp
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                //Tipo do cadastro

                Text(
                    text = "Tipo de Cadastro",
                    modifier = Modifier.padding(start = 8.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                SeletorAprdzMentor(
                    tipoCadastro = tipoCadastro,
                    onClickAprendiz = { tipoCadastro = "Aprendiz" },
                    onClickMentor = { tipoCadastro = "Mentor" }
                )
            }
            Spacer(modifier = Modifier.height(48.dp))

            //Botões de retorno ou salvar

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Botao(
                    onClick = { navController.navigate("login") },
                    texto = "Cancelar",
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .width(120.dp)
                        .height(70.dp),
                    cor = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.danger)),
                    enabled = true
                ){}
                BotoesAprendizMentor(
                    tipoCadastro = tipoCadastro,
                    onClick = {
                        if (tipoCadastro == "Aprendiz") {
                            if (validacaoNome(nome) &&
                                validacaoEmail(email) &&
                                validacaoSenha(senha1) &&
                                validacaoGenero(genero) &&
                                senha1 == senha2
                            ) {
                                //TODO rota da API para cadastrar Aprendiz
                                navController.navigate("login")
                            } else {
                                erroCadastro = true
                            }
                        } else if (tipoCadastro == "Mentor") {
                            if (validacaoNome(nome) &&
                                validacaoEmail(email) &&
                                validacaoSenha(senha1) &&
                                validacaoGenero(genero) &&
                                senha1 == senha2
                            ) {
                                //TODO rota da API para cadastrar Mentor
                                navController.navigate("login")
                            } else {
                                erroCadastro = true
                            }
                        }
                    },
                    txtBotaoAprendiz = "Cadastrar Aprendiz",
                    txtBotaoMentor = "Cadastrar Mentor",
                    txtDisabled = "Cadastrar",
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .width(120.dp)
                        .height(70.dp)
                )
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
fun CadastroAprendizPreview() {
    CadastroScreen(rememberNavController())
}
