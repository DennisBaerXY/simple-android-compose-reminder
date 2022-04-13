package com.dennisdevelops.simple_vitamin_task_reminder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.dennisdevelops.simple_vitamin_task_reminder.ui.theme.SimplevitamintaskreminderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimplevitamintaskreminderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val scaffoldState = rememberScaffoldState()

                    val onClick = {

                    }
                    Scaffold(scaffoldState = scaffoldState,
                        floatingActionButtonPosition = FabPosition.Center,
                        floatingActionButton = {
                            ExtendedFloatingActionButton(
                                text = { Text(text = "Add a new Task!") },
                                onClick = onClick
                            )
                        }


                    ) {
                        val taskList = remember {
                            mutableStateListOf("Multivitamin", "Omega-3", "Sport")
                        }

                        Column(modifier = Modifier.padding(16.dp)) {
                            Heading("Today")
                            LazyColumn {
                                items(taskList) { task ->
                                    TaskCard(name = task)
                                }
                            }
                        }
                    }


                }
            }
        }
    }
}

@Composable
fun Heading(name: String) {
    Text(text = name, fontSize = 30.sp, fontWeight = FontWeight.Bold)
}


@Composable

fun TaskCard(name: String) {

    var isChecked by remember { mutableStateOf(false) }
    Card(
        elevation = 12.dp, modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = name)
                Checkbox(checked = isChecked, onCheckedChange = {
                    isChecked = it
                })
            }
        }


    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SimplevitamintaskreminderTheme {
        TaskCard(name = "Multivitamin")
    }
}