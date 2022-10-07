package com.example.jetlearning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetlearning.ui.theme.JetlearningTheme

class WaterCounterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetlearningTheme {
                WellnessScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetlearningTheme {
        WellnessScreen()
    }
}

@Composable
fun WellnessScreen(modifier: Modifier = Modifier) {
    val list = remember {
        getWellnessTasks().toMutableStateList()
    }
    Column {
        StatefulCounter()
        WellnessTaskList(modifier = modifier, onCloseTask = { task -> list.remove(task) }, list)
    }
}


@Composable
fun WellnessTaskList(

    modifier: Modifier = Modifier,
    onCloseTask: (WellnessTask) -> Unit,
    list: SnapshotStateList<WellnessTask>
) {
    LazyColumn(modifier = modifier) {
        items(list) { task ->
            WellnessStatefulItem(taskName = task.label, onClose = { onCloseTask(task) })
        }
    }
}


@Composable
fun WellnessStatefulItem(
    taskName: String,
    modifier: Modifier = Modifier,
    onClose: () -> Unit,
) {
    var checkedState by rememberSaveable {
        mutableStateOf(false)
    }
    WellnessTaskItem(
        taskName = taskName,
        onClose = onClose,
        modifier = modifier,
        checked = checkedState,
        onCheckedChange = { newValue -> checkedState = newValue }
    )
}

@Composable
fun WellnessTaskItem(
    taskName: String,
    onClose: () -> Unit,
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = taskName, modifier = modifier
                .weight(1f)
                .padding(start = 16.dp)
        )
        Checkbox(checked = checked, onCheckedChange = onCheckedChange)
        IconButton(onClick = onClose) {
            Icon(Icons.Filled.Close, contentDescription = null)
        }
    }
}

private fun getWellnessTasks() = List(30) { i -> WellnessTask(i, "Task # $i") }

data class WellnessTask(val id: Int, val label: String)


@Composable
fun StatefulCounter(modifier: Modifier = Modifier) {
    var count by rememberSaveable {
        mutableStateOf(0)
    }
    StatelessCounter(count = count, onIncrement = { ++count }, modifier = modifier)
}

@Composable
fun StatelessCounter(count: Int, onIncrement: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) {
            Text(text = "You've had $count glasses.")
        }
        Row {
            Button(onClick = onIncrement, modifier.padding(top = 8.dp), enabled = count <= 10) {
                Text(text = "Add one")
            }
        }
    }
}