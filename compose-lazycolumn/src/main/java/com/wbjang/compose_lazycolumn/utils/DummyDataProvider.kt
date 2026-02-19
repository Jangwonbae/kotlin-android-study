package com.wbjang.compose_lazycolumn.utils

data class RandomUser(
    val name: String = "개발 실습",
    val description: String = "오늘도 빡고딩 하고 계신가요",
    val profilePictureUrl: String = "https://randomuser.me/api/portraits/men/75.jpg"
)
object DummyDataProvider {
    val userList = List<RandomUser>(200) { RandomUser() }
}