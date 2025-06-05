package edu.ucne.geremy_delossantos_p1_ap2.presentation.sistema

sealed interface TareaEvent {
    data class TareaCambio(val tareaId: Int) : TareaEvent
    data class DescripcionCambio(val descripcion: String) : TareaEvent
    data class TimpoCambio(val tiempo: Int?) : TareaEvent
    data object Save: TareaEvent
    data object Delete: TareaEvent
    data object New: TareaEvent
}
