package com.karolina.jetpack.newsapp.domain.usecases.app_entry

import com.karolina.jetpack.newsapp.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManger: LocalUserManager
) {

    suspend operator fun invoke(){
        localUserManger.saveAppEntry()
    }

}