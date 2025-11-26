package model;

import java.io.Serializable;

/**
 * Classe Cliente
 * representa um cliente do sistema
 * * Conceitos Aplicados:
 * - Herança (extends Pessoa): Reutiliza nome, idade e email.
 * - Serialização (implements Serializable): Permite salvar o objeto no HD.
 */

public class Cliente extends Pessoa implements Serializable {

    // identificador de versão da classe, importante para garantir que o objeto salvo
    // seja compatível com a classe quando for lido do arquivo novamente
    private static final long serialVersionUID = 1L;

    // atributos específicos do Cliente (que a Pessoa genérica não tem)
    private String telefone;
    private String cpf;

    // Construtor vazio
    public Cliente() { }

    // Construtor completo
    public Cliente(String nome, int idade, String email, String telefone, String cpf) {
        // Aqui estamos chamando o construtor da classe Pessoa para preencher nome, idade e email.
        // isso evita repeticao de código

        super(nome, idade, email);
        // 'super' refere-se à superclasse (Pessoa).

        // Inicializa os atributos específicos desta classe
        this.telefone = telefone;
        this.cpf = cpf;
    }

    // --- Getters e Setters  ---

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }


    // Este metodo Facilita a criação de um Cliente sem precisar chamar 'new Cliente(...)'
    // diretamente na Main.
    public static Cliente cadastrarCliente(String nome, int idade, String email, String telefone, String cpf) {
        return new Cliente(nome, idade, email, telefone, cpf);
    }

    // ---  toString  ---
    // Formata como o Cliente é impresso no console
    @Override
    public String toString() {
        return "=== Cliente ===\n" +
                // super.toString() chama o toString da classe Pessoa.
                // ai imprimimos Nome e Email (da Pessoa) + CPF e Telefone (do Cliente).
                super.toString() + "\n" +
                "CPF: " + cpf + " | Tel: " + telefone + "\n";
    }

    // --- equals (Sobrescrita) ---
    // Define a regra de igualdade: Um cliente é igual ao outro se tiverem o mesmo CPF.
    @Override
    public boolean equals(Object obj) {
        //  Verifica se é o mesmo objeto na memória
        if (this == obj) return true;

        //  Verifica se é nulo ou se é de uma classe diferente (segurança)
        if (obj == null || getClass() != obj.getClass()) return false;

        //  faz o Casting ( conversão ) do objeto genérico para Cliente para acessar o CPF
        Cliente outro = (Cliente) obj;

        // Compara as Strings de CPF.
        return this.cpf != null && this.cpf.equals(outro.cpf);
        // Se this.cpf for diferente de null e igual ao cpf do outro, retorna true.

    }
}