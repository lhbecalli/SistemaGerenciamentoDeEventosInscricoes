package controller;

import model.Cliente;
import model.Eventos;
import model.Inscricao;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe GerenciadorDeEventos
 * Responsável por gerenciar os eventos, clientes e inscrições do sistema.
 */


public class GerenciadorDeEventos implements Serializable {

    private static final long serialVersionUID = 1L;

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

    // --- Metodo de eventos ---

    public void adicionarEvento(Eventos evento) {
        listaEventos.add(evento);
    }

    public ArrayList<Eventos> listarEventos() {
        return listaEventos;
    }

    // Busca por nome/artista
    public Eventos buscarEventoPorArtista(String artista) {
        for (Eventos e : listaEventos) {
            // Ignora maiúsculas/minúsculas
            if (e.getArtista().equalsIgnoreCase(artista)) {
                return e;
            }
        }
        return null; // não encontrado
    }

    // Necessário para a Inscrição funcionar corretamente
    public Eventos buscarEventoPorId(int id) {
        for (Eventos e : listaEventos) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    // --- Metodo de clientes ---

    public void adicionarCliente(Cliente cliente) {
        listaClientes.add(cliente);
    }

    public ArrayList<Cliente> listarClientes() {
        return listaClientes;
    }

    // Aqui vai ser necessário para validar se o cliente já existe antes de cadastrar
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
}