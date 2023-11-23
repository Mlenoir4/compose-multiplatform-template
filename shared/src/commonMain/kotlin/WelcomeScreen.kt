import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import moe.tlaster.precompose.navigation.Navigator

@Composable()
internal fun welcomeScreen(navigator: Navigator) {
    var pseudo by remember { mutableStateOf("") }
    var limit by remember { mutableStateOf(0) }
    var selectedDifficulty = 0


    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth().fillMaxHeight()
    ) {
        Box {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Quiz",
                        fontSize = 30.sp,
                        modifier = Modifier.padding(all = 10.dp)
                    )
                    OutlinedTextField(
                        value = pseudo,
                        onValueChange = { pseudo = it },
                        label = { Text("Pseudo") },
                        modifier = Modifier.padding(20.dp, 10.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color(104, 104, 226),
                            unfocusedBorderColor = Color(242, 243, 253))
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(0.7f),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Button(
                            onClick = {  limit = 10 },
                            ){
                            Text("10")
                        }
                        Button(
                            colors = if (limit == 20) ButtonDefaults.buttonColors(
                                Color(104, 104, 226)
                            ) else ButtonDefaults.buttonColors(Color(242, 243, 253)),
                            onClick = {  limit = 20 },
                            ){
                            Text("20")
                        }
                        Button(
                            colors = if (limit == 30) ButtonDefaults.buttonColors(
                                Color(104, 104, 226)
                            ) else ButtonDefaults.buttonColors(Color(242, 243, 253)),
                            onClick = { limit = 30 },
                            ){
                            Text("30")
                        }
                    }
                    Button(
                        modifier = Modifier.padding(all = 10.dp),
                        onClick = { navigator.navigate(route = "/quiz/${pseudo}/${limit}") },
                        enabled = ( pseudo != "" && limit != 0 )

                    ) {
                        Text("Start the Quiz")
                    }
                }
            }
        }
    }
}