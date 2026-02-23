package com.wbjang.compose_button

import android.R.attr.onClick
import android.R.attr.shadowRadius
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.wbjang.compose_button.ui.theme.AndroidStudyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidStudyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
                        ButtonContainer()
                    }
                }
            }
        }
    }
}

//onClick: () -> Unit,
//modifier: Modifier = Modifier,
//enabled: Boolean = true,
//shape: Shape = ButtonDefaults.shape,
//colors: ButtonColors = ButtonDefaults.buttonColors(),
//elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
//border: BorderStroke? = null,
//contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
//interactionSource: MutableInteractionSource? = null,
//content: @Composable RowScope.() -> Unit

// Button
// enabled: 클릭 여부 처리
// interactionSource: 사용자의 인터렉션 처리
// elevation: 그림자 즉 버튼을 위로 뛰우면서 그림자 그리기
// 커스텀 그림자 넣는 법
// shape: 모양
// border: 테두리
// color: 버튼 색
// contentPadding: 내용물 밀어넣는 공간

@Composable
fun ButtonContainer() {
    val buttonBorderGradient = Brush.horizontalGradient(listOf(Color.Yellow, Color.Red))
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val interactionSourceForSecondBtn = remember { MutableInteractionSource() }
    val isPressedForSecondBtn by interactionSourceForSecondBtn.collectIsPressedAsState()

    val pressStatusTitle = if(isPressed) "버튼을 누르고 있다" else "버튼에서 손을 땠다"
    val pressedBtnRadius = if(isPressedForSecondBtn) 0.dp else 20.dp
    val pressedBtnRadiusWithAnim: Dp by animateDpAsState(targetValue = if(isPressedForSecondBtn) 0.dp else 20.dp)
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            enabled = true,
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 0.dp
            ),
            onClick = {
            Log.d("TAG", "ButtonContainer : ")
        }) {
            Text(text = "버튼 1")
        }
        Button(
            enabled = true,
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 10.dp,
                pressedElevation = 10.dp,
                disabledElevation = 0.dp
            ),
            onClick = {
                Log.d("TAG", "ButtonContainer : ")
            }) {
            Text(text = "버튼 2")
        }
        Button(
            enabled = true,
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 10.dp,
                pressedElevation = 10.dp,
                disabledElevation = 0.dp
            ),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(4.dp, Color.Red),
            contentPadding = PaddingValues(top = 200.dp, bottom = 20.dp, start = 100.dp, end = 100.dp),
            onClick = {
                Log.d("TAG", "ButtonContainer : ")
            }) {
            Text(text = "버튼 3")
        }
        Button(
            enabled = true,
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 10.dp,
                pressedElevation = 10.dp,
                disabledElevation = 0.dp
            ),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(4.dp, buttonBorderGradient),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                disabledContainerColor = Color.LightGray
            ),
            interactionSource = interactionSource,
            onClick = {
                Log.d("TAG", "ButtonContainer : ")
            }) {
            Text(text = "버튼 5")
        }
        Button(
            enabled = true,
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp
            ),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(4.dp, buttonBorderGradient),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                disabledContainerColor = Color.LightGray
            ),
            interactionSource = interactionSourceForSecondBtn,
            modifier = Modifier.drawColoredShadow(
                color = Color.Red,
                alpha = 0.5f,
                borderRadius = 10.dp,
                shadowRadius = pressedBtnRadiusWithAnim,
                offsetY = 0.dp,
                offsetX = 0.dp
            ),
            onClick = {
                Log.d("TAG", "ButtonContainer : ")
            }) {
            Text(text = "버튼 5")
        }
        Text(text = pressStatusTitle)
//        if(isPressed) {
//            Text(text = "버튼을 누르고 있다")
//        } else {
//            Text(text = "버튼에서 손을 땠다")
//        }
//        color: Color,
//        alpha: Float = 0.2f,
//        borderRadius: Dp = 0.dp,
//        shadowRadius: Dp = 20.dp,
//        offsetY: Dp = 0.dp,
//        offsetX: Dp = 0.dp
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidStudyTheme {
        ButtonContainer()
    }
}