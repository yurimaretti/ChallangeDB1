package br.com.fiap.challangedb1.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import br.com.fiap.challangedb1.components.BotaoOutline
import br.com.fiap.challangedb1.components.BotoesAprendizMentor
import br.com.fiap.challangedb1.components.CardTemplate
import br.com.fiap.challangedb1.components.InputBox
import br.com.fiap.challangedb1.components.MensagemErro
import br.com.fiap.challangedb1.components.SeletorAprdzMentor
import br.com.fiap.challangedb1.components.TemplateScreen
import br.com.fiap.challangedb1.util.validation.validacaoEmail
import br.com.fiap.challangedb1.util.validation.validacaoSenha

@Composable
fun LoginScreen(navController: NavController) {

    var email by remember {
        mutableStateOf("")
    }
    var senha by remember {
        mutableStateOf("")
    }
    var tipoCadastro by remember {
        mutableStateOf("")
    }
    var erroCadastro by remember {
        mutableStateOf(false)
    }

    TemplateScreen(nomeTela = "Login") {
        CardTemplate {
            Text(text = "Bem Vindo!",
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))

            //Formulário Login

            Column(modifier = Modifier.padding(horizontal = 12.dp)) {

                //Input EMAIL e validação

                InputBox(
                    label = "E-mail",
                    placeholder = "Informe seu e-mail",
                    value = email,
                    kayboardType = KeyboardType.Email,
                    modifier = Modifier
                        .fillMaxWidth(),
                    updateValue = { email = it },
                    visualTransformation = VisualTransformation.None,
                    isError = false
                )
                if (!validacaoEmail(email) && erroCadastro) {
                    MensagemErro(
                        mensagem = "Endereço de e-mail inválido",
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
                    value = senha,
                    kayboardType = KeyboardType.Password,
                    modifier = Modifier
                        .fillMaxWidth(),
                    updateValue = { senha = it },
                    visualTransformation = PasswordVisualTransformation(),
                    isError = false
                )
                if (!validacaoSenha(senha) && erroCadastro) {
                    MensagemErro(
                        mensagem = "Senha deve possuir de 6 a 15 caracteres",
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.Normal,
                        spacer = 4.dp
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            //Seletor do tipoCadastro e botão login

            SeletorAprdzMentor(
                tipoCadastro = tipoCadastro,
                onClickAprendiz = { tipoCadastro = "Aprendiz" },
                onClickMentor = { tipoCadastro = "Mentor" }
            )
            Spacer(modifier = Modifier.height(36.dp))
            BotoesAprendizMentor(
                tipoCadastro = tipoCadastro,
                onClick = {
                    if (tipoCadastro == "Aprendiz") {
                        if (validacaoEmail(email) &&
                            validacaoSenha(senha)
                        ) {
                            navController.navigate("inicio/$tipoCadastro")
                            //TODO incluir API de validação do usuário e senha do Aprendiz, e acrescentar o email do usuário na rota
                        } else {
                            erroCadastro = true
                        }
                    } else if (tipoCadastro == "Mentor") {
                        if (validacaoEmail(email) &&
                            validacaoSenha(senha)
                        ) {
                            navController.navigate("inicio/$tipoCadastro")
                            //TODO incluir API de validação do usuário e senha do Aprendiz, e acrescentar o email do usuário na rota
                        } else {
                            erroCadastro = true
                        }
                    }
                },
                txtBotaoAprendiz = "Login Aprendiz",
                txtBotaoMentor = "Login Mentor",
                txtDisabled = "Login",
                modifier = Modifier
                    .fillMaxWidth()
            )

            //Validação do formulário

            if (erroCadastro == true) {
                MensagemErro(
                    mensagem = "Preencha login e senha",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    spacer = 36.dp
                )
            }

            Spacer(modifier = Modifier.height(48.dp))

            //Botão para cadastro

            Divider(
                color = Color.Black,
                thickness = 1.dp,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Ainda não é cadastrado?",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            BotaoOutline(
                onClick = { navController.navigate("cadastro") },
                texto = "Cadastre-se",
                modifier = Modifier.fillMaxWidth(),
                enabled = true
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(rememberNavController())
}