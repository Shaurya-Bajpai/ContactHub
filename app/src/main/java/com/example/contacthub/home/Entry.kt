package com.example.contacthub.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.contacthub.data.Con
import com.example.contacthub.viewmodel.ConViewModel
import com.example.contacthub.R
import kotlinx.coroutines.launch

@Composable
fun Entry(
    id: Long,
    viewModel: ConViewModel,
    navController: NavController
) {

    val snackMessage = remember{ mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    if(id != 0L){
        val con = viewModel.getConById(id).collectAsState(initial = Con(0L,"",""))
        viewModel.title = con.value.title
        viewModel.description = con.value.description
    }
    else{
        viewModel.title = ""
        viewModel.description = ""
    }


    Scaffold(
//        topBar = { TopBar(title = "Contacts", viewModel = viewModel) },
        topBar = {
            TopBar(
                title = if (id != 0L)
                stringResource(R.string.update_con)
                else   stringResource(R.string.add_con),
                onBackClick = {navController.navigateUp()},
                viewModel = viewModel
            )
        },
        scaffoldState = scaffoldState
        ){

        Column(
            modifier = Modifier.padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            ConTextField(label = "Name", value = viewModel.title, onValueChange = { viewModel.titleChange(it) }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next), leadingIcon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = null)
            }, visualTransformation = VisualTransformation.None,)

            ConTextField(label = "Number", value = viewModel.description, onValueChange = { viewModel.descriptionChange(it) }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done), leadingIcon = {
                    Icon(imageVector = Icons.Default.Phone, contentDescription = null)
                }, visualTransformation = VisualTransformation.None,)

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier.padding(10.dp).weight(1f),
                verticalArrangement = Arrangement.Bottom
            ) {
                Button(
                    onClick = {
//                    Toast.makeText(context, "Contact Added", Toast.LENGTH_LONG).show()
                        if(viewModel.title.isNotEmpty() && viewModel.description.isNotEmpty()){
                            if(id != 0L) {
                                // TODO update contact
                                viewModel.updateCon(
                                    Con(
                                        id = id,
                                        title = viewModel.title.trim(),
                                        description = viewModel.description.trim(),
                                    )
                                )
                                snackMessage.value = "Contact has been updated"
                            }
                            else {
                                // TODO add contact
                                viewModel.addCon(
                                    Con(
                                        title = viewModel.title.trim(),
                                        description = viewModel.description.trim()
                                    )
                                )
                                snackMessage.value = "Contact has been added"
                            }
                        }
                        else{
                            snackMessage.value = "Enter Fields"
                        }
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(snackMessage.value)
                            navController.navigateUp()
                        }
                    },

                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(Color(red = 47, green = 120, blue = 197)),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "Add Contact",
                        modifier = Modifier.padding(8.dp),
                        fontSize = 20.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}


@Composable
fun ConTextField(
    label: String,
    value:String,
    onValueChange: (String)->Unit,
    keyboardOptions: KeyboardOptions,
    leadingIcon: @Composable() (()->Unit)?,
    visualTransformation: VisualTransformation,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        keyboardOptions = keyboardOptions,
        maxLines = 1,
        leadingIcon = leadingIcon,
        visualTransformation = visualTransformation
//        colors = TextFieldDefaults.outlinedTextFieldColors(
//            cursorColor = MaterialTheme.colors.onPrimary,
//            textColor = MaterialTheme.colors.onPrimary,
//            focusedBorderColor = MaterialTheme.colors.,
//            unfocusedBorderColor = Color.White,
//            focusedLabelColor = Color.White,
//            unfocusedLabelColor = Color.White
//        )
    )
}

//@Preview(showBackground = true)
//@Composable
//fun ConEntryPrev(){
//    ConTextField(
//        label = "text",
//        value = "text",
//        onValueChange = {},
//        KeyboardOptions(keyboardType = KeyboardType.Number),
//        {},
//        VisualTransformation.None
//    )
//}

@Preview(showBackground = true)
@Composable
fun ConEntryPreview(){
    Entry(0L, ConViewModel(), rememberNavController())
}