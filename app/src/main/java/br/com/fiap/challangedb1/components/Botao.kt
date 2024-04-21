package br.com.fiap.challangedb1.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.challangedb1.R

@Composable
fun Botao(
    onClick: () -> Unit,
    texto: String,
    cor: ButtonColors,
    modifier: Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(56.dp)
            .padding(4.dp),
        shape = RoundedCornerShape(16.dp),
        colors = cor
    ) {
        Text(
            text = texto,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 14.sp
        )
    }
}