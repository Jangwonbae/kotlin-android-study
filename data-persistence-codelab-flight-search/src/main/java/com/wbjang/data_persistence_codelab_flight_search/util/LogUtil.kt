package com.wbjang.data_persistence_codelab_flight_search.util

import android.util.Log

/**
 * 앱 전체에서 공통으로 사용할 로그 유틸리티
 */
object LogUtil {
    private const val DEFAULT_TAG = "FlightSearchApp"

    // 디버그 모드에서만 로그가 출력되도록 설정 (선택 사항)
    private const val isDebug = true 

    // 호출한 파일의 이름을 자동으로 추출하는 함수
    private fun getTag(): String {
        val stackTrace = Thread.currentThread().stackTrace
        // 0: getThreadStackTrace, 1: getStackTrace, 2: getTag, 3: d/e/i, 4: 실제 호출자
        return stackTrace.getOrNull(4)?.let { element ->
            element.fileName?.substringBefore(".")
        } ?: DEFAULT_TAG
    }

    fun d(message: String, tag: String = getTag()) {
        if (isDebug) Log.d(tag, message)
    }

    fun e(message: String, tag: String = getTag(), throwable: Throwable? = null) {
        if (isDebug) Log.e(tag, message, throwable)
    }

    fun i(message: String, tag: String = getTag()) {
        if (isDebug) Log.i(tag, message)
    }

    fun v(message: String, tag: String = getTag()) {
        if (isDebug) Log.v(tag, message)
    }

    fun w(message: String, tag: String = getTag()) {
        if (isDebug) Log.w(tag, message)
    }
}
