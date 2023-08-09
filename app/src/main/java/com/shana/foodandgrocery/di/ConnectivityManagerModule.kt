package com.shana.foodandgrocery.di

import com.shana.foodandgrocery.data.networkMonitoring.ConnectivityManagerNetworkMonitor
import com.shana.foodandgrocery.data.networkMonitoring.NetworkMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ConnectivityManagerModule {
    @Binds
    fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor,
    ): NetworkMonitor
}