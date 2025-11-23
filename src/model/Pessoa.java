package model;

import java.io.Serializable;

/**
 * Classe Pessoa
 * * É uma Superclasse (Mãe) que representa uma pessoa genérica.
 * Implementa Serializable para que seus dados possam ser gravados em arquivo.
 */
public class Pessoa implements Serializable {

    // Identificador de versão para a serialização
    private static final long serialVersionUID = 1L;

    // Atributos protected permitem que as classes filhas (como Cliente)
    // acessem essas variáveis diretamente se necessário.
    protected String nome;
    protected int idade;
    protected String email;

    // Construtor vazio (Necessário para algumas bibliotecas e boas práticas)
    public Pessoa() { }

    // Construtor completo para inicializar os dados
    public Pessoa(String nome, int idade, String email) {
        this.nome = nome;
        this.idade = idade;
        this.email = email;
    }

    // --- Getters e Setters (Encapsulamento) ---

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    // ---  toString ---
    // Transforma o objeto em uma String legível para impressão
    @Override
    public String toString() {
        return "Nome: " + nome + " | Email: " + email;
    }

    // ---  equals  ---
    // Compara se duas Pessoas são iguais baseando-se no EMAIL.
    @Override
    public boolean equals(Object obj) {
        // Se for o mesmo objeto na memória, retorna true
        if (this == obj) return true;

        // Se o objeto for nulo ou de outra classe, retorna false
        if (obj == null || getClass() != obj.getClass()) return false;

        // Converte o objeto genérico para Pessoa
        Pessoa outraPessoa = (Pessoa) obj;

        // Compara os emails (tratando caso o email seja nulo)
        if (this.email == null) {
            return outraPessoa.email == null;
        } else {
            // Retorna true se os emails forem idênticos
            return this.email.equals(outraPessoa.email);
        }
    }
}