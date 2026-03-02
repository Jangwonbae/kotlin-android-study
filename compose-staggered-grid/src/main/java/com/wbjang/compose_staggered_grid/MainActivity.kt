package com.wbjang.compose_staggered_grid

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wbjang.compose_staggered_grid.ui.theme.AndroidStudyTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {

    companion object{
        const val MIN_COUNT = 2
        const val MAX_COUNT = 5
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidStudyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)) {
                        val columnCount = remember { mutableIntStateOf(2) }
                        Column() {
                            Row(
                                modifier = Modifier.padding(5.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Button(modifier = Modifier.weight(1f),
                                    onClick ={
                                    if(columnCount.value < MIN_COUNT) {
                                        return@Button
                                    }
                                    columnCount.value = columnCount.value - 1
                                }) {
                                    Text(text = "-", fontSize = 20.sp)
                                }
                                Text(modifier = Modifier.weight(1f),
                                    text = "${columnCount.value}", fontSize = 20.sp,
                                    textAlign = TextAlign.Center)
                                Button(modifier = Modifier.weight(1f),onClick ={
                                    if(columnCount.value > MAX_COUNT-1) {
                                        return@Button
                                    }
                                    columnCount.value = columnCount.value + 1
                                }) {
                                    Text(text = "+", fontSize = 20.sp)
                                }
                            }
                            MyLazyStaggeredVerticalGrid(columnCount.value)
                         }
                    }
                }
            }
        }
    }
}
//columnCount: Int,
//contentPadding: PaddingValues = PaddingValues(0.dp),
//content: @Composable LazyStaggeredGridScope.() -> Unit,
@Composable
fun MyLazyStaggeredVerticalGrid(
    columnCount : Int
) {
    var randomTexts = getRandomStringList(100)

    LazyStaggeredGrid(
        columnCount = columnCount,
        content = {
            randomTexts.forEach {
                item {
                    TextCard(text = it)
                }
            }
        }
    )

}
@Composable
fun NonLazyStaggeredVerticalGrid(
    columnCount : Int
) {
    var randomTexts = getRandomStringList(100)

    LazyColumn() {
        item {
            StaggeredVerticalGrid(
                columnCount = columnCount,
                modifier = Modifier.padding(4.dp)
            ) {
                randomTexts.forEach{ aRandomText->
                    TextCard(text = aRandomText)
                }
            }
        }
    }


}
@Composable
fun TextCard(modifier: Modifier = Modifier, text: String) {
    Card(modifier = modifier
        .fillMaxWidth()
        .padding(4.dp),
        colors = CardDefaults.cardColors(
            // 3. containerColor에 랜덤 색상을 지정합니다.
            containerColor = Color.random()
        )) {
        Text(text = text, modifier = Modifier.padding(4.dp))
    }
}

private fun getRandomString(length: Int): String {
    val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')

    return (1..length)
        .map { allowedChars.random() }
        .joinToString("")
}

fun getRandomStringList(numItems: Int): List<String> {
    return (1..numItems)
        .map {
            val itemLength = Random.nextInt(1, 150)
            getRandomString(itemLength)
        }
}

// 랜덤 칼라 가져오기
fun Color.Companion.random() : Color {
    val red = Random.nextInt(256)
    val green = Random.nextInt(256)
    val blue = Random.nextInt(256)
    return Color(red, green, blue)
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun MyStaggeredGridPreview() {
    AndroidStudyTheme {
        NonLazyStaggeredVerticalGrid(2)
    }
}