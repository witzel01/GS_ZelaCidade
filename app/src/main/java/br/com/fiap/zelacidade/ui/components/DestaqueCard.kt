package br.com.fiap.zelacidade.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fiap.zelacidade.model.Ocorrencia
import br.com.fiap.zelacidade.repository.OcorrenciaRepository
import br.com.fiap.zelacidade.ui.theme.ZelaCidadeTheme

/**
 * Card de uma ocorrência em destaque (prioridade da comunidade), exibido na LazyRow
 * de destaques. Mostra imagem, título, bairro, status e número de apoios.
 */
@Composable
fun DestaqueCard(
    ocorrencia: Ocorrencia,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.width(260.dp),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box {
            Image(
                painter = painterResource(id = ocorrencia.imagem),
                contentDescription = "Ilustração: ${ocorrencia.titulo}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
            )
            Surface(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(10.dp),
                shape = RoundedCornerShape(percent = 50),
                color = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "Prioridade", style = MaterialTheme.typography.labelMedium)
                }
            }
        }
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = ocorrencia.titulo,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                minLines = 2,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(6.dp))
            InfoItem(icone = Icons.Filled.LocationOn, texto = ocorrencia.bairro)
            Spacer(modifier = Modifier.height(10.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                StatusBadge(status = ocorrencia.status)
                Spacer(modifier = Modifier.weight(1f))
                InfoItem(
                    icone = Icons.Filled.ThumbUp,
                    texto = ocorrencia.apoios.toString(),
                    cor = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DestaqueCardPreview() {
    ZelaCidadeTheme {
        DestaqueCard(
            ocorrencia = OcorrenciaRepository().listarDestaques().first(),
            modifier = Modifier.padding(16.dp)
        )
    }
}
