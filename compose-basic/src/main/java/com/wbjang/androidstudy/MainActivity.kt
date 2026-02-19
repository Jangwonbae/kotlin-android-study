package com.wbjang.androidstudy

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wbjang.androidstudy.ui.theme.AndroidStudyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidStudyTheme() {
                Greeting("안드입니다.")
            }
        }
    }
}

// 뷰
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(name: String) {
    Scaffold(topBar = {
        TopAppBar(title = { Text("TopAppBar") },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xfff25287)
            )
        )
    }, floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = { FloatingActionButton(onClick = {}) { Text("클릭") } },
    ) { innerPadding ->
        MyComposableView(modifier = Modifier.padding(innerPadding))
    }
}
@Composable
fun RowItem() {
    Log.d("TAG", "RowItem: ")
    Row(
        modifier = Modifier
            .padding(10.dp)
            .background(Color(0xffeaffd0))
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "안녕하세요", Modifier.padding(all = 10.dp).background(Color.Yellow))
        Spacer(Modifier.size(10.dp))
        Text(text = "안녕하세요")
        Text(text = "안녕하세요")
    }
}
@Composable
fun MyComposableView(modifier: Modifier = Modifier) {
    Log.d("TAG", "MyComposableView: ")
    // 호리젠탈 리니어
    Column(modifier.background(Color.Green).verticalScroll(rememberScrollState())) {
        for(i in 0..4) {
            RowItem()
        }
    }
}

// 미리보기
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidStudyTheme {
        Greeting("안드입니다.")
    }
}
