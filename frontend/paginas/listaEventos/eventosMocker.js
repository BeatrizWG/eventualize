class EventosMocker {
    constructor() {
        this.eventos = [];
        this.carregarEventos();
    }


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

  
    obterTodosEventos() {
        return this.eventos;
    }

   
    obterEventoPorId(idEvento) {
        return this.eventos.find(evento => evento.idEvento === idEvento) || null;
    }

    
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

    
    atualizarEvento(idEvento, dadosAtualizados) {
        const index = this.eventos.findIndex(e => e.idEvento === idEvento);
        
        if (index === -1) {
            return null;
        }
        
        this.eventos[index] = {
            ...this.eventos[index],
            ...dadosAtualizados,
            idEvento: idEvento 
        
        return this.eventos[index];
    }

    
    removerEvento(idEvento) {
        const index = this.eventos.findIndex(e => e.idEvento === idEvento);
        
        if (index === -1) {
            return false;
        }
        
        this.eventos.splice(index, 1);
        return true;
    }

   
    buscarEventosPorTitulo(termo) {
        const termoLower = termo.toLowerCase();
        return this.eventos.filter(evento => 
            evento.titulo.toLowerCase().includes(termoLower)
        );
    }

    
    buscarEventosPorData(data) {
        return this.eventos.filter(evento => evento.data === data);
    }

    
    buscarEventosPorLocal(local) {
        const localLower = local.toLowerCase();
        return this.eventos.filter(evento => 
            evento.local.toLowerCase().includes(localLower)
        );
    }

    
    obterEventosOrdenadosPorData(crescente = true) {
        return [...this.eventos].sort((a, b) => {
            const dataA = new Date(a.data);
            const dataB = new Date(b.data);
            return crescente ? dataA - dataB : dataB - dataA;
        });
    }

    
    obterQuantidadeEventos() {
        return this.eventos.length;
    }

    
    limparEventos() {
        this.eventos = [];
    }

    
    async resetarEventos() {
        await this.carregarEventos();
    }
}

const eventosMocker = new EventosMocker();

window.addEventListener('DOMContentLoaded', async () => {
    await eventosMocker.carregarEventos();
});
