import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.jetbrains.compose.resources.ExperimentalResourceApi

private val repository = QuizRepository()


@Composable
fun App() {
    var questionsProgress by remember { mutableStateOf(0) }


    MaterialTheme {
        val questions = repository.questionState.collectAsState()
        val quizExemple =  Quiz(listOf(
            Question(
                1,
                "Android is a great platform ?",
                1,
                listOf(Answer(1, "YES"), Answer(2, "NO"), Answer(3, "HIHIHI"))
            ),
            Question(
                1,
                "Android is a bad platform ?",
                2,
                listOf(Answer(1, "YES"), Answer(2, "NO"))
            )
        ))
        val quiz = Quiz(questions.value)
        if(questions.value.isNotEmpty()) {
            questionScreen(quiz)
        }
        //WelcomeScreen()
        //ScoreScreen("10/20")
        //questionScreen(quiz)
    }
}


expect fun getPlatformName(): String