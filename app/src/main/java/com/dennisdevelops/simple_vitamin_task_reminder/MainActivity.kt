package com.dennisdevelops.simple_vitamin_task_reminder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
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

                        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                            Heading("Today")
                            Spacer(modifier = Modifier.height(20.dp))
                            LazyColumn(modifier = Modifier.fillMaxSize()) {
                                itemsIndexed(taskList) { index, task ->
                                    TaskCard(title = task, description = "Lorem ipsum dolor")

                                    if (index != task.length - 1) {
                                        Spacer(modifier = Modifier.height(10.dp))
                                    }
                                }

                                items(10) {
                                    TaskCard(title = "Test", description = "Lorem ipsum dolor")
                                    Spacer(modifier = Modifier.height(10.dp))

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

fun TaskCard(title: String, description: String? = null) {

    var isChecked by remember { mutableStateOf(false) }
    Card(
        elevation = 12.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        shape = RoundedCornerShape(10.dp)
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

                TextForTextCard(
                    title = title,
                    description = description,
                    modifier = Modifier,
                    completed = isChecked
                )
                Checkbox(checked = isChecked, onCheckedChange = {
                    isChecked = it
                })

            }
        }


    }
}

@Composable
fun TextForTextCard(
    title: String,
    description: String? = null,
    modifier: Modifier,
    completed: Boolean
) {

    val textColor by animateColorAsState(targetValue = if (completed) Color.Gray else Color.Black)
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            color = textColor,
            textDecoration = if (completed) TextDecoration.LineThrough else TextDecoration.None
        )
        if (description != null) {
            Text(
                text = description,
                fontWeight = FontWeight.Light,
                fontSize = 10.sp,
                color = textColor,
                textDecoration = if (completed) TextDecoration.LineThrough else TextDecoration.None
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewText() {
    SimplevitamintaskreminderTheme {
        TaskCard(title = "Multivitamin", description = "Take the Vitamins every day only once")


    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SimplevitamintaskreminderTheme {
        TaskCard(title = "Multivitamin")
    }
}