package br.com.fiap.zelacidade.model

/**
 * Situação de atendimento de uma ocorrência pelo poder público.
 */
enum class StatusOcorrencia(val rotulo: String) {
    ABERTA("Aberta"),
    EM_ANDAMENTO("Em andamento"),
    RESOLVIDA("Resolvida")
}
