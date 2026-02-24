package com.wbjang.compose_codelab_day1

import ads_mobile_sdk.h4
import android.R
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wbjang.compose_codelab_day1.ui.theme.AndroidStudyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidStudyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),) {
                        ContentView()
                    }
                }
            }
        }
    }
}

@Composable
private fun ContentView() {
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    if (shouldShowOnboarding) {
        OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
    } else {
//        MyColumn(modifier = Modifier.padding(20.dp))
        MyLazyColumn(modifier = Modifier.padding(20.dp))
    }

}
@Composable
fun MyLazyColumn(fruits: List<String> = List(1000){"번호 : $it"}, modifier: Modifier = Modifier) {
    LazyColumn(modifier.background(Color(0xFFC5E1A5))) {
        items(items = fruits) {aFruit->
            FruitView(aFruit)
        }

    }
}

@Composable
fun MyColumn(fruits: List<String> = listOf("바나나", "딸기", "포도"), modifier: Modifier = Modifier) {
    Column(modifier.background(Color(0xFFC5E1A5))) {
        for (aFruit in fruits) {
            FruitView(aFruit)
        }
    }
}
@Composable
fun FruitView(name : String ){
    val expanded = rememberSaveable() { mutableStateOf(false) }
//    val isOpen by remember { mutableStateOf(false) }
//    val (shouldOpen, setShouldOpen) = remember { mutableStateOf(false) }

//    val extraPadding by animateDpAsState(
//        if (expanded.value) 48.dp else 0.dp
//    )
    val extraPadding = animateDpAsState(
        if (expanded.value) 48.dp else 0.dp
    )

    Surface(
        color = Color(0xFFF48FB1),
        modifier = Modifier
            .padding(bottom = extraPadding.value)
            .clickable {
            Log.d("TAG", "FruitView: $name")
        }
    ) {
        Row(modifier = Modifier
            .padding(end = 10.dp)
            .fillMaxWidth(fraction = 1f),
            verticalAlignment = Alignment.CenterVertically) {
            Text(text = name, modifier = Modifier
                .padding(10.dp)
                .weight(1f)
//                .fillMaxWidth(fraction = 0.3f)
                .background(Color(0xFFA5D6A7)),
                style = MaterialTheme.typography.bodyMedium
            )
            OutlinedButton(onClick = {
                expanded.value = !expanded.value
                Log.d("TAG", "FruitView: 버튼클릭 $name")

            }) {
                Text(text = if(expanded.value) "열렸다" else "닫혔다")
            }
        }

    }
}
@Composable
fun OnboardingScreen(
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Basics Codelab!")
        Button(
            modifier = Modifier
                .padding(vertical = 24.dp),
            onClick = onContinueClicked
        ) {
            Text("Continue")
        }
    }

}
@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "GreetingPreviewDark"
)
@Composable
fun GreetingPreview() {
    AndroidStudyTheme {
//        HelloWorld()
        ContentView()
//        MyLazyColumn()
    }
}