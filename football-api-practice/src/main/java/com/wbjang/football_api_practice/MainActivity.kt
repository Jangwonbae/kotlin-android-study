package com.wbjang.football_api_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.wbjang.football_api_practice.ui.MatchDetailScreen
import com.wbjang.football_api_practice.ui.MatchListScreen
import com.wbjang.football_api_practice.ui.theme.AndroidStudyTheme
import com.wbjang.football_api_practice.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidStudyTheme {
                val viewModel: MainViewModel = hiltViewModel()
                var selectedMatchId by remember { mutableStateOf<Int?>(null) }

                if (selectedMatchId == null) {
                    MatchListScreen(
                        viewModel = viewModel,
                        onMatchClick = { id ->
                            selectedMatchId = id
                            viewModel.loadMatchDetail(id)
                        }
                    )
                } else {
                    MatchDetailScreen(
                        viewModel = viewModel,
                        onBack = {
                            selectedMatchId = null
                            viewModel.clearMatchDetail()
                        }
                    )
                    
                    // 시스템 뒤로가기 버튼 처리
                    BackHandler {
                        selectedMatchId = null
                        viewModel.clearMatchDetail()
                    }
                }
            }
        }
    }
}
