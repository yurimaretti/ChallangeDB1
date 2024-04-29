package br.com.fiap.challangedb1.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
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
import br.com.fiap.challangedb1.enums.GrauInstrucao

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InicioScreen(navController: NavController, tipoCadastro: String) {

    var expandedSeach by remember {
        mutableStateOf (false)
    }
    var expandedDropdownGrau by remember {
        mutableStateOf(false)
    }
    var expandedDropdownHab by remember {
        mutableStateOf(false)
    }
    var grau by remember {
        mutableStateOf("")
    }
    var habilidades by remember {
        mutableStateOf("")
    }
    val listaGrau = GrauInstrucao.entries
    val listaHabilidade = GrauInstrucao.entries //TODO Usar Enum GrauInstrucao até criar API das habilidades/interesses

    TemplateScreen(nomeTela = "Bem vindo $tipoCadastro!") {

        //Botões do cabeçalho

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Botao(
                onClick = { navController.navigate("editarPerfil/$tipoCadastro") },
                texto = "Editar Perfil",
                cor = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.black)),
                modifier = Modifier,
                enabled = true
            ){
                Image(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Perfil",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Botao(
                onClick = { navController.navigate("match/$tipoCadastro") },
                texto = "Matches",
                cor = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.purple_700)),
                modifier = Modifier,
                enabled = true
            ) {
                Image(
                    imageVector = Icons.Default.ThumbUp,
                    contentDescription = "Perfil",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

        // Menu de pesquisa avançada

        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable(onClick = { expandedSeach = !expandedSeach }),
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults
                .cardColors(colorResource(id = R.color.cor_card))
        ) {
            Column {
                Row(modifier = Modifier
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Pesquisa Avançada",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(16.dp),
                        fontWeight = FontWeight.ExtraBold
                    )
                    if (expandedSeach) {
                        Image(
                            imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = "Seta para cima",
                            colorFilter = ColorFilter.tint(Color.Black),
                            modifier = Modifier.padding(16.dp)
                        )
                    } else {
                        Image(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "Seta para baixo",
                            colorFilter = ColorFilter.tint(Color.Black),
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
                if (expandedSeach) {
                    Text(
                        text = "Grau de Instrução",
                        modifier = Modifier.padding(start = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    ExposedDropdownMenuBox(
                        expanded = expandedDropdownGrau,
                        onExpandedChange = { expandedDropdownGrau = it },
                        modifier = Modifier
                            .background(colorResource(id = R.color.cor_card))
                            .padding(horizontal = 16.dp)
                    ) {
                        OutlinedTextField(
                            value = grau,
                            onValueChange = {},
                            trailingIcon = {
                                ExposedDropdownMenuDefaults
                                    .TrailingIcon(expanded = expandedDropdownGrau)
                            },
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth(),
                            readOnly = true,
                            colors = ExposedDropdownMenuDefaults.textFieldColors(),
                            shape = RoundedCornerShape(16.dp)
                        )
                        ExposedDropdownMenu(
                            expanded = expandedDropdownGrau,
                            onDismissRequest = { expandedDropdownGrau = !expandedDropdownGrau },
                            modifier = Modifier
                                .background(Color.White)
                        ) {
                            listaGrau.forEach{
                                DropdownMenuItem(
                                    text = {
                                        Text(text = it.grau)
                                    },
                                    onClick = {
                                        grau = it.grau
                                        expandedDropdownGrau = !expandedDropdownGrau
                                    }
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    if (tipoCadastro == "Aprendiz") {
                        Text(
                            text = "Habilidades",
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    } else if (tipoCadastro == "Mentor") {
                        Text(
                            text = "Interesses",
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    ExposedDropdownMenuBox(
                        expanded = expandedDropdownHab,
                        onExpandedChange = { expandedDropdownHab = it },
                        modifier = Modifier
                            .background(colorResource(id = R.color.cor_card))
                            .padding(horizontal = 16.dp)
                    ) {
                        OutlinedTextField(
                            value = habilidades,
                            onValueChange = {},
                            trailingIcon = {
                                ExposedDropdownMenuDefaults
                                    .TrailingIcon(expanded = expandedDropdownHab)
                            },
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth(),
                            readOnly = true,
                            colors = ExposedDropdownMenuDefaults.textFieldColors(),
                            shape = RoundedCornerShape(16.dp)
                        )
                        ExposedDropdownMenu(
                            expanded = expandedDropdownHab,
                            onDismissRequest = { expandedDropdownHab = !expandedDropdownHab },
                            modifier = Modifier
                                .background(Color.White)
                        ) {
                            listaHabilidade.forEach{
                                DropdownMenuItem(
                                    text = {
                                        Text(text = it.grau)
                                    },
                                    onClick = {
                                        habilidades = it.grau
                                        expandedDropdownHab = !expandedDropdownHab
                                    }
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Botao(
                        onClick = { /*TODO Inserir API de pesquisa por Grau de Instrução e Habilidades*/ },
                        texto = "Filtrar",
                        cor = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.purple_700)),
                        modifier = Modifier.padding(start = 16.dp, bottom = 16.dp),
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
            }
        }
        Spacer(modifier = Modifier.height(36.dp))

        // Cards dos Mentores/Aprendizes

        if (tipoCadastro == "Aprendiz") {
            Text(
                text = "Mentores",
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )
        } else if (tipoCadastro == "Mentor") {
            Text(
                text = "Aprendizes",
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(36.dp))
        if (tipoCadastro == "Aprendiz") {
            LazyRow(modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)){
                item {
                    CardMentor()
                    CardMentor()
                    CardMentor()
                    CardMentor()
                    CardMentor()
                }
            }
        } else if (tipoCadastro == "Mentor") {
            LazyRow(modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)){
                item {
                    CardAprendiz()
                    CardAprendiz()
                    CardAprendiz()
                    CardAprendiz()
                    CardAprendiz()
                }
            }
        }
    }
}

//Templates dos cards de Mentor e Aprendiz

@Composable
fun CardMentor() {
    CardTemplate() {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Yuri Maretti Cornacioni",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                fontSize = 18.sp
            )
            Row() {
                Column() {
                    Column() {
                        Text(
                            text = "Gênero",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )
                        Text(text = "Masculino", modifier = Modifier.padding(bottom = 12.dp))
                    }
                    Column() {
                        Text(
                            text = "Habilidades",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )
                        Text(text = "Administração", modifier = Modifier.padding(bottom = 12.dp))
                        Text(text = "Programação", modifier = Modifier.padding(bottom = 12.dp))
                        Text(text = "Gestão de Pessoas", modifier = Modifier.padding(bottom = 12.dp))
                        Text(text = "Metodologias ágeis", modifier = Modifier.padding(bottom = 12.dp))
                        Text(text = "Mentoria", modifier = Modifier.padding(bottom = 12.dp))
                        Text(text = "Administração", modifier = Modifier.padding(bottom = 12.dp))
                        Text(text = "Programação", modifier = Modifier.padding(bottom = 12.dp))
                        Text(text = "Gestão de Pessoas", modifier = Modifier.padding(bottom = 12.dp))
                        Text(text = "Metodologias ágeis", modifier = Modifier.padding(bottom = 12.dp))
                        Text(text = "Mentoria", modifier = Modifier.padding(bottom = 12.dp))
                    }
                }
                Botao(
                    onClick = { /*TODO incluir API para curtir*/ },
                    texto = "Curtir",
                    cor = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.purple_700)),
                    modifier = Modifier
                        .fillMaxHeight()
                        .align(Alignment.CenterVertically)
                        .padding(start = 36.dp),
                    enabled = true
                ) {
                    Image(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Perfil",
                        colorFilter = ColorFilter.tint(Color.White),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun CardAprendiz() {
    CardTemplate() {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Bruna Letícia Martins da Silva",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                fontSize = 18.sp
            )
            Row() {
                Column() {
                    Column() {
                        Text(
                            text = "Gênero",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )
                        Text(text = "Feminino", modifier = Modifier.padding(bottom = 12.dp))
                    }
                    Column() {
                        Text(
                            text = "Interesses",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )
                        Text(text = "Administração", modifier = Modifier.padding(bottom = 12.dp))
                        Text(text = "Programação", modifier = Modifier.padding(bottom = 12.dp))
                        Text(text = "Gestão de Pessoas", modifier = Modifier.padding(bottom = 12.dp))
                        Text(text = "Metodologias ágeis", modifier = Modifier.padding(bottom = 12.dp))
                        Text(text = "Mentoria", modifier = Modifier.padding(bottom = 12.dp))
                        Text(text = "Administração", modifier = Modifier.padding(bottom = 12.dp))
                        Text(text = "Programação", modifier = Modifier.padding(bottom = 12.dp))
                        Text(text = "Gestão de Pessoas", modifier = Modifier.padding(bottom = 12.dp))
                        Text(text = "Metodologias ágeis", modifier = Modifier.padding(bottom = 12.dp))
                        Text(text = "Mentoria", modifier = Modifier.padding(bottom = 12.dp))
                    }
                }
                Botao(
                    onClick = { /*TODO incluir API para curtir*/ },
                    texto = "Curtir",
                    cor = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.purple_700)),
                    modifier = Modifier
                        .fillMaxHeight()
                        .align(Alignment.CenterVertically)
                        .padding(start = 36.dp),
                    enabled = true
                ) {
                    Image(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Perfil",
                        colorFilter = ColorFilter.tint(Color.White),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun InicioPreview() {
    InicioScreen(rememberNavController(), "Mentor")
}