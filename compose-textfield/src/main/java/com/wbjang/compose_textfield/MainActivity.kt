package com.wbjang.compose_textfield

import android.R.attr.label
import android.R.attr.value
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wbjang.compose_textfield.ui.theme.AndroidStudyTheme
import kotlinx.coroutines.sync.Mutex
import java.nio.file.WatchEvent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidStudyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
                        TextFieldTest()
                    }
                }
            }
        }
    }
}
//value: TextFieldValue,
//onValueChange: (TextFieldValue) -> Unit,
//modifier: Modifier = Modifier,
//enabled: Boolean = true,
//readOnly: Boolean = false,
//textStyle: TextStyle = LocalTextStyle.current,
//label: @Composable (() -> Unit)? = null,
//placeholder: @Composable (() -> Unit)? = null,
//leadingIcon: @Composable (() -> Unit)? = null,
//trailingIcon: @Composable (() -> Unit)? = null,
//prefix: @Composable (() -> Unit)? = null,
//suffix: @Composable (() -> Unit)? = null,
//supportingText: @Composable (() -> Unit)? = null,
//isError: Boolean = false,
//visualTransformation: VisualTransformation = VisualTransformation.None,
//keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
//keyboardActions: KeyboardActions = KeyboardActions.Default,
//singleLine: Boolean = false,
//maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
//minLines: Int = 1,
//interactionSource: MutableInteractionSource? = null,
//shape: Shape = TextFieldDefaults.shape,
//colors: TextFieldColors = TextFieldDefaults.colors()
@Composable
fun TextFieldTest() {
    var userInput by remember {mutableStateOf(TextFieldValue())}
    var phoneNumberInput by remember {mutableStateOf(TextFieldValue())}
    var emailInput by remember {mutableStateOf(TextFieldValue())}
    var passwordInput by remember {mutableStateOf(TextFieldValue())}
    val shouldShowPassword = remember { mutableStateOf(false) }

    var passwordResource : (Boolean) -> Int = {
        if(it) R.drawable.baseline_visibility_24 else R.drawable.outline_select_window_off_24
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = userInput,
            singleLine = false,
            onValueChange = {userInput = it},
            label = { Text("사용자 입력") },
            placeholder = { Text("작성해 주세요") }
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = phoneNumberInput,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            onValueChange = {phoneNumberInput = it},
            label = { Text("전화번호") },
            placeholder = { Text("010-1234-1234") }
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = emailInput,
            singleLine = true,
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null)},
//            trailingIcon = { Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null)},
            trailingIcon = { IconButton(onClick = {
                Log.d("TAG","TextFieldTest : 체크 버튼 클릭")
            })  {
                Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null)
            }},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = {emailInput = it},
            label = { Text("이메일 주소") },
            placeholder = { Text("이메일 주소를 입력해 주세요.") }
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = passwordInput,
            singleLine = true,
//            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null)},
//            trailingIcon = { Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null)},
            trailingIcon = { IconButton(onClick = {
                Log.d("TAG","TextFieldTest : 체크 버튼 클릭")
                shouldShowPassword.value = !shouldShowPassword.value
            })  {
                Icon(painterResource(id = passwordResource(shouldShowPassword.value)),contentDescription = null)
            }},
            visualTransformation = if(shouldShowPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = {passwordInput = it},
            label = { Text("비밀번호") },
            placeholder = { Text("비밀번호를 입력해 주세요.") }
        )
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidStudyTheme {
        TextFieldTest()
    }
}