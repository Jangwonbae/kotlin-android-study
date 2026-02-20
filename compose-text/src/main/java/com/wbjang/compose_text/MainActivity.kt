package com.wbjang.compose_text

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wbjang.compose_text.ui.theme.AndroidStudyTheme

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
                        TextContainer()
                    }
                }
            }
        }
    }
}


//text: String,
//modifier: Modifier = Modifier,
//color: Color = Color.Unspecified,
//fontSize: TextUnit = TextUnit.Unspecified,
//fontStyle: FontStyle? = null,
//fontWeight: FontWeight? = null,
//fontFamily: FontFamily? = null,
//letterSpacing: TextUnit = TextUnit.Unspecified,
//textDecoration: TextDecoration? = null,
//textAlign: TextAlign? = null,
//lineHeight: TextUnit = TextUnit.Unspecified,
//overflow: TextOverflow = TextOverflow.Clip,
//softWrap: Boolean = true,
//maxLines: Int = Int.MAX_VALUE,
//minLines: Int = 1,
//onTextLayout: ((TextLayoutResult) -> Unit)? = null,
//style: TextStyle = LocalTextStyle.current
@Composable
fun TextContainer() {
    val name = "원배"

    val scrollState = rememberScrollState()

    var words = stringResource(id = R.string.dummy_short_text)
    var wordsArray = words.split(" ")
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .verticalScroll(scrollState)) {
        Text(text = "안녕하세여? $name",
            style = TextStyle(
                textAlign = TextAlign.Start
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow)
        )
        Text(text = "안녕하세여? $name",
            style = TextStyle(
                textAlign = TextAlign.End
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow)
        )
        Text(text = "안녕하세여? $name",
            style = TextStyle(
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow)
        )
        Text(text = stringResource(id = R.string.dummy_short_text),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                textAlign = TextAlign.Justify,
                textDecoration = TextDecoration.combine(
                    decorations = listOf(
                        TextDecoration.Underline,
                        TextDecoration.LineThrough
                    )
                )
            ),
//            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow)
        )
        Text(text = stringResource(R.string.dummy_short_text),
            style = TextStyle(
                textAlign = TextAlign.Start,
                fontFamily = FontFamily(Font(R.font.moneygraphy_pixel, FontWeight.ExtraBold)),
                lineHeight = 40.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow)
        )
        Text(text = buildAnnotatedString {
            append("안녕하세여?")
            withStyle(style = SpanStyle(
                color = Color.Blue,
                fontSize = 40.sp,
                fontWeight = FontWeight.ExtraBold),) {
                append("취준 중인 원배입니다.?")
            }
            withStyle(style = SpanStyle(
                color = Color.Red)) {
                append("빡코딩?")
            }
        })
        Text(text = buildAnnotatedString {
            wordsArray.forEach {
                if(it.contains("아침")) {
                    withStyle(style = SpanStyle(
                        color = Color.Blue,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold)) {
                        append("$it ")
                    }
                } else {
                    append("$it ")
                }
            }
        })
        ClickableText(text = buildAnnotatedString{
            append("클릭미!")
        }, onClick = {
            Log.d("TAG","클릭미가 클릭되었다!")
        })
        Text(text = stringResource(id = R.string.dummy_long_text),
            style = TextStyle(lineHeight = 20.sp))
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidStudyTheme {
        TextContainer()
    }
}
