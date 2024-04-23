package br.com.fiap.challangedb1.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import br.com.fiap.challangedb1.R

@Composable
fun SeletorAprdzMentor(
    tipoCadastro: String,
    onClickAprendiz: () -> Unit,
    onClickMentor: () -> Unit
) {
    Row(modifier = Modifier
        .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = tipoCadastro == "Aprendiz",
                onClick = onClickAprendiz,
                colors = RadioButtonDefaults.colors(
                    selectedColor = colorResource(id = R.color.preto_fundo),
                    unselectedColor = Color.Gray
                )
            )
            Text(text = "Aprendiz", color = colorResource(id = R.color.preto_fundo), fontWeight = FontWeight.ExtraBold)
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = tipoCadastro == "Mentor",
                onClick = onClickMentor,
                colors = RadioButtonDefaults.colors(
                    selectedColor = colorResource(id = R.color.preto_fundo),
                    unselectedColor = Color.Gray
                )
            )
            Text(text = "Mentor", color = colorResource(id = R.color.preto_fundo), fontWeight = FontWeight.ExtraBold)
        }
    }
}