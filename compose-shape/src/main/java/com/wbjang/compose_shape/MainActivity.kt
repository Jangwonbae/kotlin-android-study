package com.wbjang.compose_shape

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.key.Key.Companion.D
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.wbjang.compose_shape.ui.theme.AndroidStudyTheme
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidStudyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
                        ShapeContainer()
                    }
                }
            }
        }
    }
}

@Composable
fun ShapeContainer() {

    var poltSides by remember { mutableIntStateOf(3) }

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        DummyBox(modifier = Modifier.clip(RectangleShape))
        DummyBox(modifier = Modifier.clip(CircleShape))
        DummyBox(modifier = Modifier.clip(RoundedCornerShape(10.dp)))
        DummyBox(modifier = Modifier.clip(CutCornerShape(10.dp)))
        DummyBox(modifier = Modifier.clip(TriangleShape()))
        DummyBox(modifier = Modifier.clip(PolyShape(poltSides, 100f)))

        Text(text = "polySides : $poltSides")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                poltSides +=1
            }) {
                Text(text = "polySides + 1")
            }
            Button(onClick = {
                poltSides = 3
            }) {
                Text(text = "초기화")
            }
        }
    }
}
class TriangleShape() : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            moveTo(size.width/2f,0f)
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            close()
        }
        return Outline.Generic(path = path)
    }
}

class PolyShape(private val sides: Int, private val radius: Float) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(path = Path().apply { this.polygon(sides, radius, size.center) })
    }
}
fun Path.polygon(sides: Int, radius: Float, center: Offset) {
    val angle = 2.0 * Math.PI / sides
    moveTo(
        x = center.x + (radius * cos(0.0)).toFloat(),
        y= center.y + (radius * sin(0.0)).toFloat()
    )
    for (i in 1 until sides) {
        lineTo(
            x = center.x + (radius * cos(angle * i)).toFloat(),
            y = center.y + (radius * sin(angle * i)).toFloat()
        )
    }
    close()
}


@Composable
fun DummyBox(modifier: Modifier = Modifier, color : Color? = null) {
    val red = Random.nextInt(256)
    val green = Random.nextInt(256)
    val blue = Random.nextInt(256)
    val randomColor = color ?: Color(red, green, blue)

    Box(modifier = modifier.size(100.dp).background(randomColor))
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidStudyTheme {
        ShapeContainer()
    }
}