package br.com.fiap.zelacidade.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fiap.zelacidade.ui.theme.ZelaCidadeTheme

/**
 * Card de boas-vindas com o resumo das ocorrências do território: quantas estão
 * abertas, em andamento e resolvidas, e quantos apoios os moradores já registraram.
 */
@Composable
fun ResumoCard(
    abertas: Int,
    emAndamento: Int,
    resolvidas: Int,
    totalApoios: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Filled.Campaign, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Sua rua, seu cuidado",
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "$totalApoios apoios de moradores ajudam a priorizar os reparos do bairro.",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                IndicadorResumo(valor = abertas, rotulo = "Abertas", modifier = Modifier.weight(1f))
                IndicadorResumo(valor = emAndamento, rotulo = "Em andamento", modifier = Modifier.weight(1f))
                IndicadorResumo(valor = resolvidas, rotulo = "Resolvidas", modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun IndicadorResumo(
    valor: Int,
    rotulo: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = valor.toString(),
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = rotulo,
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Center,
            maxLines = 1
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ResumoCardPreview() {
    ZelaCidadeTheme {
        ResumoCard(
            abertas = 5,
            emAndamento = 3,
            resolvidas = 2,
            totalApoios = 1235,
            modifier = Modifier.padding(16.dp)
        )
    }
}
