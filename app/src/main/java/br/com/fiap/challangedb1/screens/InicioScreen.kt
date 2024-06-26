package br.com.fiap.challangedb1.screens

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.challangedb1.R
import br.com.fiap.challangedb1.components.Botao
import br.com.fiap.challangedb1.components.CardTemplate
import br.com.fiap.challangedb1.components.TemplateScreen
import br.com.fiap.challangedb1.enums.AreaConhecimento
import br.com.fiap.challangedb1.model.AprendizModel
import br.com.fiap.challangedb1.model.MatchModel
import br.com.fiap.challangedb1.model.MentorModel
import br.com.fiap.challangedb1.service.RetrofitInstance
import br.com.fiap.challangedb1.service.RetrofitInstance.apiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InicioScreen(navController: NavController, tipoCadastro: String, email: String) {

    var expandedSeach by remember {
        mutableStateOf (false)
    }
    var expandedDropdownHab by remember {
        mutableStateOf(false)
    }
    var habilidades by remember {
        mutableStateOf("")
    }
    val listaHabilidade = AreaConhecimento.entries
    val context = LocalContext.current
    val apiService = RetrofitInstance.apiService
    var mentores by remember {
        mutableStateOf(listOf<MentorModel>())
    }
    var aprendizes by remember {
        mutableStateOf(listOf<AprendizModel>())
    }
    val listaAprendizesFiltrada = aprendizes.filter { aprendiz ->
        aprendiz.interesse?.any { interesse ->
            interesse.areaInteresse.contains(habilidades)
        } == true
    }
    val listaMentoresFiltrada = mentores.filter { mentor ->
        mentor.habilidade?.any { habilidade ->
            habilidade.areaHabilidade.contains(habilidades)
        } == true
    }

    TemplateScreen(nomeTela = "Bem vindo $tipoCadastro!") {

        //Botões do cabeçalho

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Botao(
                onClick = { navController.navigate("editarPerfil/$tipoCadastro/$email") },
                texto = "Editar Perfil",
                cor = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.black)),
                modifier = Modifier,
                enabled = true
            ){
                Image(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Perfil",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Botao(
                onClick = { navController.navigate("match/$tipoCadastro/$email") },
                texto = "Matches",
                cor = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.purple_700)),
                modifier = Modifier,
                enabled = true
            ) {
                Image(
                    imageVector = Icons.Default.ThumbUp,
                    contentDescription = "Perfil",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

        //Botão de consulta aos Mentores/Aprendizes

        if (tipoCadastro == "Aprendiz") {
            Botao(
                onClick = {
                    val call = apiService.getMentor()

                    call.enqueue(object : Callback<List<MentorModel>> {
                        override fun onResponse(call: Call<List<MentorModel>>, response: Response<List<MentorModel>>) {
                            mentores = response.body()!!
                        }
                        override fun onFailure(call: Call<List<MentorModel>>, t: Throwable) {
                            Toast.makeText(context, "Por favor tente novamente mais tarde.", Toast.LENGTH_LONG).show();
                            Log.e("TAG", "Erro na chamada à API: ${t.message}")
                        }
                    })
                },
                texto = "Consultar Mentores",
                cor = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.teal_700)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                enabled = true
            ) {
                Image(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Perfil",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        } else if (tipoCadastro == "Mentor") {
            Botao(
                onClick = {
                    val call = apiService.getAprendiz()

                    call.enqueue(object : Callback<List<AprendizModel>> {
                        override fun onResponse(call: Call<List<AprendizModel>>, response: Response<List<AprendizModel>>) {
                            aprendizes = response.body()!!
                        }
                        override fun onFailure(call: Call<List<AprendizModel>>, t: Throwable) {
                            Toast.makeText(context, "Por favor tente novamente mais tarde.", Toast.LENGTH_LONG).show();
                            Log.e("TAG", "Erro na chamada à API: ${t.message}")
                        }
                    })
                },
                texto = "Consultar Aprendizes",
                cor = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.teal_700)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                enabled = true
            ) {
                Image(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Perfil",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

        // Menu de pesquisa avançada

        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable(onClick = { expandedSeach = !expandedSeach }),
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults
                .cardColors(colorResource(id = R.color.cor_card))
        ) {
            Column {
                Row(modifier = Modifier
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Pesquisa Avançada",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(16.dp),
                        fontWeight = FontWeight.ExtraBold
                    )
                    if (expandedSeach) {
                        Image(
                            imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = "Seta para cima",
                            colorFilter = ColorFilter.tint(Color.Black),
                            modifier = Modifier.padding(16.dp)
                        )
                    } else {
                        Image(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "Seta para baixo",
                            colorFilter = ColorFilter.tint(Color.Black),
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
                if (expandedSeach) {
                    if (tipoCadastro == "Aprendiz") {
                        Text(
                            text = "Habilidades",
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    } else if (tipoCadastro == "Mentor") {
                        Text(
                            text = "Interesses",
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    ExposedDropdownMenuBox(
                        expanded = expandedDropdownHab,
                        onExpandedChange = { expandedDropdownHab = it },
                        modifier = Modifier
                            .background(colorResource(id = R.color.cor_card))
                            .padding(horizontal = 16.dp)
                    ) {
                        OutlinedTextField(
                            value = habilidades,
                            onValueChange = {},
                            trailingIcon = {
                                ExposedDropdownMenuDefaults
                                    .TrailingIcon(expanded = expandedDropdownHab)
                            },
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth(),
                            readOnly = true,
                            colors = ExposedDropdownMenuDefaults.textFieldColors(),
                            shape = RoundedCornerShape(16.dp)
                        )
                        ExposedDropdownMenu(
                            expanded = expandedDropdownHab,
                            onDismissRequest = { expandedDropdownHab = !expandedDropdownHab },
                            modifier = Modifier
                                .background(Color.White)
                        ) {
                            listaHabilidade.forEach{
                                DropdownMenuItem(
                                    text = {
                                        Text(text = it.area)
                                    },
                                    onClick = {
                                        habilidades = it.area
                                        expandedDropdownHab = !expandedDropdownHab
                                    }
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row {
                        Botao(
                            onClick = {
                                if (tipoCadastro == "Aprendiz") {
                                    mentores = listaMentoresFiltrada
                                } else if (tipoCadastro == "Mentor") {
                                    aprendizes = listaAprendizesFiltrada
                                }
                            },
                            texto = "Filtrar",
                            cor = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.purple_700)),
                            modifier = Modifier.padding(start = 16.dp, bottom = 16.dp),
                            enabled = true
                        ) {
                            Image(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Perfil",
                                colorFilter = ColorFilter.tint(Color.White),
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                        Botao(
                            onClick = {
                                if (tipoCadastro == "Aprendiz") {
                                    val call = apiService.getMentor()

                                    call.enqueue(object : Callback<List<MentorModel>> {
                                        override fun onResponse(call: Call<List<MentorModel>>, response: Response<List<MentorModel>>) {
                                            mentores = response.body()!!
                                        }
                                        override fun onFailure(call: Call<List<MentorModel>>, t: Throwable) {
                                            Toast.makeText(context, "Por favor tente novamente mais tarde.", Toast.LENGTH_LONG).show();
                                            Log.e("TAG", "Erro na chamada à API: ${t.message}")
                                        }
                                    })
                                } else if (tipoCadastro == "Mentor") {
                                    val call = apiService.getAprendiz()

                                    call.enqueue(object : Callback<List<AprendizModel>> {
                                        override fun onResponse(call: Call<List<AprendizModel>>, response: Response<List<AprendizModel>>) {
                                            aprendizes = response.body()!!
                                        }
                                        override fun onFailure(call: Call<List<AprendizModel>>, t: Throwable) {
                                            Toast.makeText(context, "Por favor tente novamente mais tarde.", Toast.LENGTH_LONG).show();
                                            Log.e("TAG", "Erro na chamada à API: ${t.message}")
                                        }
                                    })
                                }
                            },
                            texto = "Limpar filtro",
                            cor = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.danger)),
                            modifier = Modifier.padding(start = 16.dp, bottom = 16.dp),
                            enabled = true
                        ) {
                            Image(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Perfil",
                                colorFilter = ColorFilter.tint(Color.White),
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }

                }
            }
        }

        // Cards dos Mentores/Aprendizes

        Spacer(modifier = Modifier.height(36.dp))
        if (tipoCadastro == "Aprendiz") {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ){
                items(mentores) {
                    CardMentor(it, email, navController, tipoCadastro)
                }
            }
        } else if (tipoCadastro == "Mentor") {
            LazyRow(modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
            ){
                items(aprendizes) {
                    CardAprendiz(it, email, navController, tipoCadastro)
                }
            }
        }
    }
}

//Templates dos cards de Mentor e Aprendiz

@Composable
fun CardMentor(mentor: MentorModel, email: String, navController: NavController, tipoCadastro: String) {

    val context = LocalContext.current

    //Acesso às habilidades do mentor

    val habilidades = mentor.habilidade

    //Acesso aos matches do mentor

    val matches = mentor.match
    var curtMentor by remember {
        mutableStateOf(0)
    }
    var curtAprdz by remember {
        mutableStateOf(0)
    }
    var emailAprdz by remember {
        mutableStateOf("")
    }
    var emailMentor by remember {
        mutableStateOf("")
    }
    var matchId by remember {
        mutableStateOf(0)
    }
    if (!matches.isNullOrEmpty()) {
        for (match in matches) {
            matchId = match.matchId
            emailAprdz = match.emailAprdz
            emailMentor = match.emailMentor
            curtMentor = match.curtidaMentor
            curtAprdz = match.curtidaAprendiz
        }
    }

    CardTemplate() {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = mentor.nomeMentor,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                fontSize = 18.sp
            )
            Row() {
                Column() {
                    Column() {
                        Text(
                            text = "Gênero",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )
                        Text(text = mentor.generoMentor, modifier = Modifier.padding(bottom = 12.dp))
                    }
                    Column() {
                        Text(
                            text = "Habilidades",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )
                        if (!habilidades.isNullOrEmpty()) {
                            habilidades.forEach { habilidade ->
                                Text(
                                    text = "${habilidade.areaHabilidade}",
                                    modifier = Modifier.padding(bottom = 12.dp)
                                )
                            }
                        } else {
                            Text(
                                text = "Sem habilidades registradas",
                                modifier = Modifier.padding(bottom = 12.dp)
                            )
                        }
                    }
                }
                Botao(
                    onClick = {
                        val call = apiService.getMatchPorEmails(email, mentor.emailMentor)

                        call.enqueue(object : Callback<MatchModel> {
                            override fun onResponse(call: Call<MatchModel>, response: Response<MatchModel>) {
                                if (response.isSuccessful) {
                                    val respostaMatch = response.body()
                                    var id = respostaMatch!!.matchId
                                    var matchAtualizado = MatchModel(respostaMatch.matchId, 1, respostaMatch.curtidaMentor, email, emailMentor)
                                    var atualizar = apiService.atualizarMatch(id, matchAtualizado)

                                    atualizar.enqueue(object : Callback<MatchModel> {
                                        override fun onResponse(atualizar: Call<MatchModel>, response: Response<MatchModel>) {
                                            if (response.isSuccessful) {
                                                Toast.makeText(context, "Mentor curtido!", Toast.LENGTH_LONG).show();
                                                navController.navigate("inicio/$tipoCadastro/$email")
                                            } else {
                                                val errorBody = response.errorBody()?.string()
                                                Log.e("TAG", "Erro na chamada à API: $errorBody")
                                                Toast.makeText(context, "Ops, algo deu errado... Pode tentar de novo?", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                        override fun onFailure(atualizar: Call<MatchModel>, t: Throwable) {
                                            Log.e("TAG", "Erro na chamada à API: ${t.message}")
                                            Toast.makeText(context, "Ops, algo deu errado... Pode tentar de novo?", Toast.LENGTH_LONG).show();
                                        }
                                    })
                                } else {
                                    var match = MatchModel(0, 1, 0, email, mentor.emailMentor)
                                    val incluir = apiService.incluirMatch(match)

                                    incluir.enqueue(object : Callback<MatchModel> {
                                        override fun onResponse(incluir: Call<MatchModel>, response: Response<MatchModel>) {
                                            if (response.isSuccessful) {
                                                Toast.makeText(context, "Mentor curtido!", Toast.LENGTH_LONG).show();
                                                navController.navigate("inicio/$tipoCadastro/$email")
                                            } else {
                                                val errorBody = response.errorBody()?.string()
                                                Log.e("TAG", "Erro na chamada à API: $errorBody")
                                                Toast.makeText(context, "Ops, algo deu errado... Pode tentar de novo?", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                        override fun onFailure(incluir: Call<MatchModel>, t: Throwable) {
                                            Log.e("TAG", "Erro na chamada à API: ${t.message}")
                                            Toast.makeText(context, "Ops, algo deu errado... Pode tentar de novo?", Toast.LENGTH_LONG).show();
                                        }
                                    })
                                }
                            }
                            override fun onFailure(call: Call<MatchModel>, t: Throwable) {
                                // Erro na chamada à API
                                Log.e("TAG", "Erro na chamada à API: ${t.message}")
                                Toast.makeText(context, "Erro na chamada à API: ${t.message}", Toast.LENGTH_LONG).show()
                            }
                        })

                        //Gerar Push

                        fun NotificationManager.sendNotification(title: String, message: String) {
                            val channelId = "channel_id"
                            val notificationBuilder = NotificationCompat.Builder(context, channelId)
                                .setContentTitle(title)
                                .setContentText(message)
                                .setSmallIcon(R.drawable.jigsaw)
                                .setAutoCancel(true)

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                val channel = NotificationChannel(channelId, "Channel Name", NotificationManager.IMPORTANCE_DEFAULT)
                                createNotificationChannel(channel)
                            }

                            notify(0, notificationBuilder.build())
                        }

                        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                        notificationManager.sendNotification("Mentor Curtido", "Você curtiu um mentor!")
                    },
                    texto = "Curtir",
                    cor = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.purple_700)),
                    modifier = Modifier
                        .fillMaxHeight()
                        .align(Alignment.CenterVertically)
                        .padding(start = 36.dp),
                    enabled = true
                ) {
                    Image(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Perfil",
                        colorFilter = ColorFilter.tint(Color.White),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun CardAprendiz(aprendiz: AprendizModel, email: String, navController: NavController, tipoCadastro: String) {

    val context = LocalContext.current

    //Acesso aos interesses do aprendiz

    val interesses = aprendiz.interesse

    //Acesso aos matches do aprendiz

    val matches = aprendiz.match
    var curtMentor by remember {
        mutableStateOf(0)
    }
    var curtAprdz by remember {
        mutableStateOf(0)
    }
    var emailAprdz by remember {
        mutableStateOf("")
    }
    var emailMentor by remember {
        mutableStateOf("")
    }
    var matchId by remember {
        mutableStateOf(0)
    }
    if (!matches.isNullOrEmpty()) {
        for (match in matches) {
            matchId = match.matchId
            emailAprdz = match.emailAprdz
            emailMentor = match.emailMentor
            curtMentor = match.curtidaMentor
            curtAprdz = match.curtidaAprendiz
        }
    }

    CardTemplate() {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = aprendiz.nomeAprdz,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                fontSize = 18.sp
            )
            Row() {
                Column() {
                    Column() {
                        Text(
                            text = "Gênero",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )
                        Text(text = aprendiz.generoAprdz, modifier = Modifier.padding(bottom = 12.dp))
                    }
                    Column() {
                        Text(
                            text = "Interesses",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )
                        if (!interesses.isNullOrEmpty()) {
                            interesses.forEach { interesse ->
                                Text(
                                    text = "${interesse.areaInteresse}",
                                    modifier = Modifier.padding(bottom = 12.dp)
                                )
                            }
                        } else {
                            Text(
                                text = "Sem interesses registrados",
                                modifier = Modifier.padding(bottom = 12.dp)
                            )
                        }
                    }
                }
                Botao(
                    onClick = {
                        val call = apiService.getMatchPorEmails(aprendiz.emailAprdz, email)

                        call.enqueue(object : Callback<MatchModel> {
                            override fun onResponse(call: Call<MatchModel>, response: Response<MatchModel>) {
                                if (response.isSuccessful) {
                                    val respostaMatch = response.body()
                                    var id = respostaMatch!!.matchId
                                    var matchAtualizado = MatchModel(respostaMatch.matchId, respostaMatch.curtidaAprendiz, 1, emailAprdz, email)
                                    var atualizar = apiService.atualizarMatch(id, matchAtualizado)

                                    atualizar.enqueue(object : Callback<MatchModel> {
                                        override fun onResponse(atualizar: Call<MatchModel>, response: Response<MatchModel>) {
                                            if (response.isSuccessful) {
                                                Toast.makeText(context, "Aprendiz curtido!", Toast.LENGTH_LONG).show();
                                                navController.navigate("inicio/$tipoCadastro/$email")
                                            } else {
                                                val errorBody = response.errorBody()?.string()
                                                Log.e("TAG", "Erro na chamada à API: $errorBody")
                                                Toast.makeText(context, "Ops, algo deu errado... Pode tentar de novo?", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                        override fun onFailure(atualizar: Call<MatchModel>, t: Throwable) {
                                            Log.e("TAG", "Erro na chamada à API: ${t.message}")
                                            Toast.makeText(context, "Ops, algo deu errado... Pode tentar de novo?", Toast.LENGTH_LONG).show();
                                        }
                                    })
                                } else {
                                    var match = MatchModel(0, 0, 1, aprendiz.emailAprdz, email)
                                    val incluir = apiService.incluirMatch(match)

                                    incluir.enqueue(object : Callback<MatchModel> {
                                        override fun onResponse(incluir: Call<MatchModel>, response: Response<MatchModel>) {
                                            if (response.isSuccessful) {
                                                Toast.makeText(context, "Aprendiz curtido!", Toast.LENGTH_LONG).show();
                                                navController.navigate("inicio/$tipoCadastro/$email")
                                            } else {
                                                val errorBody = response.errorBody()?.string()
                                                Log.e("TAG", "Erro na chamada à API: $errorBody")
                                                Toast.makeText(context, "Ops, algo deu errado... Pode tentar de novo?", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                        override fun onFailure(incluir: Call<MatchModel>, t: Throwable) {
                                            Log.e("TAG", "Erro na chamada à API: ${t.message}")
                                            Toast.makeText(context, "Ops, algo deu errado... Pode tentar de novo?", Toast.LENGTH_LONG).show();
                                        }
                                    })
                                }
                            }
                            override fun onFailure(call: Call<MatchModel>, t: Throwable) {
                                // Erro na chamada à API
                                Log.e("TAG", "Erro na chamada à API: ${t.message}")
                                Toast.makeText(context, "Erro na chamada à API: ${t.message}", Toast.LENGTH_LONG).show()
                            }
                        })

                        //Gerar Push

                        fun NotificationManager.sendNotification(title: String, message: String) {
                            val channelId = "channel_id"
                            val notificationBuilder = NotificationCompat.Builder(context, channelId)
                                .setContentTitle(title)
                                .setContentText(message)
                                .setSmallIcon(R.drawable.jigsaw)
                                .setAutoCancel(true)

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                val channel = NotificationChannel(channelId, "Channel Name", NotificationManager.IMPORTANCE_DEFAULT)
                                createNotificationChannel(channel)
                            }

                            notify(0, notificationBuilder.build())
                        }

                        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                        notificationManager.sendNotification("Aprendiz Curtido", "Você curtiu um aprendiz!")
                    },
                    texto = "Curtir",
                    cor = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.purple_700)),
                    modifier = Modifier
                        .fillMaxHeight()
                        .align(Alignment.CenterVertically)
                        .padding(start = 36.dp),
                    enabled = true
                ) {
                    Image(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Perfil",
                        colorFilter = ColorFilter.tint(Color.White),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun InicioPreview() {
    InicioScreen(rememberNavController(), "Mentor", "")
}