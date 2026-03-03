package com.wbjang.compose_background_exoplayer

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.wbjang.compose_background_exoplayer.ui.theme.AndroidStudyTheme
import androidx.core.net.toUri
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.AspectRatioFrameLayout

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidStudyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)) {
                        Box(contentAlignment = Alignment.Center) {
                            VideoBackground(modifier = Modifier)
                            KakaoLoginView()
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun KakaoLoginView() {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Spacer(modifier = Modifier
            .height(10.dp))
        Button(onClick = {

        }) {
            Text(text = "카카오 로그인하기")
        }
        Button(onClick = {

        }) {
            Text(text = "카카오 로그아웃하기")
        }
        Text(text = "하하하하하하", textAlign = TextAlign.Center, fontSize = 20.sp)
    }
}
@OptIn(UnstableApi::class)
@SuppressLint("RememberReturnType")
@Composable
fun VideoBackground(modifier: Modifier) {

    val mContext = LocalContext.current

    val url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
    val mediaItem = MediaItem.Builder().setUri(url.toUri()).build()

    val mExoPlayer = remember(mContext) {
        ExoPlayer.Builder(mContext).build()
            .apply {
                setMediaItem(mediaItem)
                playWhenReady = true
                prepare()
                volume = 0f
                repeatMode = Player.REPEAT_MODE_ONE


        }
    }
    Box(modifier = modifier
        .fillMaxSize()) {
        AndroidView(factory = { context->
            PlayerView(context).apply {
                player = mExoPlayer
                useController = false
                resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
            }
        })
        Box(modifier.matchParentSize().alpha(0.5f).background(Color.Black))
    }
}

@Preview(showBackground = true)
@Composable
fun ExoPlayerPreview() {
    AndroidStudyTheme {

    }
}