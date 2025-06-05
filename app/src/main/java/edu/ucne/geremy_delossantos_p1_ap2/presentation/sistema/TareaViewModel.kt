package edu.ucne.geremy_delossantos_p1_ap2.presentation.sistema

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.geremy_delossantos_p1_ap2.data.local.entities.TareaEntity
import edu.ucne.geremy_delossantos_p1_ap2.data.repository.TareaRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class TareaViewModel @Inject constructor(
    private val tareaRepository: TareaRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(TareaUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getTarea()
    }

    fun onEvent(event: TareaEvent){
        when (event){
            is TareaEvent.TareaCambio -> onTareaId(event.tareaId)
            is TareaEvent.DescripcionCambio -> onDescripcion(event.descripcion)
            is TareaEvent.TimpoCambio -> onTiempo(event.tiempo)
            TareaEvent.Save -> save()
            TareaEvent.Delete -> delete()
            TareaEvent.New -> nuevo()
        }
    }
    private fun save() {
        viewModelScope.launch {
            if (_uiState.value.descripcion.isNullOrBlank() && _uiState.value.tiempo.isNullOrBlank()){
                _uiState.update {
                    it.copy(errorMessage = "Campos vacios")
                }
            }
            else{
                tareaRepository.save(_uiState.value.toEntity())
            }
        }
    }

    private fun nuevo() {
        _uiState.update {
            it.copy(
                tareaId = null,
                descripcion = "",
                tiempo = null,
                errorMessage = null
            )
        }
    }

    fun selectedTarea(tareaId: Int){
        viewModelScope.launch {
            if(t > 0){
                val tarea = tareaRepository.getTareas(tareaId)
                _uiState.update {
                    it.copy(
                        ticketId = tarea?.tareaId,
                        descripcion = tarea?.descripcion,
                        tiempo = tarea?.tiempo
                    )
                }
            }
        }
    }

    private fun delete() {
        viewModelScope.launch {
            tareaRepository.delete(_uiState.value.toEntity())
        }
    }

    private fun getTarea() {
        viewModelScope.launch {
            tareaRepository.getTareas().collect { tareas ->
                _uiState.update {
                    it.copy(tareas = tareas)
                }
            }
        }
    }

    private fun onTareaId(tareaId: Int) {
        _uiState.update {
            it.copy(tareaId = tareaId)
        }
    }

    private fun onDescripcion(descripcion: String) {
        _uiState.update {
            it.copy(descripcion = descripcion)
        }
    }

    private fun onTiempo(tiempo: Int?) {
        _uiState.update {
            it.copy(tiempo = tiempo)
        }
    }
}



fun TareaUiState.toEntity() = TareaEntity(
    tareaId = tareaId,
    descripcion = descripcion ?: "",
    tiempo = tiempo
)
