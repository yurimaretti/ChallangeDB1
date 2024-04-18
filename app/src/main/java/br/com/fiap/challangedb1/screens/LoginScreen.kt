package br.com.fiap.challangedb1.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fiap.challangedb1.R
import kotlin.math.round

//@Composable
//fun LoginScreen() {
//    Column(modifier = Modifier
//        .fillMaxSize()
//        .background(Color(0xFF15E7D4))
//    ) {
//        Column(modifier = Modifier
//            .fillMaxSize()
//            .offset(y = 80.dp)
//            .background(Color(0xFFFFFFFF))
//            .border(1.dp, Color.White, shape = RoundedCornerShape(8.dp))
//        ) {
//
//        }
//    }
//}
//
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun LoginScreenPreview() {
//    LoginScreen()
//}

@Composable
fun LoginScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.verde_fundo))
                .padding(16.dp)
                .offset(y = 80.dp)
        ) {

        }
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .offset(y = 80.dp),
            color = Color.White,
            shape = RoundedCornerShape(28.dp) // Definindo cantos arredondados
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}