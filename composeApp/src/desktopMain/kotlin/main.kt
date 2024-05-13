import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.viewModelFactory
import viewmodel.PmViewModel

fun main() = application {
    val viewModel = viewModelFactory {

    }.create(
        PmViewModel::class,
        extras = CreationExtras.Empty
    )
    Window(
        onCloseRequest = ::exitApplication,
        title = "BetterProjectileMotion",
    ) {
        App(viewModel)
    }
}