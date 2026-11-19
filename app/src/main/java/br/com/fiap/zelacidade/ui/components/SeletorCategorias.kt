package br.com.fiap.zelacidade.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fiap.zelacidade.model.Categoria
import br.com.fiap.zelacidade.ui.theme.ZelaCidadeTheme

/**
 * Lista horizontal (LazyRow) de categorias para filtrar as ocorrências.
 *
 * O componente não guarda estado nem aplica regras de filtragem: ele apenas exibe a
 * [categoriaSelecionada] e avisa a tela, por [onCategoriaSelecionada], qual chip foi tocado.
 */
@Composable
fun SeletorCategorias(
    categorias: List<Categoria>,
    categoriaSelecionada: Categoria,
    onCategoriaSelecionada: (Categoria) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = categorias, key = { it.name }) { categoria ->
            CategoriaChip(
                categoria = categoria,
                selecionada = categoria == categoriaSelecionada,
                onClick = { onCategoriaSelecionada(categoria) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SeletorCategoriasPreview() {
    ZelaCidadeTheme {
        SeletorCategorias(
            categorias = Categoria.entries.toList(),
            categoriaSelecionada = Categoria.ALAGAMENTOS,
            onCategoriaSelecionada = {}
        )
    }
}
