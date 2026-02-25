package com.wbjang.compose_codelab_day3

import android.R.attr.text
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wbjang.compose_codelab_day3.ui.theme.AndroidStudyTheme

@SuppressLint("UnrememberedMutableState")
@Composable
fun WaterCounter(modifier: Modifier = Modifier) {

//    Column(modifier = modifier
//        .padding(16.dp)
//    ) {
//        // Changes to count are now tracked by Compose
//        // 이제 Compose에서 카운트 변경 사항을 추적합니다.
//        val count : MutableState<Int> = remember { mutableIntStateOf(0) }
////        var count by remember { mutableIntStateOf(0) }
////        val (count, setCount)  = remember { mutableIntStateOf(0) }
//
//        if(count.value > 0) {
//            var showTask by remember { mutableStateOf(true) }
//            if (showTask) {
//                WellnessTaskItem(
//                    onClose = { showTask = false },
//                    taskName = "Have you taken your 15 minute walk today?"
//                )
//            }
//            Text("You've had ${count.value} glasses.")
//        }
//        Row(
//            modifier = Modifier.padding(8.dp)
//        ) {
//            Button(onClick = { count.value++ }, enabled = count.value < 10) {
//                Text("Add one")
//            }
//            Button(
//                onClick = { count.value = 0 },
//                Modifier.padding(start = 8.dp)) {
//                Text("Clear water count")
//            }
//        }
//    }
    Column(modifier = modifier.padding(16.dp)) {
        var count by rememberSaveable() { mutableStateOf(0) }
        if (count > 0) {
            Text("You've had $count glasses.")
        }
        Button(onClick = { count++ }, Modifier.padding(top = 8.dp), enabled = count < 10) {
            Text("Add one")
        }
    }
}
@Composable
fun StatelessCounter(count: Int, onIncrement: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) {
            Text("You've had $count glasses.")
        }
        Button(onClick = onIncrement, Modifier.padding(top = 8.dp), enabled = count < 10) {
            Text("Add one")
        }
    }
}
@Composable
fun StatefulCounter(modifier: Modifier = Modifier) {
    var count by rememberSaveable { mutableStateOf(0) }
    var juiceCount by remember { mutableStateOf(0) }
    Column() {
        StatelessCounter(count, { count++ }, modifier)
//        StatelessCounter(juiceCount, { juiceCount++ })
    }

}

@Preview(showBackground = true)
@Composable
fun WaterCounterPreview() {
    AndroidStudyTheme {
        StatefulCounter()
    }
}