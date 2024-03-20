package com.karolina.jetpack.newsapp.domain.usecases.app_entry

import com.karolina.jetpack.newsapp.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }
}