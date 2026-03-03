package com.wbjang.compose_kakaotalk_oauth

import android.R.attr.onClick
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kakao.sdk.common.util.Utility
import com.wbjang.compose_kakaotalk_oauth.ui.theme.AndroidStudyTheme

class MainActivity : ComponentActivity() {

    private val kakaoOauthViewModel : KakaoOauthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val keyHash = Utility.getKeyHash(this)
        Log.d("KakaoKeyHash", keyHash)
        enableEdgeToEdge()
        setContent {
            AndroidStudyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(Modifier
                        .padding(innerPadding)
                        .fillMaxSize()) {
                        KakaoLoginView(kakaoOauthViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun KakaoLoginView(viewModel: KakaoOauthViewModel) {
    val isLoggedIn = viewModel.isLoggedIn.collectAsState()
    val loginStatusInfoTitle = if(isLoggedIn.value) "로그인 상태" else "로그아웃 상태"
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Spacer(modifier = Modifier
            .height(10.dp))
        Button(onClick = {
            viewModel.kakaoLogin()
        }) {
            Text(text = "카카오 로그인하기")
        }
        Button(onClick = {
            viewModel.kakaoLogout()
        }) {
            Text(text = "카카오 로그아웃하기")
        }
        Text(text = "카카오 로그인 여부 $loginStatusInfoTitle", textAlign = TextAlign.Center, fontSize = 20.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidStudyTheme {
//        KakaoLoginView()
    }
}