package ru.vtb.moretech.screen

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.vtb.storage.PreferencesProvider
import javax.inject.Inject

const val TAG = "TESTING"

@HiltViewModel
class DayViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val preferencesProvider: PreferencesProvider
): ViewModel() {

    val dayState = MutableStateFlow(0)
    val headerState = MutableStateFlow(1)
    private val message = MutableStateFlow(1)
    val answer = MutableStateFlow(1)

    val messages = mutableListOf<String>()

    val isNeededToNavigate = MutableStateFlow(false)

    init {

    }

    fun onClick(item: Int) {
        Log.d(TAG, item.toString())
        if (answer.value != 8) {
            answer.value++
            if (headerState.value < 2) headerState.value++
            if (answer.value > 2) {
                addMsg()
            }
        }
        else {
            viewModelScope.launch {
                preferencesProvider.savePassedInitialIntroduction()
                isNeededToNavigate.value = true
            }
        }
    }

    private fun addMsg() {
        val id =context.resources.getIdentifier("message_title_${dayState.value}_${message.value++}", "string", "ru.vtb.moretech")
        messages.add(context.getString(id))
        if (answer.value == 4) {
            val idd =context.resources.getIdentifier("message_title_${dayState.value}_${message.value++}", "string", "ru.vtb.moretech")
            messages.add(context.getString(idd))
        }
    }

}