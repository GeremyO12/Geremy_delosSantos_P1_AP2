package edu.ucne.geremy_delossantos_p1_ap2.presentation.sistema

import androidx.compose.runtime.Composable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import edu.ucne.geremy_delossantos_p1_ap2.data.local.entities.TareaEntity
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun TareaList(
    viewModel: TareaViewModel = hiltViewModel(),
    goToTarea: (Int) -> Unit,
    createTarea: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    TicketListBody(
        uiState,
        goToTarea,
        createTarea
    )
}

@Composable
fun TicketListBody(
    uiState: TareaUiState,
    goToTarea: (Int) -> Unit,
    createTarea: () -> Unit
) {
    Scaffold(modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = createTarea
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Crear nueva Tarea"
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text("Lista de tareas")

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(uiState.tareas) {
                    TareaRow(
                        it,
                        goToTarea,
                        createTarea
                    )
                }
            }
        }
    }
}
@Composable
private fun TareaRow(
    it: TareaEntity,
    goToTarea: (Int) -> Unit,
    createTarea: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable {
                goToTarea(it.tareaId ?: 0)
            }
        ) {
            Text(modifier = Modifier.weight(1f), text = it.tareaId.toString())
            Text(
                modifier = Modifier.weight(2f),
                text = it.descripcion,
                style = MaterialTheme.typography.headlineLarge
            )
            Text(modifier = Modifier.weight(2f), text = it.tiempo.toString())
        }
    }
    HorizontalDivider()
}
