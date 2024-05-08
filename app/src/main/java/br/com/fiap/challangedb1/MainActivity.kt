package br.com.fiap.challangedb1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.fiap.challangedb1.screens.AlterarSenhaScreen
import br.com.fiap.challangedb1.screens.CadastroScreen
import br.com.fiap.challangedb1.screens.EditarFormacaoScreen
import br.com.fiap.challangedb1.screens.EditarPerfilScreen
import br.com.fiap.challangedb1.screens.IncluirFormacaoScreen
import br.com.fiap.challangedb1.screens.InicioScreen
import br.com.fiap.challangedb1.screens.InteressesScreen
import br.com.fiap.challangedb1.screens.LoginScreen
import br.com.fiap.challangedb1.screens.MatchScreen
import br.com.fiap.challangedb1.ui.theme.ChallangeDB1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChallangeDB1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "login",
                        enterTransition = {
                            slideIntoContainer(
                                AnimatedContentTransitionScope.SlideDirection.Start,
                                animationSpec = tween(1000)
                            )
                        },
                        exitTransition = {
                            slideOutOfContainer(
                                AnimatedContentTransitionScope.SlideDirection.End,
                                animationSpec = tween(1000)
                            )
                        }
                    ){
                        composable(route = "login") { LoginScreen(navController) }
                        composable(route = "cadastro") { CadastroScreen(navController) }
                        composable(
                            route = "inicio/{tipoCadastro}/{email}",
                            arguments = listOf(
                                navArgument("tipoCadastro"){
                                    type = NavType.StringType
                                },
                                navArgument("email"){
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            val tipoCadastro: String? = it.arguments?.getString("tipoCadastro")
                            val email: String? = it.arguments?.getString("email")
                            InicioScreen(navController, tipoCadastro!!, email!!)
                        }
                        composable(
                            route = "editarPerfil/{tipoCadastro}/{email}",
                            arguments = listOf(
                                navArgument("tipoCadastro"){
                                    type = NavType.StringType
                                },
                                navArgument("email"){
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            val tipoCadastro: String? = it.arguments?.getString("tipoCadastro")
                            val email: String? = it.arguments?.getString("email")
                            EditarPerfilScreen(navController = navController, tipoCadastro!!, email!!)
                        }
                        composable(
                            route = "alterarSenha/{tipoCadastro}/{email}",
                            arguments = listOf(
                                navArgument("tipoCadastro"){
                                    type = NavType.StringType
                                },
                                navArgument("email"){
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            val tipoCadastro: String? = it.arguments?.getString("tipoCadastro")
                            val email: String? = it.arguments?.getString("email")
                            AlterarSenhaScreen(navController = navController, tipoCadastro!!, email!!)
                        }
                        composable(
                            route = "editarFormacao/{tipoCadastro}/{email}",
                            arguments = listOf(
                                navArgument("tipoCadastro"){
                                    type = NavType.StringType
                                },
                                navArgument("email"){
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            val tipoCadastro: String? = it.arguments?.getString("tipoCadastro")
                            val email: String? = it.arguments?.getString("email")
                            EditarFormacaoScreen(navController = navController, tipoCadastro!!, email!!)
                        }
                        composable(
                            route = "formacao/{tipoCadastro}/{email}",
                            arguments = listOf(
                                navArgument("tipoCadastro"){
                                    type = NavType.StringType
                                },
                                navArgument("email"){
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            val tipoCadastro: String? = it.arguments?.getString("tipoCadastro")
                            val email: String? = it.arguments?.getString("email")
                            IncluirFormacaoScreen(navController = navController, tipoCadastro!!, email!!)
                        }
                        composable(
                            route = "match/{tipoCadastro}/{email}",
                            arguments = listOf(
                                navArgument("tipoCadastro"){
                                    type = NavType.StringType
                                },
                                navArgument("email"){
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            val tipoCadastro: String? = it.arguments?.getString("tipoCadastro")
                            val email: String? = it.arguments?.getString("email")
                            MatchScreen(navController = navController, tipoCadastro!!, email!!)
                        }
                        composable(
                            route = "interesses/{tipoCadastro}/{email}",
                            arguments = listOf(
                                navArgument("tipoCadastro"){
                                    type = NavType.StringType
                                },
                                navArgument("email"){
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            val tipoCadastro: String? = it.arguments?.getString("tipoCadastro")
                            val email: String? = it.arguments?.getString("email")
                            InteressesScreen(navController = navController, tipoCadastro!!, email!!)
                        }
                    }
                }
            }
        }
    }
}