import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.ExperimentalResourceApi

private val repository = QuizRepository()


@Composable
fun App() {

    Card(
        //modifier = Modifier.background(Color(23,23,23)),
        backgroundColor = Color(221,221,251),
        modifier = Modifier.fillMaxSize()
    ) {

        val questions = repository.questionState.collectAsState()
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