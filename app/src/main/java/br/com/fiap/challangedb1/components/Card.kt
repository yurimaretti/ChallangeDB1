package br.com.fiap.challangedb1.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import br.com.fiap.challangedb1.R

@Composable
fun CardTemplate(conteudo: @Composable () -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        colors = CardDefaults
            .cardColors(colorResource(id = R.color.cor_card)),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        conteudo()
    }
}