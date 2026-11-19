package br.com.fiap.zelacidade.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.Autorenew
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Construction
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Park
import androidx.compose.material.icons.filled.ReportProblem
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import br.com.fiap.zelacidade.model.Categoria
import br.com.fiap.zelacidade.model.StatusOcorrencia
import br.com.fiap.zelacidade.ui.theme.StatusAberta
import br.com.fiap.zelacidade.ui.theme.StatusAbertaEscuro
import br.com.fiap.zelacidade.ui.theme.StatusEmAndamento
import br.com.fiap.zelacidade.ui.theme.StatusEmAndamentoEscuro
import br.com.fiap.zelacidade.ui.theme.StatusResolvida
import br.com.fiap.zelacidade.ui.theme.StatusResolvidaEscuro
import br.com.fiap.zelacidade.ui.theme.temaEscuroAtivo

/*
 * Mapeamentos visuais dos enums do modelo. Ficam na camada de UI para que o
 * pacote "model" não dependa de classes do Compose.
 */

/** Ícone que representa cada categoria nos filtros e nos cards. */
val Categoria.icone: ImageVector
    get() = when (this) {
        Categoria.TODAS -> Icons.Filled.Apps
        Categoria.ILUMINACAO -> Icons.Filled.Lightbulb
        Categoria.VIAS_E_CALCADAS -> Icons.Filled.Construction
        Categoria.ACESSIBILIDADE -> Icons.Filled.Accessibility
        Categoria.ALAGAMENTOS -> Icons.Filled.WaterDrop
        Categoria.LIMPEZA -> Icons.Filled.Delete
        Categoria.AREAS_VERDES -> Icons.Filled.Park
        Categoria.SEGURANCA -> Icons.Filled.Shield
    }

/** Ícone que representa cada status de atendimento. */
val StatusOcorrencia.icone: ImageVector
    get() = when (this) {
        StatusOcorrencia.ABERTA -> Icons.Filled.ReportProblem
        StatusOcorrencia.EM_ANDAMENTO -> Icons.Filled.Autorenew
        StatusOcorrencia.RESOLVIDA -> Icons.Filled.CheckCircle
    }

/** Cor do status, ajustada para o tema claro ou escuro. */
@Composable
fun StatusOcorrencia.cor(): Color {
    val escuro = temaEscuroAtivo()
    return when (this) {
        StatusOcorrencia.ABERTA -> if (escuro) StatusAbertaEscuro else StatusAberta
        StatusOcorrencia.EM_ANDAMENTO -> if (escuro) StatusEmAndamentoEscuro else StatusEmAndamento
        StatusOcorrencia.RESOLVIDA -> if (escuro) StatusResolvidaEscuro else StatusResolvida
    }
}

/** Texto amigável para a quantidade de dias desde o registro. */
fun textoDias(dias: Int): String = when (dias) {
    0 -> "hoje"
    1 -> "há 1 dia"
    else -> "há $dias dias"
}

/** Texto amigável para a quantidade de apoios. */
fun textoApoios(apoios: Int): String = if (apoios == 1) "1 apoio" else "$apoios apoios"
