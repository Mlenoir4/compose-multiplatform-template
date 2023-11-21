import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable()
internal fun questionScreen(quiz: Quiz) {
    var questionProgress by remember { mutableStateOf(0) }
    var selectedAnswer by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf(0) }
    val test = arrayOf("A", "B", "C", "D")

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.padding(20.dp, 80.dp),
        ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = quiz.questions[questionProgress].label,
                fontSize = 30.sp,
                modifier = Modifier.padding(all = 10.dp)
            )
            quiz.questions[questionProgress].answers.forEach { answer ->
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        modifier = Modifier.height(80.dp).width(400.dp),
                        onClick = {
                            selectedAnswer = answer.id
                        }
                    ) {
                        Text(test[answer.id - 1] + " " + answer.label)
                    }
                }
            }
            Button(
                modifier = Modifier.padding(all = 10.dp),
                onClick = {
                    if (quiz.questions[questionProgress].correctAnswerId == selectedAnswer)
                        score++
                    if ( questionProgress == quiz.questions.size-1)
                        // scoreScreen(score)
                    else
                        questionProgress++
                }
            ) {
                if (questionProgress == quiz.questions.size-1) Text("Score") else Text("Next")
            }
        }
    }
}