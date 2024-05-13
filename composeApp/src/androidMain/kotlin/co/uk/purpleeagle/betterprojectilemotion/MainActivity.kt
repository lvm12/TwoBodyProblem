package co.uk.purpleeagle.betterprojectilemotion

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import viewmodel.PmViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = PmViewModelFactory().create(PmViewModel::class.java)
        setContent {
            App(viewModel)
        }
    }
}

