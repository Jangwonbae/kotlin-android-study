package com.wbjang.compose_checkbox

import android.R.attr.checked
import android.R.attr.radius
import android.R.attr.text
import android.os.Bundle
import android.util.Log
import android.util.Log.e
import android.widget.CheckBox
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.Indication
import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wbjang.compose_checkbox.ui.theme.AndroidStudyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidStudyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(modifier = Modifier.fillMaxSize()
                        .padding(innerPadding)) {
                        CheckBoxContainer()
                    }
                }
            }
        }
    }
}
//checked: Boolean,
//onCheckedChange: ((Boolean) -> Unit)?,
//modifier: Modifier = Modifier,
//enabled: Boolean = true,
//colors: CheckboxColors = CheckboxDefaults.colors(),
//interactionSource: MutableInteractionSource? = null

// val mutableState = remember { mutableStateOf(false) }
// var value by remember { mutableStateOf(false) }
// val (value, setValue) = remember { mutableStateOf(false) }

@Composable
fun CheckBoxContainer() {
    val checkedStatusForFirst = remember { mutableStateOf(false) }
    val checkedStatusForSecond = remember { mutableStateOf(false) }
    val checkedStatusForThird = remember { mutableStateOf(false) }
//    val checkedStatusForFourth = remember { mutableStateOf(false) }

    val checkedStatesArray = listOf(
        checkedStatusForFirst,
        checkedStatusForSecond,
        checkedStatusForThird,
    )

    val allBoxChecked : (Boolean) -> Unit = {isAllChecked ->
        Log.d("TAG", "CheckBoxContainer: isAllChecked: $isAllChecked")
        checkedStatesArray.forEach {
            it.value = isAllChecked
        }
    }
//    fun allBoxChecked(isAllChecked : Boolean) {
//        Log.d("TAG", "CheckBoxContainer: isAllChecked: $isAllChecked")
//        checkedStatesArray.forEach {
//            it.value = isAllChecked
//        }
//    }

//    var checkedStatusForSecond by remember { mutableStateOf(false) }
//    val (checkedStatusForThird, setCheckedStatusForThird) = remember { mutableStateOf(false)}
//    val (checkedStatusForFourth, setCheckedStatusForFourth) = remember { mutableStateOf(false)}

    var checkedStatusForFourth : Boolean = checkedStatesArray.all{it.value}

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CheckBoxWithTitle("1번 확인 사항", checkedStatusForFirst)
        CheckBoxWithTitle("2번 확인 사항", checkedStatusForSecond)
        CheckBoxWithTitle("3번 확인 사항", checkedStatusForThird)

//        Checkbox(
//            enabled = true,
//            checked = checkedStatusForFirst.value, onCheckedChange = {isChecked ->
//                Log.d("TAG", "CheckBoxContainer: isChecked: $isChecked")
//                checkedStatusForFirst.value = isChecked
//            })
//        Checkbox(
//            enabled = true,
//            checked = checkedStatusForThird, onCheckedChange = {isChecked ->
//                Log.d("TAG", "CheckBoxContainer: isChecked: $isChecked")
//                setCheckedStatusForThird.invoke(isChecked)
//            })
        Spacer(modifier = Modifier.height(10.dp))
        AllAgreeCheckBox("모두 동의하십니까?", checkedStatusForFourth, allBoxChecked)
        Spacer(modifier = Modifier.height(10.dp))
        MyCustomCheckBox(title = "커스텀 체크 박스 입니다.", withRipple = true)
        MyCustomCheckBox(title = "커스텀 체크 박스 입니다.", withRipple = false)
//        Checkbox(
//            enabled = true,
//            checked = checkedStatusForFourth,
//            colors = CheckboxDefaults.colors(
//                checkedColor = Color.Red,
//                uncheckedColor = Color(0xFFEF9A9A),
//                checkmarkColor = Color.Black,
//                disabledCheckedColor = Color(0xFF90CAF9),
//            ),
//            onCheckedChange = {isChecked ->
//                Log.d("TAG", "CheckBoxContainer: isChecked: $isChecked")
//                setCheckedStatusForFourth.invoke(isChecked)
//            })
    }
}

//checkedColor: Color = Color.Unspecified,
//uncheckedColor: Color = Color.Unspecified,
//checkmarkColor: Color = Color.Unspecified,
//disabledCheckedColor: Color = Color.Unspecified,
//disabledUncheckedColor: Color = Color.Unspecified,
//disabledIndeterminateColor: Color = Color.Unspecified

@Composable
fun CheckBoxWithTitle(title: String, isCheckState: MutableState<Boolean>) {
    Row(
        modifier = Modifier
//            .background(Color.Yellow)
            .fillMaxWidth()
            .height(25.dp)
            .padding(start = 30.dp, end = 30.dp),
        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Checkbox(
            enabled = true,
            checked = isCheckState.value, onCheckedChange = {isChecked ->
                Log.d("TAG", "CheckBoxContainer: isChecked: $isChecked")
                isCheckState.value = isChecked
            })
        Text(text = title)
    }
}
@Composable
fun AllAgreeCheckBox(title: String, shouldChecked: Boolean,
                     allBoxChecked: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
//            .background(Color.Yellow)
            .fillMaxWidth()
            .height(25.dp)
            .padding(start = 30.dp, end = 30.dp),
        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Checkbox(
            enabled = true,
            colors = CheckboxDefaults.colors(
                checkedColor = Color.Red,
                uncheckedColor = Color(0xFFEF9A9A),
                checkmarkColor = Color.White,
                disabledCheckedColor = Color(0xFF90CAF9),
            ),
            checked = shouldChecked,
            onCheckedChange = { isChecked ->
                Log.d("TAG", "CheckBoxContainer: isChecked: $isChecked")
//                isCheckState.value = isChecked
                allBoxChecked(isChecked)
            })
        Text(text = title)
    }
}
@Composable
fun MyCustomCheckBox(title: String, withRipple: Boolean) {

//    val isChecked = remember { mutableStateOf(false) }
//    var isChecked by remember { mutableStateOf(false) }
    val (isChecked, setIsChecked) = remember { mutableStateOf(false)}
    var togglePainter = if (isChecked) R.drawable.ic_checked else R.drawable.ic_unchecked
    var checkedInfoString = if (isChecked) "checked" else "unchecked"
    var rippleEffect = if(withRipple) ripple(
        radius = 30.dp,
        bounded = false,
        color = Color.Red
    ) else null
    Row(
        modifier = Modifier
//            .background(Color.Yellow)
            .fillMaxWidth()
            .height(50.dp)
            .padding(start = 30.dp, end = 30.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
            .size(50.dp)
            .clickable(interactionSource = remember { MutableInteractionSource() }, indication = rippleEffect) {
                setIsChecked.invoke(!isChecked)
            }) {
            Image(painter = painterResource(id = togglePainter),
                contentDescription = null,
                modifier = Modifier
                    )
        }
        Text(text = "커스텀 체크박스입니다. $checkedInfoString")
    }
}
//bounded: Boolean = true,
//radius: Dp = Dp.Unspecified,
//color: Color = Color.Unspecified

//enabled = enabled,
//onClickLabel = onClickLabel,
//onClick = onClick,
//role = role,
//indication = localIndication,
//interactionSource = interactionSource

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidStudyTheme {
        CheckBoxContainer()
    }
}
