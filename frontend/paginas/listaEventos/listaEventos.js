/**
 * Script principal para exibir a lista de eventos
 */

// Aguarda o carregamento completo da página e dos dados
document.addEventListener('DOMContentLoaded', async () => {
    // Aguarda um pouco para garantir que o mocker carregou os dados
    await new Promise(resolve => setTimeout(resolve, 100));
    
    exibirEventos();
});

/**
 * Exibe todos os eventos na tela
 */
function exibirEventos() {
    const container = document.getElementById('eventos-container');
    const eventos = eventosMocker.obterTodosEventos();
    
    if (!container) {
        console.error('Container de eventos não encontrado');
        return;
    }
    
    if (eventos.length === 0) {
        container.innerHTML = '<p style="text-align: center; color: var(--cinza-bg);">Nenhum evento encontrado.</p>';
        return;
    }
    
    container.innerHTML = eventos.map(evento => criarCardEvento(evento)).join('');
}

/**
 * Cria o HTML de um card de evento
 * @param {Object} evento - Objeto do evento
 * @returns {string} HTML do card
 */
function criarCardEvento(evento) {
    const dataFormatada = formatarData(evento.data);
    const organizadorNome = evento.organizador?.nome || 'Organizador não informado';
    
    return `
        <div class="evento-card">
            <h2>${escapeHtml(evento.titulo)}</h2>
            <div class="evento-data">📅 ${dataFormatada}</div>
            <div class="evento-local">📍 ${escapeHtml(evento.local)}</div>
            <div class="evento-descricao">${escapeHtml(evento.descricao)}</div>
            <div class="evento-organizador">Organizador: ${escapeHtml(organizadorNome)}</div>
        </div>
    `;
}

/**
 * Formata a data no formato brasileiro
 * @param {string} dataISO - Data no formato ISO (YYYY-MM-DD)
 * @returns {string} Data formatada (DD/MM/YYYY)
 */
function formatarData(dataISO) {
    if (!dataISO) return 'Data não informada';
    
    const data = new Date(dataISO + 'T00:00:00');
    const dia = String(data.getDate()).padStart(2, '0');
    const mes = String(data.getMonth() + 1).padStart(2, '0');
    const ano = data.getFullYear();
    
    return `${dia}/${mes}/${ano}`;
}

/**
 * Escapa caracteres HTML para prevenir XSS
 * @param {string} texto - Texto a ser escapado
 * @returns {string} Texto escapado
 */
function escapeHtml(texto) {
    if (!texto) return '';
    
    const div = document.createElement('div');
    div.textContent = texto;
    return div.innerHTML;
}

// Exporta funções para uso externo se necessário
window.listaEventos = {
    exibirEventos,
    formatarData,
    criarCardEvento
};
