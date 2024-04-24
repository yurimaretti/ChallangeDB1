package br.com.fiap.challangedb1.screens

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
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
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
import br.com.fiap.challangedb1.enums.Generos

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarPerfilAprdzScreen(navController: NavController) {

    TemplateScreen(nomeTela = "Editar Perfil") {

        var nome by remember {
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

        var direito by remember {
            mutableStateOf(false)
        }
        var tecnologia by remember {
            mutableStateOf(false)
        }
        var engenharia by remember {
            mutableStateOf(false)
        }

        CardTemplate {
            Text(text = "Editar",
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
                        shape = RoundedCornerShape(16.dp)
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
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Áreas de Interesse:",
                modifier = Modifier.padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            //INÍCIO - Mock de áreas de interesse

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = tecnologia,
                    onCheckedChange = { tecnologia = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0, 0, 0, 255),
                        uncheckedColor = Color.Gray
                    )
                )
                Text(text = "Tecnologia", color = Color.Black, fontWeight = FontWeight.ExtraBold)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = direito,
                    onCheckedChange = { direito = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0, 0, 0, 255),
                        uncheckedColor = Color.Gray
                    )
                )
                Text(text = "Direito", color = Color.Black, fontWeight = FontWeight.ExtraBold)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = engenharia,
                    onCheckedChange = { engenharia = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0, 0, 0, 255),
                        uncheckedColor = Color.Gray
                    )
                )
                Text(text = "Engenharia", color = Color.Black, fontWeight = FontWeight.ExtraBold)
            }

            //FIM - Mock de áreas de interesse

            Spacer(modifier = Modifier.height(16.dp))
            Botao(
                onClick = { /*TODO*/ },
                texto = "Incluir Formação",
                cor = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.black)),
                modifier = Modifier.fillMaxWidth(),
                enabled = true
            ) {
                Image(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Formação",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Botao(
                onClick = { navController.navigate("alterarSenhaAprendiz") },
                texto = "Alterar Senha",
                cor = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.black)),
                modifier = Modifier.fillMaxWidth(),
                enabled = true
            ) {
                Image(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Senha",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(48.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Botao(
                    onClick = { navController.navigate("inicioAprendiz") },
                    texto = "Cancelar",
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .width(120.dp)
                        .height(70.dp),
                    cor = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.danger)),
                    enabled = true
                ){}
                Botao(
                    onClick = { navController.navigate("inicioAprendiz") },
                    texto = "Salvar",
                    cor = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.black)),
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .width(120.dp)
                        .height(70.dp),
                    enabled = true
                ) {

                }
            }
            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EditarPerfilAprdzPreview() {
    EditarPerfilAprdzScreen(rememberNavController())
}