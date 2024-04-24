package br.com.fiap.challangedb1.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun InicioAprendizScreen(navController: NavController) {
    TemplateScreen(nomeTela = "Início") {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Botao(
                onClick = { /*TODO*/ },
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
                onClick = { /*TODO*/ },
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
        Spacer(modifier = Modifier.height(36.dp))
        Text(
            text = "Mentores",
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(36.dp))
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
    }
}

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
                    onClick = { /*TODO*/ },
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
fun InicioAprendizPreview() {
    InicioAprendizScreen(rememberNavController())
}