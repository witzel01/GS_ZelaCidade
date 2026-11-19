package br.com.fiap.zelacidade.model

/**
 * Categorias de zeladoria urbana usadas para classificar e filtrar as ocorrências.
 *
 * [TODAS] é uma opção exclusiva do filtro: ela não é atribuída a nenhuma ocorrência
 * e, quando selecionada, faz a lista principal exibir todos os registros.
 */
enum class Categoria(val rotulo: String) {
    TODAS("Todas"),
    ILUMINACAO("Iluminação"),
    VIAS_E_CALCADAS("Vias e calçadas"),
    ACESSIBILIDADE("Acessibilidade"),
    ALAGAMENTOS("Alagamentos"),
    LIMPEZA("Limpeza urbana"),
    AREAS_VERDES("Áreas verdes"),
    SEGURANCA("Segurança")
}
