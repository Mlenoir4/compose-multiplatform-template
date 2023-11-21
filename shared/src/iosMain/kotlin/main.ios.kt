import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController
import moe.tlaster.precompose.PreComposeApplication

actual fun getPlatformName(): String = "iOS"

fun MainViewController(): UIViewController =
    PreComposeApplication("Quizie") {
        App()
    }