package com.example.mgames.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mgames.domain.GameItem
import com.example.mgames.domain.GetGamesFromApiUseCase
import com.example.mgames.domain.GetGamesFromDatabaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getGamesFromApiUseCase: GetGamesFromApiUseCase,
    private val getGamesFromDatabaseUseCase: GetGamesFromDatabaseUseCase
) : ViewModel() {

    private val _games = MutableLiveData<List<GameItem>>()
    val games: LiveData<List<GameItem>> get() = _games

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> get() = _message

    private val _recyclerViewVisibility = MutableLiveData<Boolean>()
    val recyclerViewVisibility: LiveData<Boolean> get() = _recyclerViewVisibility

    private val _progressBarVisibility = MutableLiveData<Boolean>()
    val progressBarVisibility: LiveData<Boolean> get() = _progressBarVisibility

    private val _textViewVisibility = MutableLiveData<Boolean>()
    val textViewVisibility: LiveData<Boolean> get() = _textViewVisibility

    fun getGamesFromApi() {

        showProgressBar()

        viewModelScope.launch {
            try {
                val games = getGamesFromApiUseCase()
                if (games.isNotEmpty()) {
                    _games.value = games
                    showRecyclerView()
                }
            } catch (e: Exception) {
                getGamesFromDatabase()
            }
        }

    }


    private fun getGamesFromDatabase() {
        viewModelScope.launch {
            val games = getGamesFromDatabaseUseCase()

            if (games.isNotEmpty()) {
                _games.value = games
            } else {
                _message.value = "Terjadi error"
                showTextView()
            }
        }
    }

    private fun showRecyclerView() {
        _recyclerViewVisibility.value = true
        _textViewVisibility.value = false
        _progressBarVisibility.value = false
    }

    private fun showProgressBar() {
        _progressBarVisibility.value = true
        _recyclerViewVisibility.value = false
        _textViewVisibility.value = false
    }

    private fun showTextView() {
        _textViewVisibility.value = true
        _progressBarVisibility.value = false
        _recyclerViewVisibility.value = false
    }

}