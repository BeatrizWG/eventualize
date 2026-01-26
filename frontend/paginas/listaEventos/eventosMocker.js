/**
 * Mocker para gerenciar dados de eventos
 * Fornece funções para manipular e acessar os dados de eventos de forma constante
 */

class EventosMocker {
    constructor() {
        this.eventos = [];
        this.carregarEventos();
    }

    /**
     * Carrega os eventos do arquivo JSON
     */
    async carregarEventos() {
        try {
            const response = await fetch('eventosMock.json');
            const data = await response.json();
            this.eventos = data.eventos || [];
        } catch (error) {
            console.error('Erro ao carregar eventos:', error);
            this.eventos = [];
        }
    }

    /**
     * Retorna todos os eventos
     * @returns {Array} Lista de eventos
     */
    obterTodosEventos() {
        return this.eventos;
    }

    /**
     * Busca um evento por ID
     * @param {number} idEvento - ID do evento
     * @returns {Object|null} Evento encontrado ou null
     */
    obterEventoPorId(idEvento) {
        return this.eventos.find(evento => evento.idEvento === idEvento) || null;
    }

    /**
     * Adiciona um novo evento
     * @param {Object} novoEvento - Objeto do evento a ser adicionado
     * @returns {Object} Evento adicionado com ID gerado
     */
    adicionarEvento(novoEvento) {
        const proximoId = this.eventos.length > 0 
            ? Math.max(...this.eventos.map(e => e.idEvento)) + 1 
            : 1;
        
        const evento = {
            idEvento: proximoId,
            ...novoEvento
        };
        
        this.eventos.push(evento);
        return evento;
    }

    /**
     * Atualiza um evento existente
     * @param {number} idEvento - ID do evento a ser atualizado
     * @param {Object} dadosAtualizados - Dados para atualizar
     * @returns {Object|null} Evento atualizado ou null se não encontrado
     */
    atualizarEvento(idEvento, dadosAtualizados) {
        const index = this.eventos.findIndex(e => e.idEvento === idEvento);
        
        if (index === -1) {
            return null;
        }
        
        this.eventos[index] = {
            ...this.eventos[index],
            ...dadosAtualizados,
            idEvento: idEvento // Garante que o ID não seja alterado
        };
        
        return this.eventos[index];
    }

    /**
     * Remove um evento
     * @param {number} idEvento - ID do evento a ser removido
     * @returns {boolean} True se removido com sucesso, false caso contrário
     */
    removerEvento(idEvento) {
        const index = this.eventos.findIndex(e => e.idEvento === idEvento);
        
        if (index === -1) {
            return false;
        }
        
        this.eventos.splice(index, 1);
        return true;
    }

    /**
     * Busca eventos por título (busca parcial)
     * @param {string} termo - Termo de busca
     * @returns {Array} Lista de eventos encontrados
     */
    buscarEventosPorTitulo(termo) {
        const termoLower = termo.toLowerCase();
        return this.eventos.filter(evento => 
            evento.titulo.toLowerCase().includes(termoLower)
        );
    }

    /**
     * Busca eventos por data
     * @param {string} data - Data no formato YYYY-MM-DD
     * @returns {Array} Lista de eventos encontrados
     */
    buscarEventosPorData(data) {
        return this.eventos.filter(evento => evento.data === data);
    }

    /**
     * Busca eventos por local
     * @param {string} local - Nome do local
     * @returns {Array} Lista de eventos encontrados
     */
    buscarEventosPorLocal(local) {
        const localLower = local.toLowerCase();
        return this.eventos.filter(evento => 
            evento.local.toLowerCase().includes(localLower)
        );
    }

    /**
     * Retorna eventos ordenados por data
     * @param {boolean} crescente - Se true, ordena do mais antigo para o mais recente
     * @returns {Array} Lista de eventos ordenados
     */
    obterEventosOrdenadosPorData(crescente = true) {
        return [...this.eventos].sort((a, b) => {
            const dataA = new Date(a.data);
            const dataB = new Date(b.data);
            return crescente ? dataA - dataB : dataB - dataA;
        });
    }

    /**
     * Retorna a quantidade total de eventos
     * @returns {number} Quantidade de eventos
     */
    obterQuantidadeEventos() {
        return this.eventos.length;
    }

    /**
     * Limpa todos os eventos (útil para testes)
     */
    limparEventos() {
        this.eventos = [];
    }

    /**
     * Reseta os eventos para os dados iniciais do JSON
     */
    async resetarEventos() {
        await this.carregarEventos();
    }
}

// Instância global do mocker
const eventosMocker = new EventosMocker();

// Aguarda o carregamento dos dados antes de disponibilizar
window.addEventListener('DOMContentLoaded', async () => {
    await eventosMocker.carregarEventos();
});
