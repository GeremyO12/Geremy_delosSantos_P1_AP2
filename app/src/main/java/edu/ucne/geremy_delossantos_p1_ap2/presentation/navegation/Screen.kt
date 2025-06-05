package edu.ucne.geremy_delossantos_p1_ap2.presentation.navegation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen{
    @Serializable
    data object TreaSistema : Screen()
    @Serializable
    data class Tarea(val tareaId: Int) : Screen()
}
