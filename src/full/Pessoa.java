package full;

/**
 * Classe Pessoa
 *
 * É uma Superclasse que representa uma pessoa genérica no sistema
 * Contém os atributos básicos nome, idade e email, além de seus métodos getters e setters
 * Serve como classe base para outras classes que herdam suas propriedades, como Cliente
 */

public class Pessoa {
    protected String nome;
    protected int idade;
    protected String email;

    public Pessoa() { }

    public Pessoa(String nome, int idade, String email) {
        this.nome = nome;
        this.idade = idade;
        this.email = email;
    }

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
}
