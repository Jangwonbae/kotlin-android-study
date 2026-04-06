package com.wbjang.football_api_practice.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.wbjang.football_api_practice.model.Match
import com.wbjang.football_api_practice.viewmodel.MainViewModel

/**
 * 오늘의 축구 경기 목록을 표시하는 메인 화면 컴포저블입니다.
 *
 * @param viewModel 경기 데이터를 제공하는 [MainViewModel]
 * @param onMatchClick 경기를 선택했을 때 호출되는 콜백
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchListScreen(
    viewModel: MainViewModel,
    onMatchClick: (Int) -> Unit
) {
    val matches by viewModel.matches.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadTodayMatches()
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("오늘의 경기", fontWeight = FontWeight.Bold) }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (error != null) {
                Text(
                    text = error ?: "알 수 없는 에러",
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Center).padding(16.dp)
                )
            } else if (matches.isEmpty()) {
                Text(
                    text = "오늘 예정된 경기가 없습니다.",
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(matches) { match ->
                        MatchItem(
                            match = match,
                            onClick = { onMatchClick(match.id) }
                        )
                    }
                }
            }
        }
    }
}

/**
 * 개별 경기 정보를 표시하는 카드 아이템 컴포저블입니다.
 *
 * @param match 표시할 경기 정보 [Match]
 * @param onClick 아이템 클릭 시 호출되는 콜백
 */
@Composable
fun MatchItem(match: Match, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = match.competition?.name ?: "Unknown Competition",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // 홈팀
                TeamInfo(
                    name = match.homeTeam.shortName ?: match.homeTeam.name,
                    crestUrl = match.homeTeam.crest,
                    modifier = Modifier.weight(1f)
                )

                // 스코어 또는 시간
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    val homeScore = match.score.fullTime?.home ?: 0
                    val awayScore = match.score.fullTime?.away ?: 0
                    
                    Text(
                        text = "$homeScore : $awayScore",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = match.status,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                // 어웨이팀
                TeamInfo(
                    name = match.awayTeam.shortName ?: match.awayTeam.name,
                    crestUrl = match.awayTeam.crest,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

/**
 * 팀 로고와 이름을 표시하는 컴포저블입니다.
 */
@Composable
fun TeamInfo(name: String, crestUrl: String?, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = crestUrl,
            contentDescription = name,
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = name,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )
    }
}
