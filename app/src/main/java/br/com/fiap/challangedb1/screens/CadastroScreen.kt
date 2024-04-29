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
import br.com.fiap.challangedb1.components.SeletorAprdzMentor
import br.com.fiap.challangedb1.components.TemplateScreen
import br.com.fiap.challangedb1.enums.Generos
import br.com.fiap.challangedb1.util.validation.validacaoGenero
import br.com.fiap.challangedb1.util.validation.validacaoNomeEmail
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
                InputBox(
                    label = "Nome",
                    placeholder = "Informe seu nome",
                    value = nome,
                    kayboardType = KeyboardType.Text,
                    modifier = Modifier
                        .fillMaxWidth(),
                    updateValue = { nome = it },
                    visualTransformation = VisualTransformation.None,
                    isError = !validacaoNomeEmail(nome)
                )
                if (!validacaoNomeEmail(nome)) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Nome deve possuir de 2 a 70 caracteres",
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        textAlign = TextAlign.End,
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                }
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
                    isError = !validacaoNomeEmail(email)
                )
                if (!validacaoNomeEmail(email)) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "E-mail deve possuir de 2 a 70 caracteres",
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        textAlign = TextAlign.End,
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Gênero",
                    modifier = Modifier.padding(start = 8.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))

                //Dropdown

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
                if (!validacaoGenero(genero)) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Genêro deve ser preenchido",
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        textAlign = TextAlign.End,
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                }
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
                if (!validacaoSenha(senha1)) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Senha deve possuir de 6 a 15 caracteres",
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        textAlign = TextAlign.End,
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                }
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
                if (!(senha2 == senha1)) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Senha deve ser idêntica ao campo anterior",
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        textAlign = TextAlign.End,
                        color = Color.Red,
                        fontSize = 12.sp
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
                            if (validacaoNomeEmail(nome) && validacaoNomeEmail(email) && validacaoSenha(senha1) && validacaoGenero(genero) && senha1 == senha2) {
                                //TODO rota da API para cadastrar Aprendiz
                                navController.navigate("login")
                            } else {
                                erroCadastro = true
                            }
                        } else if (tipoCadastro == "Mentor") {
                            if (validacaoNomeEmail(nome) && validacaoNomeEmail(email) && validacaoSenha(senha1) && validacaoGenero(genero) && senha1 == senha2) {
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
            if (erroCadastro == true) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Preencha todos os campos do formulário",
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    textAlign = TextAlign.Center,
                    color = Color.Red,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
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
