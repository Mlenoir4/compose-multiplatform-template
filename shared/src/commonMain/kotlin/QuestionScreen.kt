import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import moe.tlaster.precompose.navigation.Navigator

@Composable()
internal fun questionScreen(navigator: Navigator, quiz: Quiz, pseudo: String) {
    var questionProgress by remember { mutableStateOf(0) }
    var selectedAnswer by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf(0) }
    val test = arrayOf("A", "B", "C", "D")
    Column(

    ) {
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.padding(20.dp, 80.dp),
            backgroundColor = Color(242, 243, 253)
        ) {
            Column(
            ) {
                Text(
                    text = quiz.questions[questionProgress].label,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(all = 30.dp),
                    fontWeight = FontWeight.Bold,
                    //fontFamily = FontFamily.Serif
                )
                quiz.questions[questionProgress].answers.forEach { answer ->
                    Row(
                        modifier = Modifier.padding(horizontal = 16.dp),
                    ) {
                        Button(
                            modifier = Modifier
                                .height(80.dp)
                                .width(400.dp)
                                .padding(7.dp),
                            colors = if (selectedAnswer == answer.id) ButtonDefaults.buttonColors(
                                Color(104, 104, 226)
                            ) else ButtonDefaults.buttonColors(Color.White),
                            onClick = {
                                selectedAnswer = answer.id
                            }
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentAlignment = Alignment.CenterStart,

                            ){
                                Text(
                                    test[answer.id - 1],
                                    color = if (selectedAnswer == answer.id) Color(
                                        255,
                                        255,
                                        255
                                    ) else Color(68, 133, 232),
                                    modifier = Modifier
                                        .background(
                                            color = if (selectedAnswer == answer.id) Color(
                                                119,
                                                113,
                                                239
                                            ) else Color(221, 226, 249),
                                            shape = RoundedCornerShape(25.dp)
                                        )
                                        .padding(9.dp, 5.dp)
                                )
                                Text(
                                    answer.label,
                                    color = if (selectedAnswer == answer.id) Color(
                                        255,
                                        255,
                                        255
                                    ) else Color(48, 45, 85),
                                    textAlign = TextAlign.Justify,
                                    modifier = Modifier.padding(40.dp, 0.dp)
                                )
                            }

                        }
                    }
                }
                Button(
                    modifier = Modifier.height(60.dp).width(100.dp).padding(all = 10.dp)
                        .align(Alignment.End),
                    colors = ButtonDefaults.buttonColors(Color(102, 83, 243)),
                    onClick = {
                        if (quiz.questions[questionProgress].correctAnswerId == selectedAnswer)
                            score++
                        if (questionProgress == quiz.questions.size - 1)
                            navigator.navigate(route = "/score/${score}/${pseudo}")
                        else
                            questionProgress++
                        selectedAnswer = 0
                    }
                ) {
                    if (questionProgress == quiz.questions.size - 1) Text(
                        "Score",
                        color = Color.White
                    ) else Text("Next", color = Color.White)
                }
            }
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier.align(BottomCenter)
            ) {
                loadingBar(navigator, questionProgress, quiz.questions.size)
            }
        }
    }
}