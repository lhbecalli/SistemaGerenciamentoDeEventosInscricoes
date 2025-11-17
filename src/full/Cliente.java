package full;

/**
 * Classe Cliente

 * Representa um cliente do sistema, herdando atributos básicos de Pessoa (nome, idade e email)
 * e adicionando informações específicas como telefone e CPF

 * Contém métodos para acessar e modificar seus atributos, além de um metodo estático para criar novos clientes
 */




// Herda atributos e métodos da classe Pessoa
public class Cliente extends Pessoa {
    private String telefone;
    private String cpf;

    // Construtor vazio
    public Cliente() { }

    public Cliente(String nome, int idade, String email, String telefone, String cpf) {
        super(nome, idade, email); // inicializa atributos da superclasse Pessoa
        this.telefone = telefone;
        this.cpf = cpf;
    }

    // Define o telefone
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    // Retorna o telefone
    public String getTelefone() {
        return telefone;
    }

    // Define o CPF
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    // Retorna o CPF
    public String getCpf() {
        return cpf;
    }

    // Metodo para criar cliente
    public static Cliente cadastrarCliente(String nome, int idade, String email, String telefone, String cpf) {
        return new Cliente(nome, idade, email, telefone, cpf);
    }
}
