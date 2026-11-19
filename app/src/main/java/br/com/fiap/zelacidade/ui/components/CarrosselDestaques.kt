package br.com.fiap.zelacidade.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fiap.zelacidade.model.Ocorrencia
import br.com.fiap.zelacidade.repository.OcorrenciaRepository
import br.com.fiap.zelacidade.ui.theme.ZelaCidadeTheme

/**
 * Lista horizontal (LazyRow) com as ocorrências em destaque.
 *
 * A largura de cada card se adapta ao tamanho da tela: em celulares ocupa cerca de 3/4
 * da largura (deixando o próximo card aparecer, indicando a rolagem); em tablets usa
 * uma largura fixa maior.
 */
@Composable
fun CarrosselDestaques(
    destaques: List<Ocorrencia>,
    modifier: Modifier = Modifier
) {
    val larguraTela = LocalConfiguration.current.screenWidthDp.dp
    val larguraCard = if (larguraTela < 600.dp) larguraTela * 0.75f else 300.dp

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items = destaques, key = { it.id }) { ocorrencia ->
            DestaqueCard(
                ocorrencia = ocorrencia,
                modifier = Modifier.width(larguraCard)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CarrosselDestaquesPreview() {
    ZelaCidadeTheme {
        CarrosselDestaques(destaques = OcorrenciaRepository().listarDestaques())
    }
}
