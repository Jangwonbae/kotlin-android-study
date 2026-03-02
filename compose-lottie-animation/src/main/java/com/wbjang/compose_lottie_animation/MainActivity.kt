package com.wbjang.compose_lottie_animation

import android.R.attr.text
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.wbjang.compose_lottie_animation.ui.theme.AndroidStudyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidStudyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        LottieContainer()
                    }
                }
            }
        }
    }
}
@Composable
fun LottieContainer() {
    Loader()
}

//composition: LottieComposition?,
//isPlaying: Boolean = true,
//restartOnPlay: Boolean = true,
//clipSpec: LottieClipSpec? = null,
//speed: Float = 1f,
//iterations: Int = 1,
//cancellationBehavior: LottieCancellationBehavior = LottieCancellationBehavior.Immediately,
//ignoreSystemAnimatorScale: Boolean = false,
@Composable
fun HeartBGAnimation() {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.Url("https://lottie.host/4fe24f90-6ee4-47ec-9ec4-846b2e4742c8/zjsYNERcV4.lottie")
    )
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
    )

    val aspectRatio = composition?.bounds?.let {
        if (it.height() > 0) it.width().toFloat() / it.height() else null
    }

    LottieAnimation(
        composition = composition,
        progress = progress,
        modifier = Modifier
            .fillMaxWidth()
            .then(
                if (aspectRatio != null) {
                    Modifier.aspectRatio(aspectRatio)
                } else {
                    Modifier
                }
            )
//            .background(Color.Yellow)
    )
}
@Composable
fun WbjnagAnimation() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 로띠 제이슨 파일 가져오기
        val composition by rememberLottieComposition(LottieCompositionSpec.Asset("some_drawing.json"))

        var isAnimationPlaying by remember { mutableStateOf(true) }
        // 로띠 애니메이션 설정
        val progress by animateLottieCompositionAsState(
            composition,
            iterations = LottieConstants.IterateForever,
            clipSpec = LottieClipSpec.Progress(0f, 0.6f),
            isPlaying = isAnimationPlaying

        )
        LottieAnimation(
            composition = composition,
            progress =  progress,
            modifier = Modifier
                .size(300.dp)
//                .background(Color.Yellow)
        )
        Text(text = "progress : $progress")
        Button(onClick = { 
            isAnimationPlaying = !isAnimationPlaying
        }) {
            Text(text = "로띠 재생/일시 정지")
        }
    }
}

@Composable
fun Loader() {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        HeartBGAnimation()
        WbjnagAnimation()

    }
}

@Preview(showBackground = true)
@Composable
fun LottieContainerPreview() {
    AndroidStudyTheme {
        Loader()
    }
}
