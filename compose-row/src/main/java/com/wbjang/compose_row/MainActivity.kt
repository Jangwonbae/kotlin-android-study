package com.wbjang.compose_row

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wbjang.compose_row.ui.theme.AndroidStudyTheme
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidStudyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(
                        modifier = Modifier.fillMaxSize().padding(innerPadding)
                    ) {
                        Container()
                    }

                }
            }
        }
    }
}
// arrangement 요소를 어떤식 배열할지
// arrangement 는 Row, Column 같은 요소들이 들어가는
// 컨테이너 성격의 콤포저블에서 요소들의 아이템을 정렬할 때 사용 됩니다.
// 웹 개발 css 에서 flex 와 비슷하다고 보시면 됩니다.

// Horizontal Alignment 이니까 Start, End, Center만 존재
// Arrangement.SpaceBetween : 공간 모두 차지
// Arrangement.Start : 왼쪽으로
// Arrangement.End : 오른쪽으로
// Arrangement.SpaceAround : 빈 공간 남겨두기
// Arrangement.Center : 요소들에 넣기
// Arrangement.SpaceBetween : 사이에 공간을 밀어넣기
// Arrangement.SpaceEvenly : 요소들 사이에 공간을 똑같이 하기

@Composable
fun Container() {
    Row(modifier = Modifier
        .background(color = Color.White)
        .fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
        ) {
        DummyBox()
        DummyBox()
        DummyBox()
    }
}
@Composable
fun DummyBox(modifier: Modifier = Modifier) {
    val red = Random.nextInt(256)
    val green = Random.nextInt(256)
    val blue = Random.nextInt(256)
    val randomColor = Color(red, green, blue)
    Box(modifier = modifier.size(100.dp).background(randomColor))
}

@Preview(showBackground = true)
@Composable
fun RowGreetingPreview() {
    AndroidStudyTheme {
        Container()
    }
}