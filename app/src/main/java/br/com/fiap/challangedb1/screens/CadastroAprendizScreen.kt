package br.com.fiap.challangedb1.screens

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
import br.com.fiap.challangedb1.components.CardTemplate
import br.com.fiap.challangedb1.components.InputBox
import br.com.fiap.challangedb1.components.TemplateScreen

@Composable
fun CadastroAprendizScreen(navController: NavController) {

    TemplateScreen(nomeTela = "Cadastro Aprendiz") {

        var nome by remember {
            mutableStateOf("")
        }
        var email by remember {
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

        CardTemplate {
            Text(text = "Cadastro Aprendiz",
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Column(modifier = Modifier.padding(horizontal = 12.dp)) {
                InputBox(
                    label = "Nome",
                    placeholder = "Informe seu nome",
                    value = nome,
                    kayboardType = KeyboardType.Text,
                    modifier = Modifier
                        .fillMaxWidth(),
                    updateValue = { nome = it },
                    visualTransformation = VisualTransformation.None
                )
                Spacer(modifier = Modifier.height(16.dp))
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
                    label = "Gênero",
                    placeholder = "Informe seu gênero",
                    value = genero,
                    kayboardType = KeyboardType.Text,
                    modifier = Modifier
                        .fillMaxWidth(),
                    updateValue = { genero = it },
                    visualTransformation = VisualTransformation.None
                )
                Spacer(modifier = Modifier.height(16.dp))
                InputBox(
                    label = "Senha",
                    placeholder = "Informe sua senha",
                    value = senha1,
                    kayboardType = KeyboardType.Password,
                    modifier = Modifier
                        .fillMaxWidth(),
                    updateValue = { senha1 = it },
                    visualTransformation = PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.height(16.dp))
                InputBox(
                    label = "Repita a Senha",
                    placeholder = "Informe novamente sua senha",
                    value = senha2,
                    kayboardType = KeyboardType.Password,
                    modifier = Modifier
                        .fillMaxWidth(),
                    updateValue = { senha2 = it },
                    visualTransformation = PasswordVisualTransformation()
                )
            }
            Spacer(modifier = Modifier.height(48.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Botao(
                    onClick = { navController.navigate("login") },
                    texto = "Cancelar",
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .width(120.dp),
                    cor = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.danger))
                )
                Botao(
                    onClick = { navController.navigate("login") },
                    texto = "Cadastrar",
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .width(120.dp),
                    cor = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.black))
                )
            }
            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CadastroAprendizPreview() {
    CadastroAprendizScreen(rememberNavController())
}
