package model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID; // Import necessário para gerar códigos aleatórios

/**
 * Classe Inscricao
 *
 * Representa a efetivação da participação de um Cliente em um evento
 * Funciona como uma tabela de ligação no banco de dados, unindo uma Pessoa (via email)
 * a um Evento (via ID).
 *
 * Implementa Serializable para permitir salvar os dados em arquivo.
 */
public class Inscricao implements Serializable {

    // Controle de versão para a serialização
    private static final long serialVersionUID = 1L;

    private String nomeParticipante; // Nome do participante
    private String email;            // Email do participante (Chave lógica para identificar a pessoa)
    private String tipoIngresso;     // "Pista" ou "Camarote"
    private int idEvento;            // ID do evento (Chave estrangeira para vincular ao evento correto)
    
    // NOVO ATRIBUTO: Código único do ingresso (Ex: "A1B2-C3D4")
    // Permite que a mesma pessoa compre mais de um ingresso
    private String codigoIngresso; 

    // Construtor Vazio
    public Inscricao() { }

    // Construtor Completo
    public Inscricao(String nomeParticipante, String email, String tipoIngresso, int idEvento) {
        this.nomeParticipante = nomeParticipante;
        this.email = email;
        this.tipoIngresso = tipoIngresso;
        this.idEvento = idEvento;
        
        // GERAÇÃO AUTOMÁTICA DO CÓDIGO ÚNICO
        // Pega um identificador aleatório (UUID) e corta os primeiros 6 caracteres em maiúsculo
        // Isso garante que cada objeto Inscrição seja único, mesmo se for a mesma pessoa no mesmo evento.
        this.codigoIngresso = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }

    // --- Getters e Setters ---

    // Retorna o código único gerado
    public String getCodigoIngresso() {
        return codigoIngresso;
    }
    // Não criamos setCodigoIngresso pois ele é gerado automaticamente e não deve mudar

    // Define o nome do participante
    public void setNomeParticipante(String nomeParticipante) {
        this.nomeParticipante = nomeParticipante;
    }
    // Retorna o nome do participante
    public String getNomeParticipante() {
        return this.nomeParticipante;
    }

    // Define o email do participante
    public void setEmail(String email) {
        this.email = email;
    }
    // Retorna o email do participante
    public String getEmail() {
        return this.email;
    }

    // Define o tipo de ingresso
    public void setTipoIngresso(String tipoIngresso) {
        this.tipoIngresso = tipoIngresso;
    }
    // Retorna o tipo de ingresso
    public String getTipoIngresso() {
        return this.tipoIngresso;
    }

    // Define o ID do evento vinculado
    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }
    // Retorna o ID do evento vinculado
    public int getIdEvento() {
        return this.idEvento;
    }

    // --- toString Atualizado ---
    // Sobrescreve o metodo padrão para mostrar os dados da inscrição de forma legível no console
    // Agora inclui o Código do Ingresso
    @Override
    public String toString(){
        return "=== Inscrição (Cód: " + codigoIngresso + ") ===\n" +
                "Participante: " + nomeParticipante + " (" + email + ")\n" +
                "Evento ID: " + idEvento + " | Tipo: " + tipoIngresso + "\n";
    }

    // --- EQUALS ATUALIZADO ---
    // Lógica de Negócio Revisada: Agora dois ingressos só são considerados iguais se tiverem 
    // EXATAMENTE O MESMO CÓDIGO ÚNICO.
    // Isso permite que a mesma pessoa (mesmo email) compre vários ingressos diferentes para o mesmo evento.
    @Override
    public boolean equals(Object obj) {
        // 1. Verifica memória
        if (this == obj) return true;
        // 2. Verifica nulo ou classe diferente
        if (obj == null || getClass() != obj.getClass()) return false;
        
        // 3. Conversão (Casting)
        Inscricao outra = (Inscricao) obj;
        
        // 4. Comparação: Verifica se os códigos únicos batem
        return Objects.equals(this.codigoIngresso, outra.codigoIngresso);
    }

    // Boa prática: implementar hashCode junto com equals
    @Override
    public int hashCode() {
        return Objects.hash(codigoIngresso);
    }
}