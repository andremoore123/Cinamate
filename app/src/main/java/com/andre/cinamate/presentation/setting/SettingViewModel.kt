package com.andre.cinamate.presentation.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.andre.cinamate.core.domain.usecase.MovieUseCase
import com.andre.cinamate.util.preference.SettingPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val useCase: MovieUseCase,
    private val pref: SettingPreferences
) : ViewModel() {

    private var _isDarkModeActivated = MutableLiveData<Boolean>()
    val isDarkModeActivated: LiveData<Boolean> = _isDarkModeActivated

    fun getThemeSettings(): LiveData<Boolean> {
        return pref.getThemeSetting().asLiveData()
    }

    fun saveThemeSetting(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            pref.saveThemeSetting(isDarkModeActive)
        }
    }
}