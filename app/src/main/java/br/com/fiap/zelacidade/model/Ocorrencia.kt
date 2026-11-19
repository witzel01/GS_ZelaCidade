package br.com.fiap.zelacidade.model

import androidx.annotation.DrawableRes

/**
 * Representa um problema de zeladoria urbana registrado pela comunidade.
 *
 * @property id identificador único da ocorrência.
 * @property titulo descrição curta do problema.
 * @property descricao relato completo feito pelo cidadão.
 * @property bairro bairro onde o problema foi identificado.
 * @property endereco referência de endereço do local.
 * @property categorias uma ou mais categorias às quais a ocorrência pertence.
 * @property status situação atual de atendimento.
 * @property apoios quantidade de moradores que confirmaram o problema.
 * @property diasEmAberto dias desde o registro da ocorrência.
 * @property imagem ilustração local (drawable) que representa a ocorrência.
 * @property destaque indica se a ocorrência é prioridade da comunidade.
 */
data class Ocorrencia(
    val id: Int,
    val titulo: String,
    val descricao: String,
    val bairro: String,
    val endereco: String,
    val categorias: List<Categoria>,
    val status: StatusOcorrencia,
    val apoios: Int,
    val diasEmAberto: Int,
    @DrawableRes val imagem: Int,
    val destaque: Boolean = false
) {
    /**
     * Regra de filtragem baseada no atributo [categorias].
     * A opção [Categoria.TODAS] aceita qualquer ocorrência.
     */
    fun pertenceA(categoria: Categoria): Boolean =
        categoria == Categoria.TODAS || categoria in categorias
}
