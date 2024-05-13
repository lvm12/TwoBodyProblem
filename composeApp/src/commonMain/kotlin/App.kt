import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.jetbrains.compose.ui.tooling.preview.Preview
import view.Simulation
import viewmodel.PmViewModel

@Composable
@Preview
fun App(
    viewModel: PmViewModel
) {
    val world by viewModel.world.collectAsState()
    MaterialTheme {
        Simulation(
            start = viewModel::start,
            stop = viewModel::stop,
            addItem = viewModel::addItem,
            removeItem = viewModel::removeItem,
            world = world
        )
    }
}