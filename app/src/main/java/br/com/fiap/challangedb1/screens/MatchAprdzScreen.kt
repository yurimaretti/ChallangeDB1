package br.com.fiap.challangedb1.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ButtonDefaults
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
fun MatchAprdzScreen(navController: NavController) {
    TemplateScreen(nomeTela = "Matches") {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
        ) {
            Text(
                text = "Match de Mentores",
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyRow(modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)){
                item {
                    CardMatchAprdz()
                    CardMatchAprdz()
                    CardMatchAprdz()
                }
            }
            Botao(
                onClick = { navController.navigate("inicioAprendiz") },
                texto = "Voltar",
                cor = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.black)),
                modifier = Modifier.fillMaxWidth(),
                enabled = true
            ) {}
        }

    }
}

@Composable
fun CardMatchAprdz() {
    Column {
        CardTemplate() {
            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .width(240.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Yuri Maretti Cornacioni",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
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
                                text = "Email para contato",
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 12.dp)
                            )
                            Text(text = "yurimaretti@hotmail.com", modifier = Modifier.padding(bottom = 12.dp))
                        }
                        Column() {
                            Text(
                                text = "Habilidades",
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 12.dp)
                            )
                            Text(
                                text = "Administração, Programação, Gestão de Pessoas, Metodologias ágeis, Mentoria, Administração",
                                modifier = Modifier.padding(bottom = 12.dp)
                            )
                        }
                        Column() {
                            Text(
                                text = "Formação",
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 12.dp)
                            )
                            Text(
                                text = "Análise e Desenvolvimento de Sistemas na instituição FIAP",
                                modifier = Modifier.padding(bottom = 12.dp)
                            )
                            Text(
                                text = "Engenharia de Software na instituição CENES",
                                modifier = Modifier.padding(bottom = 12.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Botao(
                            onClick = { /*TODO*/ },
                            texto = "Desfazer Match",
                            cor = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.purple_700)),
                            modifier = Modifier.fillMaxWidth(),
                            enabled = true
                        ) {
                            Image(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Desfazer",
                                colorFilter = ColorFilter.tint(Color.White),
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MatchAprdzPreview() {
    MatchAprdzScreen(rememberNavController())
}