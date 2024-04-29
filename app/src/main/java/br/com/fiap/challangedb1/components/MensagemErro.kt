package br.com.fiap.challangedb1.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.challangedb1.R

@Composable
fun MensagemErro(
    mensagem: String,
    textAlign: TextAlign,
    fontWeight: FontWeight,
    spacer: Dp
) {
    Spacer(modifier = Modifier.height(spacer))
    Text(
        text = mensagem,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        textAlign = textAlign,
        color = colorResource(id = R.color.danger),
        fontSize = 12.sp,
        fontWeight = fontWeight
    )
}