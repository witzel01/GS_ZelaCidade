package br.com.fiap.zelacidade.repository

import br.com.fiap.zelacidade.R
import br.com.fiap.zelacidade.model.Categoria
import br.com.fiap.zelacidade.model.Ocorrencia
import br.com.fiap.zelacidade.model.StatusOcorrencia

/**
 * Repositório responsável por centralizar e disponibilizar as ocorrências do aplicativo.
 *
 * Os dados são mantidos em memória, separados da camada de apresentação. As telas e os
 * componentes nunca criam registros: eles apenas consomem os métodos deste repositório.
 */
class OcorrenciaRepository {

    private val ocorrencias: List<Ocorrencia> = listOf(
        Ocorrencia(
            id = 1,
            titulo = "Boca de lobo entupida causa alagamento",
            descricao = "A cada chuva forte a rua fica alagada por horas e a água invade as " +
                "casas. A boca de lobo da esquina está tomada por lixo e terra.",
            bairro = "Jardim Pantanal",
            endereco = "Rua Tietê, esquina com Rua das Garças",
            categorias = listOf(Categoria.ALAGAMENTOS, Categoria.LIMPEZA),
            status = StatusOcorrencia.ABERTA,
            apoios = 236,
            diasEmAberto = 18,
            imagem = R.drawable.ilustracao_alagamento,
            destaque = true
        ),
        Ocorrencia(
            id = 2,
            titulo = "Buraco profundo na faixa de ônibus",
            descricao = "Buraco de quase meio metro na faixa exclusiva. Ônibus desviam para a " +
                "faixa comum e motociclistas já caíram no local.",
            bairro = "Vila Prudente",
            endereco = "Av. Sapopemba, altura do nº 3.200",
            categorias = listOf(Categoria.VIAS_E_CALCADAS, Categoria.SEGURANCA),
            status = StatusOcorrencia.EM_ANDAMENTO,
            apoios = 214,
            diasEmAberto = 9,
            imagem = R.drawable.ilustracao_vias,
            destaque = true
        ),
        Ocorrencia(
            id = 3,
            titulo = "Calçada sem rampa em frente à UBS",
            descricao = "Cadeirantes e idosos precisam descer para a rua para acessar a " +
                "Unidade Básica de Saúde, disputando espaço com os carros.",
            bairro = "Itaquera",
            endereco = "Rua Gregório Ramalho, em frente à UBS",
            categorias = listOf(Categoria.ACESSIBILIDADE, Categoria.VIAS_E_CALCADAS),
            status = StatusOcorrencia.ABERTA,
            apoios = 187,
            diasEmAberto = 41,
            imagem = R.drawable.ilustracao_acessibilidade,
            destaque = true
        ),
        Ocorrencia(
            id = 4,
            titulo = "Córrego com lixo acumulado",
            descricao = "Sofás, pneus e sacos de lixo bloqueiam o fluxo do córrego, " +
                "aumentando o risco de enchente e a presença de ratos.",
            bairro = "Jardim Ângela",
            endereco = "Córrego Guavirutuba, trecho da Rua Luiz Baldinato",
            categorias = listOf(Categoria.ALAGAMENTOS, Categoria.LIMPEZA, Categoria.AREAS_VERDES),
            status = StatusOcorrencia.EM_ANDAMENTO,
            apoios = 141,
            diasEmAberto = 27,
            imagem = R.drawable.ilustracao_corrego,
            destaque = true
        ),
        Ocorrencia(
            id = 5,
            titulo = "Poste apagado no caminho da escola",
            descricao = "Trecho escuro usado por estudantes do período noturno. Moradores " +
                "relatam sensação de insegurança e casos de assalto.",
            bairro = "Consolação",
            endereco = "Rua Augusta, próximo ao nº 1.100",
            categorias = listOf(Categoria.ILUMINACAO, Categoria.SEGURANCA),
            status = StatusOcorrencia.ABERTA,
            apoios = 128,
            diasEmAberto = 12,
            imagem = R.drawable.ilustracao_iluminacao,
            destaque = true
        ),
        Ocorrencia(
            id = 6,
            titulo = "Árvore com risco de queda",
            descricao = "Árvore inclinada sobre a fiação elétrica, com galhos secos. " +
                "Após o último temporal, parte do tronco rachou.",
            bairro = "Butantã",
            endereco = "Rua Alvarenga, nº 870",
            categorias = listOf(Categoria.AREAS_VERDES, Categoria.SEGURANCA),
            status = StatusOcorrencia.EM_ANDAMENTO,
            apoios = 88,
            diasEmAberto = 6,
            imagem = R.drawable.ilustracao_arvore
        ),
        Ocorrencia(
            id = 7,
            titulo = "Descarte irregular de entulho",
            descricao = "Restos de construção são deixados toda semana no terreno da " +
                "esquina, ocupando a calçada e atraindo mais lixo.",
            bairro = "Capão Redondo",
            endereco = "Estrada de Itapecerica, nº 5.400",
            categorias = listOf(Categoria.LIMPEZA, Categoria.VIAS_E_CALCADAS),
            status = StatusOcorrencia.ABERTA,
            apoios = 64,
            diasEmAberto = 20,
            imagem = R.drawable.ilustracao_limpeza
        ),
        Ocorrencia(
            id = 8,
            titulo = "Semáforo em amarelo intermitente",
            descricao = "O semáforo do cruzamento está piscando há dias, dificultando a " +
                "travessia de pedestres e de crianças na saída da escola.",
            bairro = "Santana",
            endereco = "Av. Cruzeiro do Sul com Rua Voluntários da Pátria",
            categorias = listOf(Categoria.SEGURANCA, Categoria.VIAS_E_CALCADAS),
            status = StatusOcorrencia.RESOLVIDA,
            apoios = 73,
            diasEmAberto = 3,
            imagem = R.drawable.ilustracao_semaforo
        ),
        Ocorrencia(
            id = 9,
            titulo = "Praça sem iluminação à noite",
            descricao = "A praça é usada para caminhadas e lazer, mas fica totalmente " +
                "escura após as 18h e deixou de ser frequentada pelas famílias.",
            bairro = "Parelheiros",
            endereco = "Praça Júlio César de Campos",
            categorias = listOf(Categoria.ILUMINACAO, Categoria.AREAS_VERDES, Categoria.SEGURANCA),
            status = StatusOcorrencia.ABERTA,
            apoios = 59,
            diasEmAberto = 33,
            imagem = R.drawable.ilustracao_praca
        ),
        Ocorrencia(
            id = 10,
            titulo = "Piso tátil danificado na estação",
            descricao = "Placas do piso tátil soltas e quebradas no acesso principal, " +
                "prejudicando a orientação de pessoas com deficiência visual.",
            bairro = "Sé",
            endereco = "Acesso da Praça da Sé ao metrô",
            categorias = listOf(Categoria.ACESSIBILIDADE),
            status = StatusOcorrencia.RESOLVIDA,
            apoios = 45,
            diasEmAberto = 15,
            imagem = R.drawable.ilustracao_acessibilidade
        )
    )

    /** Retorna todas as ocorrências, das mais apoiadas para as menos apoiadas. */
    fun listarOcorrencias(): List<Ocorrencia> =
        ocorrencias.sortedByDescending { it.apoios }

    /** Retorna apenas as ocorrências marcadas como destaque (atributo [Ocorrencia.destaque]). */
    fun listarDestaques(): List<Ocorrencia> =
        listarOcorrencias().filter { it.destaque }

    /** Retorna as ocorrências que pertencem à [categoria] informada. */
    fun filtrarPorCategoria(categoria: Categoria): List<Ocorrencia> =
        listarOcorrencias().filter { it.pertenceA(categoria) }

    /** Retorna as categorias disponíveis para filtro, começando por [Categoria.TODAS]. */
    fun listarCategorias(): List<Categoria> = Categoria.entries.toList()

    /** Conta quantas ocorrências estão em determinado [status]. */
    fun contarPorStatus(status: StatusOcorrencia): Int =
        ocorrencias.count { it.status == status }

    /** Soma o total de apoios registrados pelos cidadãos. */
    fun totalDeApoios(): Int = ocorrencias.sumOf { it.apoios }
}
