package com.wbjang.compose_snackbar

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.wbjang.compose_snackbar.ui.theme.AndroidStudyTheme
import kotlinx.coroutines.launch
import kotlin.time.Duration

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidStudyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
                        MySnackbar()
                    }
                }
            }
        }
    }
}

@Composable
fun MySnackbar() {
    val snackbarHostState = remember { SnackbarHostState() }

    val coroutineScope = rememberCoroutineScope()

    val buttonTitle : (SnackbarData?) -> String = { snackbarData ->
        if (snackbarData != null) {
            "스낵바 숨기기"
        } else {
            "스낵바 보여주기"
        }
    }
    val buttonColor : (SnackbarData?) -> Color = { snackbarData ->
        if (snackbarData != null) {
            Color.Black
        } else {
            Color.Blue
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            colors =  ButtonDefaults.buttonColors(
                containerColor = buttonColor(snackbarHostState.currentSnackbarData)
            ),
            onClick = {
                Log.d("TAG", "MySnackbar : 스냅바 버튼 클릭")
                if(snackbarHostState.currentSnackbarData != null) {
                    Log.d("TAG", "MySnackbar : 이미 스낵바가 있다")
                    snackbarHostState.currentSnackbarData?.dismiss()
                    return@Button
                }
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        "스낵바가 보여집니다.",
                        "확인",
                        duration = SnackbarDuration.Short
                ).let {
                    when (it) {
                        SnackbarResult.Dismissed -> Log.d("TAG", "MySnackbar : 스냅바 닫아짐")
                        SnackbarResult.ActionPerformed -> Log.d("TAG", "MySnackbar : 스냅바 확인 버튼 클릭")
                    }
                }
            }
        }) {
            Text(text = buttonTitle(snackbarHostState.currentSnackbarData))
        }
        SnackbarHost(hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidStudyTheme {
        MySnackbar()
    }
}