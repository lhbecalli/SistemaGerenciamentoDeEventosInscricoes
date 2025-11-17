package full;

public class Cliente {
    private String nome;      // Nome do cliente
    private int idade;        // Idade do cliente
    private String email;     // Email do cliente
    private String telefone;  // Telefone de contato
    private String cpf;       // CPF do cliente

    // Construtor vazio
    public Cliente() {
    }

    // Construtor completo
    public Cliente(String nome, String email, String telefone, String cpf, int idade){
        this.nome = nome;
        this.idade = idade;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
    }

    // Define o nome do cliente
    public void setNome(String nome){
        this.nome = nome;
    }
    // Retorna o nome do cliente
    public String getNome(){
        return this.nome;
    }

    // Define a idade do cliente
    public void setIdade(int idade){
        this.idade = idade;
    }
    // Retorna a idade do cliente
    public int getIdade(){
        return this.idade;
    }

    // Define o email do cliente
    public void setEmail(String email){
        this.email = email;
    }
    // Retorna o email do cliente
    public String getEmail(){
        return this.email;
    }

    // Define o telefone do cliente
    public void setTelefone(String telefone){
        this.telefone = telefone;
    }
    // Retorna o telefone do cliente
    public String getTelefone(){
        return this.telefone;
    }

    // Define o CPF do cliente
    public void setCpf(String cpf){
        this.cpf = cpf;
    }
    // Retorna o CPF do cliente
    public String getCpf(){
        return this.cpf;
    }


    // Método que registra um novo cliente
    public static Cliente cadastrarCliente(String nome, int idade, String email, String telefone, String cpf) {
        return new Cliente(nome, email, telefone, cpf, idade);
    }
}
