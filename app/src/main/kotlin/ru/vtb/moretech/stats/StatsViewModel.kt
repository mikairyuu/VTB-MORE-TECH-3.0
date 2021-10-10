package ru.vtb.moretech.stats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.vtb.stats.domain.entity.Token
import ru.vtb.stats.domain.entity.UserStatsSummary
import ru.vtb.stats.domain.usecase.GetStatsUseCase
import ru.vtb.storage.PreferencesProvider
import javax.inject.Inject

@HiltViewModel
class StatsViewModel @Inject constructor(
    private val userStatisticUseCase: GetStatsUseCase,
    private val preferencesProvider: PreferencesProvider
) : ViewModel() {

    val userStats: MutableStateFlow<UserStatsSummary?> = MutableStateFlow(null)

    init {
        viewModelScope.launch {
            val mToken = preferencesProvider.getUserToken()
            userStats.emit(userStatisticUseCase(Token(token = mToken!!)))
        }
    }
}