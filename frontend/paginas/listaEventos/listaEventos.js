

document.addEventListener('DOMContentLoaded', async () => {
    await new Promise(resolve => setTimeout(resolve, 100));

    const inputBusca = document.getElementById('busca-eventos');
    if (inputBusca) {
        inputBusca.addEventListener('input', () => {
            const termo = inputBusca.value;
            const filtrados = buscarEventos(termo);
            const buscaAtiva = termo.trim().length > 0;
            renderizarListaEventos(filtrados, buscaAtiva);
        });
    }

    exibirEventos();
});

function buscarEventos(termo) {
    const todos = eventosMocker.obterTodosEventos();
    const t = (termo || '').trim().toLowerCase();
    if (!t) {
        return todos;
    }
    return todos.filter((evento) => {
        const titulo = (evento.titulo || '').toLowerCase();
        const local = (evento.local || '').toLowerCase();
        const descricao = (evento.descricao || '').toLowerCase();
        const organizador = (evento.organizador?.nome || '').toLowerCase();
        return (
            titulo.includes(t) ||
            local.includes(t) ||
            descricao.includes(t) ||
            organizador.includes(t)
        );
    });
}

function renderizarListaEventos(eventos, buscaAtiva) {
    const container = document.getElementById('eventos-container');
    if (!container) {
        console.error('Container de eventos não encontrado');
        return;
    }

    if (eventos.length === 0) {
        const msg = buscaAtiva
            ? 'Nenhum evento encontrado para essa busca.'
            : 'Nenhum evento encontrado.';
        container.innerHTML = `<p style="text-align: center; color: var(--cinza-bg);">${msg}</p>`;
        return;
    }

    container.innerHTML = eventos.map((evento) => criarCardEvento(evento)).join('');
}

function exibirEventos() {
    renderizarListaEventos(eventosMocker.obterTodosEventos(), false);
}

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

function formatarData(dataISO) {
    if (!dataISO) return 'Data não informada';
    
    const data = new Date(dataISO + 'T00:00:00');
    const dia = String(data.getDate()).padStart(2, '0');
    const mes = String(data.getMonth() + 1).padStart(2, '0');
    const ano = data.getFullYear();
    
    return `${dia}/${mes}/${ano}`;
}


function escapeHtml(texto) {
    if (!texto) return '';
    
    const div = document.createElement('div');
    div.textContent = texto;
    return div.innerHTML;
}

window.listaEventos = {
    exibirEventos,
    buscarEventos,
    formatarData,
    criarCardEvento
};
