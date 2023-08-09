package com.shana.foodandgrocery.data.networkMonitoring

import kotlinx.coroutines.flow.Flow

interface NetworkMonitor {
    val isOnline: Flow<Boolean>
}