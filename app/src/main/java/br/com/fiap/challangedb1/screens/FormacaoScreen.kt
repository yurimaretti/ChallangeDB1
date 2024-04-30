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
import br.com.fiap.challangedb1.components.MensagemErro
import br.com.fiap.challangedb1.components.TemplateScreen
import br.com.fiap.challangedb1.enums.GrauInstrucao
import br.com.fiap.challangedb1.util.validation.validacaoDropdown
import br.com.fiap.challangedb1.util.validation.validacaoEmail
import br.com.fiap.challangedb1.util.validation.validacaoNome
import br.com.fiap.challangedb1.util.validation.validacaoSenha

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormacaoScreen(navController: NavController, tipoCadastro: String) {

    TemplateScreen(nomeTela = "Formação $tipoCadastro") {

        var curso by remember {
            mutableStateOf("")
        }
        var instituicao by remember {
            mutableStateOf("")
        }
        var grau by remember {
            mutableStateOf("")
        }
        var isExpanded by remember {
            mutableStateOf(false)
        }
        val lista = GrauInstrucao.entries
        var erroCadastro by remember {
            mutableStateOf(false)
        }

        CardTemplate {
            Text(text = "Incluir Formação",
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))

            //Formulário

            Column(modifier = Modifier.padding(horizontal = 12.dp)) {

                //Input NÍVEL DA FORMAÇÃO e validação

                Text(
                    text = "Nível",
                    modifier = Modifier.padding(start = 8.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                ExposedDropdownMenuBox(
                    expanded = isExpanded,
                    onExpandedChange = { isExpanded = it },
                    modifier = Modifier.background(Color.White)
                ) {
                    OutlinedTextField(
                        value = grau,
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
                        isError = false
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
                                    Text(text = it.grau)
                                },
                                onClick = {
                                    grau = it.grau
                                    isExpanded = !isExpanded
                                }
                            )
                        }
                    }
                }
                if (!validacaoDropdown(grau) && erroCadastro) {
                    MensagemErro(
                        mensagem = "Grau da Formação deve ser preenchida",
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.Normal,
                        spacer = 4.dp
                    )
                }

                //Input NOME DO CURSO DA FORMAÇÃO e validação

                Spacer(modifier = Modifier.height(16.dp))
                InputBox(
                    label = "Curso",
                    placeholder = "Informe o nome do curso",
                    value = curso,
                    kayboardType = KeyboardType.Text,
                    modifier = Modifier,
                    updateValue = { curso  = it },
                    visualTransformation = VisualTransformation.None,
                    isError = false
                )
                if (!validacaoNome(curso) && erroCadastro) {
                    MensagemErro(
                        mensagem = "Nome deve possuir de 2 a 70 caracteres",
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.Normal,
                        spacer = 4.dp
                    )
                }

                //Input NOME DA INSTITUIÇÃO e validação

                Spacer(modifier = Modifier.height(16.dp))
                InputBox(
                    label = "Instituição",
                    placeholder = "Informe o nome da Instituição de Ensino",
                    value = instituicao,
                    kayboardType = KeyboardType.Text,
                    modifier = Modifier,
                    updateValue = { instituicao = it },
                    visualTransformation = VisualTransformation.None,
                    isError = false
                )
                if (!validacaoNome(instituicao) && erroCadastro) {
                    MensagemErro(
                        mensagem = "Nome deve possuir de 2 a 70 caracteres",
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.Normal,
                        spacer = 4.dp
                    )
                }
            }
            Spacer(modifier = Modifier.height(48.dp))

            //Botões para voltar e salvar

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Botao(
                    onClick = { navController.navigate("editarPerfil/$tipoCadastro") },
                    texto = "Cancelar",
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .width(120.dp)
                        .height(70.dp),
                    cor = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.danger)),
                    enabled = true
                ){}
                Botao(
                    onClick = {
                        if (tipoCadastro == "Aprendiz") {
                            if (validacaoNome(instituicao) &&
                                validacaoNome(curso) &&
                                validacaoDropdown(grau)
                            ) {
                                //TODO API para salvar na tabela de aprendiz
                                navController.navigate("editarPerfil/$tipoCadastro")
                            } else {
                                erroCadastro = true
                            }
                        } else if (tipoCadastro == "Mentor") {
                            if (validacaoNome(instituicao) &&
                                validacaoNome(curso) &&
                                validacaoDropdown(grau)
                            ) {
                                //TODO API para salvar na tabela de mentor
                                navController.navigate("editarPerfil/$tipoCadastro")
                            } else {
                                erroCadastro = true
                            }
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
fun FormacaoAprdzScreenPreview() {
    FormacaoScreen(rememberNavController(), "Mentor")
}