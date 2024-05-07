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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import br.com.fiap.challangedb1.enums.AreaConhecimento
import br.com.fiap.challangedb1.util.validation.validacaoDropdown
import br.com.fiap.challangedb1.util.validation.validacaoNome

@Composable
fun InteressesScreen(navController: NavController, tipoCadastro: String, email: String) {

    //Função para nome variável da Tela

    fun nomeTela(): String {
        var nome = ""

        if (tipoCadastro == "Aprendiz") {
            nome = "Interesses"
        } else if (tipoCadastro == "Mentor") {
            nome = "Habilidades"
        }
        return nome
    }

    //Demais variáveis

    var areasSelecionadas by remember {
        mutableStateOf(emptySet<AreaConhecimento>())
    }

    TemplateScreen(nomeTela = "${nomeTela()}") {
        CardTemplate {
            Text(
                text = "Editar ${nomeTela()}",
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))

            //Formulário

            Column(modifier = Modifier.padding(horizontal = 4.dp)) {
                AreaConhecimento.entries.forEach { area ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = areasSelecionadas.contains(area),
                            onCheckedChange = { isChecked ->
                                areasSelecionadas = if (isChecked) {
                                    areasSelecionadas + area
                                } else {
                                    areasSelecionadas - area
                                }
                            },
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Text(text = area.area)
                    }
                }
                Spacer(modifier = Modifier.height(48.dp))

                //Botões para voltar e salvar

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Botao(
                        onClick = { navController.navigate("editarPerfil/$tipoCadastro/$email") },
                        texto = "Cancelar",
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                            .width(120.dp)
                            .height(70.dp),
                        cor = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.danger)),
                        enabled = true
                    ) {}
                    Botao(
                        onClick = {
                            if (tipoCadastro == "Aprendiz") {

                                areasSelecionadas.forEach { area ->
                                    val descricao = area.area

                                    // TODO Enviar a descrição da área para tabela de Aprendiz
                                    // Exemplo:
                                    // database.enviarDescricaoAreaConhecimento(descricao)
                                }
                                navController.navigate("editarPerfil/$tipoCadastro/$email")
                            } else if (tipoCadastro == "Mentor") {
                                areasSelecionadas.forEach { area ->
                                    val descricao = area.area

                                    // TODO Enviar a descrição da área para tabela de Mentor
                                    // Exemplo:
                                    // database.enviarDescricaoAreaConhecimento(descricao)
                                }
                                navController.navigate("editarPerfil/$tipoCadastro/$email")
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
                Spacer(modifier = Modifier.height(48.dp))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun InteressesPreview() {
    InteressesScreen(rememberNavController(), "Aprendiz", "")
}