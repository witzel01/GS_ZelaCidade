package br.com.fiap.zelacidade

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import br.com.fiap.zelacidade.repository.OcorrenciaRepository
import br.com.fiap.zelacidade.ui.screens.HomeScreen
import br.com.fiap.zelacidade.ui.theme.ZelaCidadeTheme

class MainActivity : ComponentActivity() {

    // Única instância do repositório, compartilhada com as telas.
    private val repositorio = OcorrenciaRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Ícones claros na status bar, pois a TopAppBar usa sempre um fundo escuro.
        enableEdgeToEdge(statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT))
        setContent {
            ZelaCidadeTheme {
                HomeScreen(repositorio = repositorio)
            }
        }
    }
}
