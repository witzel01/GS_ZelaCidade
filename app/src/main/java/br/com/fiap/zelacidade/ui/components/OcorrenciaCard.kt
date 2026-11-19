package br.com.fiap.zelacidade.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fiap.zelacidade.model.Ocorrencia
import br.com.fiap.zelacidade.repository.OcorrenciaRepository
import br.com.fiap.zelacidade.ui.theme.ZelaCidadeTheme

/**
 * Card de um registro da lista principal (LazyColumn).
 *
 * Exibe imagem, título e informações complementares (bairro, status, apoios, dias em
 * aberto e categorias). Ao tocar, o card expande para mostrar o relato e o endereço.
 */
@Composable
fun OcorrenciaCard(
    ocorrencia: Ocorrencia,
    modifier: Modifier = Modifier
) {
    var expandido by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.large)
            .clickable { expandido = !expandido },
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .animateContentSize()
        ) {
            Row(verticalAlignment = Alignment.Top) {
                Image(
                    painter = painterResource(id = ocorrencia.imagem),
                    contentDescription = "Ilustração: ${ocorrencia.titulo}",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(88.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = ocorrencia.titulo,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    InfoItem(icone = Icons.Filled.LocationOn, texto = ocorrencia.bairro)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        StatusBadge(status = ocorrencia.status)
                        Spacer(modifier = Modifier.weight(1f))
                        InfoItem(
                            icone = Icons.Filled.ThumbUp,
                            texto = textoApoios(ocorrencia.apoios),
                            cor = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                ocorrencia.categorias.forEach { categoria ->
                    Icon(
                        imageVector = categoria.icone,
                        contentDescription = categoria.rotulo,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .padding(end = 6.dp)
                            .size(18.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                InfoItem(icone = Icons.Filled.Schedule, texto = textoDias(ocorrencia.diasEmAberto))
                Icon(
                    imageVector = if (expandido) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = if (expandido) "Recolher detalhes" else "Ver detalhes",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }

            if (expandido) {
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 10.dp),
                    color = MaterialTheme.colorScheme.outlineVariant
                )
                Text(
                    text = ocorrencia.descricao,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(8.dp))
                InfoItem(icone = Icons.Filled.Place, texto = ocorrencia.endereco)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Categorias: " + ocorrencia.categorias.joinToString(" · ") { it.rotulo },
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360)
@Composable
private fun OcorrenciaCardPreview() {
    ZelaCidadeTheme {
        OcorrenciaCard(
            ocorrencia = OcorrenciaRepository().listarOcorrencias().first(),
            modifier = Modifier.padding(16.dp)
        )
    }
}
