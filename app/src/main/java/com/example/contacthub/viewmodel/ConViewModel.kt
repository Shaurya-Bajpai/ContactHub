package com.example.contacthub.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contacthub.graph.Graph
import com.example.contacthub.data.Con
import com.example.contacthub.database.ConRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ConViewModel(
    private var wishRepository: ConRepository = Graph.conRepository
): ViewModel() {

    var title by mutableStateOf("")
    var description by mutableStateOf("")
    var isDarkThemeEnabled = mutableStateOf(false)
        private set

    fun setTheme(isDarkTheme: Boolean) {
        isDarkThemeEnabled.value = isDarkTheme
    }

    fun titleChange(newString: String){
        title = newString
    }
    fun descriptionChange(newString: String){
        description = newString
    }

    lateinit var getAllCon: Flow<List<Con>>

    init {
        viewModelScope.launch {
            getAllCon = wishRepository.getCon()
        }
    }

    fun addCon(con: Con){
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.addCon(con = con)
        }
    }

    fun getConById(id: Long): Flow<Con> {
        return wishRepository.getConById(id)
    }

    fun updateCon(con: Con){
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.updateCon(con = con)
        }
    }

    fun deleteCon(con: Con){
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.deleteCon(con = con)
        }
    }

}