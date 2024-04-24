package br.com.fiap.challangedb1.components

import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import br.com.fiap.challangedb1.R

@Composable
fun BotoesAprendizMentor(
    tipoCadastro: String,
    onClickAprendiz: () -> Unit,
    txtBotaoAprendiz: String,
    onClickMentor: () -> Unit,
    txtBotaoMentor: String,
    txtDisabled: String,
    modifier: Modifier
) {

    if (tipoCadastro == "Aprendiz") {
        Botao(
            onClick = onClickAprendiz,
            texto = txtBotaoAprendiz,
            modifier = modifier,
            cor = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.black)),
            enabled = true
        ){}
    } else if (tipoCadastro == "Mentor") {
        Botao(
            onClick = onClickMentor,
            texto = txtBotaoMentor,
            modifier = modifier,
            cor = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.black)),
            enabled = true
        ){}
    } else {
        Botao(
            onClick = {  },
            texto = txtDisabled,
            cor = ButtonDefaults.buttonColors(Color.Gray),
            modifier = modifier,
            enabled = false
        ){}
    }
}