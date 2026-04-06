package com.wbjang.football_api_practice.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wbjang.football_api_practice.model.MatchDetailResponse
import com.wbjang.football_api_practice.viewmodel.MainViewModel

/**
 * 특정 경기의 상세 정보(통계, 라인업 등)를 표시하는 화면입니다.
 *
 * @param viewModel 데이터를 제공하는 [MainViewModel]
 * @param onBack 이전 화면으로 돌아가기 위한 콜백
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchDetailScreen(viewModel: MainViewModel, onBack: () -> Unit) {
    val detail by viewModel.matchDetail.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("경기 상세 정보") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "뒤로가기")
                    }
                }
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
            } else {
                detail?.let { data ->
                    MatchDetailContent(data)
                } ?: Text(
                    text = "정보를 불러올 수 없습니다.",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun MatchDetailContent(detail: MatchDetailResponse) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 스코어 섹션
        item {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "${detail.homeTeam.name} vs ${detail.awayTeam.name}",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${detail.score.fullTime?.home ?: 0} : ${detail.score.fullTime?.away ?: 0}",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }
        }

        // 통계 섹션 (예시: 점유율 등)
        item {
            Text("경기 통계", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            
            detail.homeTeam.statistics?.let { homeStats ->
                val awayStats = detail.awayTeam.statistics
                StatRow("점유율", "${homeStats.ballPossession ?: 0}%", "${awayStats?.ballPossession ?: 0}%")
                StatRow("슈팅", "${homeStats.shots ?: 0}", "${awayStats?.shots ?: 0}")
                StatRow("유효 슈팅", "${homeStats.shotsOnGoal ?: 0}", "${awayStats?.shotsOnGoal ?: 0}")
            } ?: Text("통계 정보가 없습니다.")
        }
    }
}

@Composable
fun StatRow(label: String, homeValue: String, awayValue: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(homeValue, modifier = Modifier.weight(1f))
        Text(label, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f), textAlign = androidx.compose.ui.text.style.TextAlign.Center)
        Text(awayValue, modifier = Modifier.weight(1f), textAlign = androidx.compose.ui.text.style.TextAlign.End)
    }
}
