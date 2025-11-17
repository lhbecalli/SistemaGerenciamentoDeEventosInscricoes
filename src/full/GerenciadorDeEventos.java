package full;

import java.util.ArrayList;


/**
 * Classe GerenciadorDeEventos

 * Responsável por gerenciar os eventos, clientes e inscrições do sistema
 * Utiliza listas dinâmicas (ArrayList) para armazenar os objetos
 * Contém métodos para adicionar, listar e buscar eventos, clientes e inscrições
 */

public class GerenciadorDeEventos {

    // Utilizamos ArrayList pq tem tamanho dinâmico, ja no caso de uso de vetores teria que passar o tamanho e nao daria certo

    // Listas que armazenam os objetos
    private ArrayList<Eventos> listaEventos;
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Inscricao> listaInscricoes;

    // Construtor
    public GerenciadorDeEventos() {
        listaEventos = new ArrayList<>();
        listaClientes = new ArrayList<>();
        listaInscricoes = new ArrayList<>();
    }

    // Adicionar evento
    public void adicionarEvento(Eventos evento) {
        listaEventos.add(evento);
    }
    // Listar eventos
    public ArrayList<Eventos> listarEventos() {
        return listaEventos;
    }

    // Adicionar cliente
    public void adicionarCliente(Cliente cliente) {
        listaClientes.add(cliente);
    }
    // Listar clientes
    public ArrayList<Cliente> listarClientes() {
        return listaClientes;
    }

    // Adicionar inscrição
    public void adicionarInscricao(Inscricao inscricao) {
        listaInscricoes.add(inscricao);
    }
    // Listar inscrições
    public ArrayList<Inscricao> listarInscricoes() {
        return listaInscricoes;
    }

    // Exemplo: buscar evento por artista
    public Eventos buscarEventoPorArtista(String artista) {

        // No for vai ser a lista de todos os eventos cadastrados
        for (Eventos e : listaEventos) {
            // No EqualsIgnoreCase vai ignorar as letras minusculas/maiusculas na hora da busca
            // O artista seria o argumento da função 'equal' em que vai comparar.
            // E.GetArtista =  Vai pegar o nome do artista procurado da lista de eventos
            if (e.getArtista().equalsIgnoreCase(artista)) {
                // Retorna o Evento inteiro.
                return e;
            }
        }
        return null; // não encontrado
    }
}
