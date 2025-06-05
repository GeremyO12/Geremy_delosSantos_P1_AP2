package edu.ucne.geremy_delossantos_p1_ap2.presentation.navegation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.toRoute
import androidx.navigation.compose.composable
import edu.ucne.geremy_delossantos_p1_ap2.presentation.sistema.TareaList
import edu.ucne.geremy_delossantos_p1_ap2.presentation.sistema.TareaSistema



@Composable
fun HostNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.TareaList
    ) {
        composable<Screen.Tarea> {
            TareaList(
                goToTarea = {
                    navHostController.navigate(Screen.Tarea(it))
                },
                createTarea = {
                    navHostController.navigate(Screen.Tarea(0))
                }
            )
        }

        composable<Screen.Tarea> {
            val args = it.toRoute<Screen.Tarea>()
            TareaSistema(
                tareaId = args.tareaId,
                goBack = {
                    navHostController.navigateUp()
                }
            )
        }
    }
}
