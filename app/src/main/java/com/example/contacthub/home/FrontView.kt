package com.example.contacthub.home

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.contacthub.data.Con
import com.example.contacthub.navigation.Screen
import com.example.contacthub.viewmodel.ConViewModel
import com.example.contacthub.R

@Composable
fun FrontView(
    viewModel: ConViewModel,
    navController: NavController
) {
    val context = LocalContext.current

    Scaffold(
        topBar = { TopBar(title = "Contacts", viewModel = viewModel) },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(20.dp),
                contentColor = Color.White,
                backgroundColor = colorResource(id = R.color.topbar_color),
                onClick = {
//                    Toast.makeText(context, "FAB Clicked", Toast.LENGTH_LONG).show()
                    navController.navigate(Screen.addScreen.route + "/0L")
                }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = null)
            }
        }
    ) {

        val conList = viewModel.getAllCon.collectAsState(initial = listOf())
        
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            items(conList.value, key = { con -> con.id }){

                con -> ConItem(con = con, {
                    val id = con.id
                    navController.navigate(Screen.addScreen.route + "/$id")
                }, {
                    viewModel.deleteCon(con = con)
                })
            }
        }
    }
}

@Composable
fun ConItem(con: Con, onClickEdit: ()->Unit, onClickDelete: ()->Unit ) {
    androidx.compose.material3.Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
        elevation = CardDefaults.cardElevation(disabledElevation = 50.dp),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, Color.Black),
//        colors = CardDefaults.cardColors(Color.Unspecified)
    ) {
        
        var deleteOption by remember { mutableStateOf(false) }
        Row {
            Column(
                modifier = Modifier
                .padding(16.dp)
                .weight(2f)
            ) {
                Text(text = con.title, fontWeight = FontWeight.ExtraBold)
                Text(text = con.description)
            }

            IconButton(onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(16.dp)
                    .weight(.5f)
            ) {
                Icon(
                    imageVector = Icons.Filled.Phone,
                    contentDescription = null,
                    tint = Color.Black
                )
            }

            IconButton(onClick = { onClickEdit() },
                modifier = Modifier
                    .padding(16.dp)
                    .weight(.5f)
            ) {
                Icon(
                    imageVector = Icons.Filled.Create,
                    contentDescription = null,
                    tint = Color.Black
                )
            }
            IconButton(onClick = { deleteOption = true },
                modifier = Modifier
                    .padding(16.dp)
                    .weight(.5f)
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = null,
                    tint = Color.Red
                )
            }
            if(deleteOption){
                AlertDialog(
                    onDismissRequest = {
                        deleteOption = false
                        Log.d("AlertDialog", "no")
                    },
                    title = { Text("Delete") },
                    text = { Text("Do you want to delete ?") },
                    confirmButton = {
                        FilledTonalButton(onClick = {
                            deleteOption = false

                            onClickDelete()

                            Log.d("AlertDialog", "yes")
                        }) {
                            Text("Delete")
                        }
                    },
                    dismissButton = {
                        FilledTonalButton(onClick = {
                            deleteOption = false
                            Log.d("AlertDialog", "no")
                        }) {
                            Text("Cancel")
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PriviewTheme(){
    ConItem(Con(),{},{})
}