package com.wbjang.compose_lazycolumn

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.wbjang.compose_lazycolumn.ui.theme.AndroidStudyTheme
import com.wbjang.compose_lazycolumn.utils.DummyDataProvider
import com.wbjang.compose_lazycolumn.utils.RandomUser

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidStudyTheme {
                ContentView()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentView() {
    Scaffold(
        topBar = { MyAppBar() }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            // 여기에 화면의 콘텐츠를 추가하세요.
            RandomUserListView(randomUsers = DummyDataProvider.userList)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppBar() {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.my_app_name),
            modifier = Modifier.padding(8.dp))}
        ,  colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}
@Composable
fun RandomUserListView(randomUsers: List<RandomUser>) {
    //메모리 관리가 들어간 LazyColumn
    LazyColumn() {
        items(randomUsers) {
            RandomUserView(it)
        }
    }
}
@Composable
fun RandomUserView(randomUser: RandomUser) {
    val typography = MaterialTheme.typography
    Card(
        modifier = Modifier.fillMaxWidth().padding(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)) {

            ProfileImage(imgUrl = randomUser.profilePictureUrl,)

            Column() {
                Text(text = randomUser.name,
                    style = typography.titleMedium)
                Text(text = randomUser.description,
                    style = typography.bodyLarge
                    )
            }
        }

    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun ProfileImage(imgUrl: String, modifier: Modifier = Modifier) {
    // 이미지 비트맵
    val bitmap : MutableState<Bitmap?> = mutableStateOf(null)

    val imageModifier = modifier
        .size(50.dp,50.dp)
        .clip(CircleShape)
    Glide.with(LocalContext.current)
        .asBitmap()
        .load(imgUrl)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmap.value = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {}
        })

    // 비트맵이 있다면
    bitmap.value?.asImageBitmap()?.let {
        fetchedBitmap ->
        Image(bitmap = fetchedBitmap,
            contentScale = ContentScale.Fit,
            contentDescription = null,
            modifier = imageModifier)
    } ?: Image(painter = painterResource(id = R.drawable.ic_empty_user_img),
        contentScale = ContentScale.Fit,
        contentDescription = null,
        modifier = imageModifier)
}
@Preview(showBackground = true)
@Composable
fun LazyColumnGreetingPreview() {
    AndroidStudyTheme {
        ContentView()
    }
}
