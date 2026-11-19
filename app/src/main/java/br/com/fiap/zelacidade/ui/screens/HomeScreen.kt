package br.com.fiap.zelacidade.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Report
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fiap.zelacidade.model.Categoria
import br.com.fiap.zelacidade.model.StatusOcorrencia
import br.com.fiap.zelacidade.repository.OcorrenciaRepository
import br.com.fiap.zelacidade.ui.components.CarrosselDestaques
import br.com.fiap.zelacidade.ui.components.EstadoVazio
import br.com.fiap.zelacidade.ui.components.OcorrenciaCard
import br.com.fiap.zelacidade.ui.components.ResumoCard
import br.com.fiap.zelacidade.ui.components.SeletorCategorias
import br.com.fiap.zelacidade.ui.components.TituloSecao
import br.com.fiap.zelacidade.ui.theme.ZelaCidadeTheme
import br.com.fiap.zelacidade.ui.theme.temaEscuroAtivo

/**
 * Tela principal do ZelaCidade.
 *
 * Responsável pelo estado da categoria selecionada e por pedir ao repositório os dados
 * de cada seção. Ao trocar a categoria, o Compose recompõe a tela e a lista principal
 * é atualizada automaticamente.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    repositorio: OcorrenciaRepository,
    modifier: Modifier = Modifier
) {
    // Estado da categoria selecionada (inicia em "Todas").
    var categoriaSelecionada by remember { mutableStateOf(Categoria.TODAS) }

    // Dados obtidos do repositório: a filtragem fica fora dos componentes visuais.
    val categorias = remember { repositorio.listarCategorias() }
    val destaques = remember { repositorio.listarDestaques() }
    val ocorrencias = remember(categoriaSelecionada) {
        repositorio.filtrarPorCategoria(categoriaSelecionada)
    }

    // A barra superior fica sempre escura para manter os ícones da status bar legíveis.
    val escuro = temaEscuroAtivo()
    val corBarra = if (escuro) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.primary
    val corConteudoBarra = if (escuro) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onPrimary

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(text = "ZelaCidade", style = MaterialTheme.typography.titleLarge)
                        Text(
                            text = "Zeladoria colaborativa · São Paulo",
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                },
                actions = {
                    Icon(
                        imageVector = Icons.Filled.LocationCity,
                        contentDescription = null,
                        modifier = Modifier.padding(end = 16.dp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = corBarra,
                    titleContentColor = corConteudoBarra,
                    actionIconContentColor = corConteudoBarra
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.TopCenter
        ) {
            // Em telas largas (tablets/paisagem) o conteúdo é limitado e centralizado.
            LazyColumn(
                modifier = Modifier
                    .widthIn(max = 840.dp)
                    .fillMaxSize(),
                contentPadding = PaddingValues(top = 16.dp, bottom = 24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    ResumoCard(
                        abertas = repositorio.contarPorStatus(StatusOcorrencia.ABERTA),
                        emAndamento = repositorio.contarPorStatus(StatusOcorrencia.EM_ANDAMENTO),
                        resolvidas = repositorio.contarPorStatus(StatusOcorrencia.RESOLVIDA),
                        totalApoios = repositorio.totalDeApoios(),
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }

                item {
                    TituloSecao(
                        modifier = Modifier.padding(top = 4.dp),
                        titulo = "Prioridades da comunidade",
                        subtitulo = "Ocorrências em destaque no território",
                        icone = Icons.Filled.Star
                    )
                }
                item {
                    CarrosselDestaques(destaques = destaques)
                }

                item {
                    TituloSecao(
                        modifier = Modifier.padding(top = 4.dp),
                        titulo = "Categorias",
                        subtitulo = "Toque para filtrar as ocorrências",
                        icone = Icons.Filled.Category
                    )
                }
                item {
                    SeletorCategorias(
                        categorias = categorias,
                        categoriaSelecionada = categoriaSelecionada,
                        onCategoriaSelecionada = { categoriaSelecionada = it }
                    )
                }

                item {
                    TituloSecao(
                        modifier = Modifier.padding(top = 4.dp),
                        titulo = "Ocorrências",
                        subtitulo = textoResumoFiltro(ocorrencias.size, categoriaSelecionada),
                        icone = Icons.Filled.Report
                    )
                }

                if (ocorrencias.isEmpty()) {
                    item {
                        EstadoVazio(mensagem = "Nenhuma ocorrência registrada nesta categoria.")
                    }
                } else {
                    items(items = ocorrencias, key = { it.id }) { ocorrencia ->
                        OcorrenciaCard(
                            ocorrencia = ocorrencia,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }
                }
            }
        }
    }
}

/** Monta o subtítulo da lista, ex.: "3 ocorrências em Alagamentos". */
private fun textoResumoFiltro(quantidade: Int, categoria: Categoria): String {
    val registros = if (quantidade == 1) "1 ocorrência" else "$quantidade ocorrências"
    return if (categoria == Categoria.TODAS) {
        "$registros em todas as categorias"
    } else {
        "$registros em ${categoria.rotulo}"
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    ZelaCidadeTheme {
        HomeScreen(repositorio = OcorrenciaRepository())
    }
}
