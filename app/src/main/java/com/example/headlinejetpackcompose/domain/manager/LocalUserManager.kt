package com.example.headlinejetpackcompose.domain.manager

import kotlinx.coroutines.flow.Flow

interface LocalUserManager {

    suspend fun saveEntry()

    suspend fun readAppEntry() : Flow<Boolean>
}