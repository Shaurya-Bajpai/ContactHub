package com.example.contacthub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.contacthub.graph.ContactsApp.Companion.prefs
import com.example.contacthub.navigation.NavigationControl
import com.example.contacthub.ui.theme.AppTheme
import com.example.contacthub.viewmodel.ConViewModel

class MainActivity : ComponentActivity() {

    private val themeViewModel: ConViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val themeDark = prefs!!.themeDark
            themeDark.let {
                themeViewModel.setTheme(it)
            }
            AppTheme(useDarkTheme = themeViewModel.isDarkThemeEnabled.value) {
                NavigationControl()
            }
        }
    }
}