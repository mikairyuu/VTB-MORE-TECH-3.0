package ru.vtb.moretech.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

const val TAG = "TESTING"

@HiltViewModel
class DayViewModel @Inject constructor(): ViewModel() {

    val dayState = MutableStateFlow(0)
    val headerState = MutableStateFlow(0)
    val message = MutableStateFlow(0)
    val answer = MutableStateFlow(0)

    init {

    }

    fun onClick(item: Int) {
        Log.d(TAG, item.toString())
    }

}