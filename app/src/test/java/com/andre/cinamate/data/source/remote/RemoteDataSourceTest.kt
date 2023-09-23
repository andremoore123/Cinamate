package com.andre.cinamate.data.source.remote

import com.andre.cinamate.data.source.remote.network.ApiService
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class RemoteDataSourceTest {
    // Mock ApiService
    @Mock
    private lateinit var apiService: ApiService

    // Class under test
    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setUp() {
        // Initialize Mockito annotations
        MockitoAnnotations.initMocks(this)

        // Initialize the class under test with the mocked ApiService
        remoteDataSource = RemoteDataSource(apiService)
    }

    @Test
    fun getListPopular() {
    }

    @Test
    fun getListNowPlaying() {
    }

    @Test
    fun getListTopRated() {
    }

    @Test
    fun getListUpComing() {
    }
}