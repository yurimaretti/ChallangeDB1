package br.com.fiap.challangedb1.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.challangedb1.R
import br.com.fiap.challangedb1.components.Botao
import br.com.fiap.challangedb1.components.BotaoOutline
import br.com.fiap.challangedb1.components.Header
import br.com.fiap.challangedb1.components.InputBox

@Composable
fun LoginScreen() {

    var email by remember {
        mutableStateOf("")
    }
    var senha by remember {
        mutableStateOf("")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())

        ){
            Header(tela = "Login")
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .offset(y = (-10).dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(16.dp)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(modifier = Modifier
                    .padding(16.dp)
                ) {
                    Card(modifier = Modifier
                        .fillMaxWidth(),
                        colors = CardDefaults
                            .cardColors(colorResource(id = R.color.cor_card)),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Text(text = "Bem Vindo!",
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Column(modifier = Modifier.padding(horizontal = 12.dp)) {
                            InputBox(
                                label = "E-mail",
                                placeholder = "Informe seu e-mail",
                                value = email,
                                kayboardType = KeyboardType.Email,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                updateValue = { email = it },
                                visualTransformation = VisualTransformation.None
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            InputBox(
                                label = "Senha",
                                placeholder = "Informe sua senha",
                                value = senha,
                                kayboardType = KeyboardType.Password,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                updateValue = { senha = it },
                                visualTransformation = PasswordVisualTransformation()
                            )
                        }
                        Spacer(modifier = Modifier.height(48.dp))
                        Botao(
                            onClick = { /*TODO*/ },
                            texto = "Login",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp)
                        )
                        Spacer(modifier = Modifier.height(48.dp))
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
                            onClick = { /*TODO*/ },
                            texto = "Cadastro Aprendiz",
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        BotaoOutline(
                            onClick = { /*TODO*/ },
                            texto = "Cadastro Mentor",
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}