package com.wbjang.coroutines_rest_coil_codelab_practice.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.wbjang.coroutines_rest_coil_codelab_practice.R
import com.wbjang.coroutines_rest_coil_codelab_practice.network.Amphibian

@Composable
fun HomeScreen(amphibiansUiState: AmphibiansUiState, modifier : Modifier) {
    when (amphibiansUiState) {
        is AmphibiansUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is AmphibiansUiState.Success -> PhotosGridScreen(amphibiansUiState.amphibians, modifier)
        is AmphibiansUiState.Error -> ErrorScreen(modifier = modifier.fillMaxSize())
    }

}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
    }

}

@Composable
fun AmphibiansPhotoCard(amphibian: Amphibian, modifier: Modifier = Modifier) {

    Card(
        modifier = modifier,
        shape = RectangleShape,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column() {
            Text(text = "${amphibian.name} (${amphibian.type})",
                modifier = Modifier.padding(10.dp),
                fontSize = 20.sp,
                style = MaterialTheme.typography.titleMedium)
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(amphibian.imgSrc)
                    .crossfade(true)
                    .build(),
                error = painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img),
                contentDescription = stringResource(R.string.mars_photo),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
            Text(text = amphibian.description,modifier = Modifier.padding(10.dp),
                fontSize = 15.sp,
                style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun PhotosGridScreen(
    amphibians: List<Amphibian>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(10.dp),
) {
    LazyColumn(
        modifier = modifier.padding(horizontal = 8.dp),
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(24.dp) // 아이템 사이 간격을 24dp로 설정
    ) {
        items(items = amphibians) { amphibian ->
            AmphibiansPhotoCard(
                amphibian = amphibian,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
@Preview
fun AmphibiansAppPreview() {
    AmphibiansPhotoCard(
        amphibian = Amphibian(
            name = "Great Basin Spadefoot",
            type = "Toad",
            description = "This toad spends most of its life underground due to the arid desert conditions in which it lives. Spadefoot toads earn the name because of their hind legs which are wedged to aid in digging. They are typically grey, green, or brown with dark spots.",
            imgSrc = "https://developer.android.com/codelabs/basic-android-kotlin-compose-amphibians-app/img/great-basin-spadefoot.png"
        ),
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth())
}
