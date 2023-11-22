import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

private val repository = QuizRepository()




@Composable
internal fun App() {
    MaterialTheme {

       Row(
           modifier = Modifier.fillMaxSize().background(Color(221,221,251)),

       ){
            rootNavHost()
       }
    }

}

expect fun getPlatformName(): String