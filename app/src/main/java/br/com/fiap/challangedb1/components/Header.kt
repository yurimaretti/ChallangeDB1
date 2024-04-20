package br.com.fiap.challangedb1.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.challangedb1.R

@Composable
fun Header(tela: String){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.verde_fundo))
            .padding(16.dp)
    ) {
        Row (modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(text = tela,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.preto_fundo)
            )
            Image(
                painter = painterResource(id = R.drawable.jigsaw),
                contentDescription = "Logo do app",
                modifier = Modifier.size(60.dp)
            )
        }
    }
}