package com.example.contacthub.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.contacthub.R
import com.example.contacthub.R.drawable.baseline_dark_mode_24
import com.example.contacthub.R.drawable.baseline_light_mode_24
import com.example.contacthub.graph.ContactsApp.Companion.prefs
import com.example.contacthub.viewmodel.ConViewModel

@Composable
fun TopBar(title: String, onBackClick: ()->Unit = {}, viewModel: ConViewModel) {
    var switchState by remember { viewModel.isDarkThemeEnabled }
    val icon: (@Composable () -> Unit) = if (switchState) {
        {
            androidx.compose.material3.Icon(
                imageVector = ImageVector.vectorResource(baseline_light_mode_24),
                contentDescription = null,
                modifier = Modifier.size(SwitchDefaults.IconSize),
            )
        }
    } else {
        {
            androidx.compose.material3.Icon(
                imageVector = ImageVector.vectorResource(baseline_dark_mode_24),
                contentDescription = null,
                modifier = Modifier.size(SwitchDefaults.IconSize),
            )
        }
    }

    val navigationButton : (@Composable ()->Unit)? =
        if(!title.contains("Contacts")){
            {
                IconButton(onClick = { onBackClick() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        }
        else{
            null
        }

    TopAppBar(
        title = {
            Text(text = title,
                color = colorResource(id = R.color.white))
        }, actions = {
            Switch(
                modifier = Modifier.padding(horizontal = 8.dp),
                checked = switchState,
                onCheckedChange = {
                    switchState = !switchState
                    prefs!!.themeDark = switchState
                },
                thumbContent = icon
            )
        },
        elevation = 10.dp,
        backgroundColor = colorResource(id = R.color.topbar_color),
        navigationIcon = navigationButton
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
        TopBar("Contacts",{},ConViewModel())
}