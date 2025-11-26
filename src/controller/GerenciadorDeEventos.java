package controller;

import model.Cliente;
import model.Eventos;
import model.Inscricao;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe GerenciadorDeEventos
 * Responsável por gerenciar os eventos, clientes e inscrições do sistema
 * * Funciona como o "Cérebro" ou "Banco de Dados em Memória" do nosso sistema
 * Implementa Serializable para permitir salvar o estado completo em arquivo
 */

public class GerenciadorDeEventos implements Serializable {

    private static final long serialVersionUID = 1L;

    // Listas que armazenam os objetos na memória RAM
    private ArrayList<Eventos> listaEventos;
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Inscricao> listaInscricoes;

    // Construtor
    // Inicializa as listas vazias para evitar erros de NullPointerException
    public GerenciadorDeEventos() {
        listaEventos = new ArrayList<>();
        listaClientes = new ArrayList<>();
        listaInscricoes = new ArrayList<>();
    }

    // --- MÉTODOS DE EVENTOS ---

    public void adicionarEvento(Eventos evento) {
        listaEventos.add(evento);
    }

    public ArrayList<Eventos> listarEventos() {
        return listaEventos;
    }

    // Busca por nome/artista
    // Percorre a lista comparando o nome do artista (ignorando maiúsculas/minúsculas)
    public Eventos buscarEventoPorArtista(String artista) {

        for (Eventos e : listaEventos) {
            if (e.getArtista().equalsIgnoreCase(artista)) {
                return e;
            }
        }

        return null; // Retorna null se não encontrar
    }

    // Busca por ID (Essencial para vincular a Inscrição ao Evento correto)
    public Eventos buscarEventoPorId(int id) {

        for (Eventos e : listaEventos) {
            if (e.getId() == id) {
                return e;
            }

        }
        return null;
    }

    // --- MÉTODOS DE CLIENTES ---

    public void adicionarCliente(Cliente cliente) {
        listaClientes.add(cliente);
    }

    public ArrayList<Cliente> listarClientes() {
        return listaClientes;
    }

    // Busca Cliente por CPF (Usado para validar se o cliente já existe antes de cadastrar)
    public Cliente buscarClientePorCpf(String cpf) {

        for (Cliente c : listaClientes) {
            if (c.getCpf().equals(cpf)) {
                return c;
            }
        }

        return null;
    }

    // --- MÉTODOS DE INSCRIÇÕES ---

    public void adicionarInscricao(Inscricao inscricao) {
        listaInscricoes.add(inscricao);
    }

    public ArrayList<Inscricao> listarInscricoes() {
        return listaInscricoes;
    }


    // --- METODO do Controle de Estoque (Regra de Negócio) ---

    //Percorre a lista de inscrições e conta quantos ingressos já foram vendidos
    // para um determinado evento e um determinado setor (Pista ou Camarote)
    // Serve para validar a lotação antes de vender um novo ingresso
    public int contarIngressosVendidos(int idEvento, String tipoIngresso) {
        int quantidade = 0;

        for (Inscricao i : listaInscricoes) {
            // Verifica se a inscrição pertence ao evento solicitado (pelo ID)
            // E se é do mesmo tipo de ingresso (Pista ou Camarote)
            if (i.getIdEvento() == idEvento && i.getTipoIngresso().equalsIgnoreCase(tipoIngresso)) {
                quantidade++; // Aciona o contador
            }
        }

        return quantidade; // Retorna o total vendido até agora
    }

}