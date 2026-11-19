package br.com.fiap.zelacidade.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fiap.zelacidade.model.Categoria
import br.com.fiap.zelacidade.ui.theme.ZelaCidadeTheme

/**
 * Chip de uma categoria. Quando [selecionada] é verdadeiro, o chip recebe a cor
 * primária do tema, destacando visualmente o filtro ativo.
 */
@Composable
fun CategoriaChip(
    categoria: Categoria,
    selecionada: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FilterChip(
        selected = selecionada,
        onClick = onClick,
        modifier = modifier,
        label = {
            Text(
                text = categoria.rotulo,
                style = MaterialTheme.typography.labelLarge
            )
        },
        leadingIcon = {
            Icon(
                imageVector = categoria.icone,
                contentDescription = null,
                modifier = Modifier.size(FilterChipDefaults.IconSize)
            )
        },
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = MaterialTheme.colorScheme.primary,
            selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
            selectedLeadingIconColor = MaterialTheme.colorScheme.onPrimary,
            iconColor = MaterialTheme.colorScheme.primary
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun CategoriaChipPreview() {
    ZelaCidadeTheme {
        Row(modifier = Modifier.padding(8.dp)) {
            CategoriaChip(categoria = Categoria.TODAS, selecionada = true, onClick = {})
            CategoriaChip(
                categoria = Categoria.ILUMINACAO,
                selecionada = false,
                onClick = {},
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}
