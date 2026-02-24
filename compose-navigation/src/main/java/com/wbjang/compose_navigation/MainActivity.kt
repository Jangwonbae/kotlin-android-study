package com.wbjang.compose_navigation

import android.R.attr.onClick
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wbjang.compose_navigation.ui.theme.AndroidStudyTheme

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
                        NavigationGraph()
                    }
                }
            }
        }
    }
}
//네비게이션 라우트 이넘(값을 가지는 이넘)
enum class NAV_ROUTE(val routeName: String, val description: String, var btnColor: Color) {
    MAIN("MAIN", "메인 화면", Color(0xFF90CAF9)),
    LOGIN("LOGIN", "로그인 화면", Color(0xFFFFCC80)),
    REGISTER("REGISTER", "회원가입 화면", Color(0xFFA5D6A7)),
    USER_PROFILE("USER_PROFILE", "유저 프로필 화면", Color(0xFFF48FB1)),
    SETTING("SETTING", "설정 화면", Color(0xFFB39DDB))
}

//네비게이션 라우트 액션
class RouteAction(navHostController: NavHostController) {
    //특정 라우트로 이동
    val navTo: (NAV_ROUTE) -> Unit = { route ->
        navHostController.navigate(route.routeName)
    }
    
    //뒤로가기 이동
    val goBack: () -> Unit = {
        navHostController.navigateUp()
    }
}
@Composable
fun NavigationGraph(startRoute: NAV_ROUTE = NAV_ROUTE.MAIN) {
    //네비게이션 컨트롤러
    val navController = rememberNavController()

    //네비게이션 라우트 액션
    val routeAction = remember(navController) { RouteAction(navController) }

    // NavHost로 네비게이션 결정
    // 네비게이션 연결할 녀석들을 설정한다
    NavHost(navController, startRoute.routeName) {

        //라우트 이름 = 화면의 키
        composable(NAV_ROUTE.MAIN.routeName) {
            // 화면 = 값
            MainScreen(routeAction)
        }
        //라우트 이름 = 화면의 키
        composable(NAV_ROUTE.LOGIN.routeName) {
            // 화면 = 값
            LoginScreen(routeAction)
        }
        //라우트 이름 = 화면의 키
        composable(NAV_ROUTE.REGISTER.routeName) {
            // 화면 = 값
            RegisterScreen(routeAction)
        }
        //라우트 이름 = 화면의 키
        composable(NAV_ROUTE.USER_PROFILE.routeName) {
            // 화면 = 값
            UserProfileScreen(routeAction)
        }
        //라우트 이름 = 화면의 키
        composable(NAV_ROUTE.SETTING.routeName) {
            // 화면 = 값
            SettingScreen(routeAction)
        }
    }
}

//메인 화면
@Composable
fun MainScreen(routeAction: RouteAction) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(16.dp)) {
//            NavButton(NAV_ROUTE.MAIN,routeAction)
            NavButton(NAV_ROUTE.LOGIN,routeAction)
            NavButton(NAV_ROUTE.REGISTER,routeAction)
            NavButton(NAV_ROUTE.USER_PROFILE,routeAction)
            NavButton(NAV_ROUTE.SETTING,routeAction)
        }
    }
}
//로그인 화면
@Composable
fun LoginScreen(routeAction: RouteAction) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
                ) {
                Box(Modifier.padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("로그인 화면", style = TextStyle(Color.Black, 22.sp, FontWeight.Medium))
                    Button(onClick = routeAction.goBack,
                        modifier = Modifier.padding(16.dp)
                            .offset(y= 100.dp)
                    ) {
                        Text(text = "뒤로가기")
                    }
                }
            }
        }
    }
}
//회원가입 화면
@Composable
fun RegisterScreen(routeAction: RouteAction) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(Modifier.padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("회원가입 화면", style = TextStyle(Color.Black, 22.sp, FontWeight.Medium))
                    Button(onClick = routeAction.goBack,
                        modifier = Modifier.padding(16.dp)
                            .offset(y= 100.dp)
                    ) {
                        Text(text = "뒤로가기")
                    }
                }
            }
        }
    }
}
//유저 프로필 화면
@Composable
fun UserProfileScreen(routeAction: RouteAction) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(Modifier.padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("유저 프로필 화면", style = TextStyle(Color.Black, 22.sp, FontWeight.Medium))
                    Button(onClick = routeAction.goBack,
                        modifier = Modifier.padding(16.dp)
                            .offset(y= 100.dp)
                    ) {
                        Text(text = "뒤로가기")
                    }
                }
            }
        }
    }
}
//설정 화면
@Composable
fun SettingScreen(routeAction: RouteAction) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally

//                horizontalAlignment = Alignment.CenterHorizontally
//                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(Modifier.padding(8.dp),
                    contentAlignment = Alignment.Center
                    ) {
                    Text("설정 화면", style = TextStyle(Color.Black, 22.sp, FontWeight.Medium))
                    Button(onClick = routeAction.goBack,
                        modifier = Modifier.padding(16.dp)
                            .offset(y= 100.dp)
                    ) {
                        Text(text = "뒤로가기")
                    }
                }
            }
        }
    }
}


// 컬럼에 있는 네비게이션 버튼
@Composable
fun ColumnScope.NavButton(route: NAV_ROUTE, routeAction: RouteAction) {
    Button(onClick = {
        routeAction.navTo(route)
    }, colors = ButtonDefaults.buttonColors(containerColor = route.btnColor),
        modifier = Modifier.padding(8.dp).fillMaxSize().weight(1f)) {
        Text(route.description,
            style = TextStyle(Color.White, 22.sp, FontWeight.Medium)
        )
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidStudyTheme {
        NavigationGraph()
    }
}