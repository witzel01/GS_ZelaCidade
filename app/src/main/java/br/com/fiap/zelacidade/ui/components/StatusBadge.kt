package br.com.fiap.zelacidade.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fiap.zelacidade.model.StatusOcorrencia
import br.com.fiap.zelacidade.ui.theme.ZelaCidadeTheme

/**
 * Selo colorido que indica a situação de atendimento de uma ocorrência.
 */
@Composable
fun StatusBadge(
    status: StatusOcorrencia,
    modifier: Modifier = Modifier
) {
    val cor = status.cor()
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(percent = 50),
        color = cor.copy(alpha = 0.14f),
        contentColor = cor
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = status.icone,
                contentDescription = null,
                modifier = Modifier.size(14.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = status.rotulo,
                style = MaterialTheme.typography.labelMedium,
                maxLines = 1
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun StatusBadgePreview() {
    ZelaCidadeTheme {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            StatusOcorrencia.entries.forEach { StatusBadge(status = it) }
        }
    }
}
