package edu.ucne.geremy_delossantos_p1_ap2.presentation.sistema

import edu.ucne.geremy_delossantos_p1_ap2.data.local.entities.TareaEntity

data class TareaUiState(
  val tareaId: Int? = null,
  val descripcion: String? = null,
  val tiempo: Int? = null,
  val errorMessage: String? = null,
  val tareas: List<TareaEntity> = emptyList()
)

