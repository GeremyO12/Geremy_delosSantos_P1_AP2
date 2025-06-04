package edu.ucne.geremy_delossantos_p1_ap2.presentation.navegation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen{
    @Serializable
    data object Sistema : Screen()
    @Serializable
    data object List : Screen()
}