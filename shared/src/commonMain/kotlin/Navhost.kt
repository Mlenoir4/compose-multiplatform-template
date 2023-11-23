import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition
import kotlin.random.Random

private val quizRepository = QuizRepository()

@Composable
internal fun rootNavHost() {

    val navigator = rememberNavigator()
    NavHost(
        navigator = navigator,
        navTransition = NavTransition(),
        initialRoute = "/welcome",
    ) {
        scene(
            route = "/welcome",
            navTransition = NavTransition(),
        ) {
            welcomeScreen(navigator)
        }
        scene(
            route = "/quiz/{pseudo}/{limit}",
            navTransition = NavTransition(),
        ) { backStackEntry ->
            backStackEntry.path<String>("pseudo")?.let { pseudo ->
                backStackEntry.path<Int>("limit")?.let { limit ->
                    val questions: List<Question> = quizRepository.questionState.value
                    val listQuestion = ArrayList<Question>()
                    var i = 1
                    while (i <= limit) {
                        val ran = (questions.indices-1).random()
                        if (!listQuestion.contains(questions[ran])) {
                            listQuestion.add(questions[ran])
                            i++
                        }
                    }
                    val quiz = Quiz(listQuestion)
                    questionScreen(navigator, quiz, pseudo)

                }
            }
        }
        scene(
            route = "/score/{score}/{pseudo}",
            navTransition = NavTransition(),
        ) { backStackEntry ->
            backStackEntry.path<String>("score")?.let { score ->
                backStackEntry.path<String>("pseudo")?.let { pseudo ->
                    scoreScreen(navigator, score, pseudo)
                }
            }
        }
    }
}

