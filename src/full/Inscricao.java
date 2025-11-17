package full;

public class Inscricao {
    private String nomeParticipante; // Nome do participante inscrito
    private String email;            // Email do participante
    private String tipoIngresso;     // Tipo do ingresso escolhido: "Pista" ou "Camarote"
    private int idEvento;            // ID do evento onde a pessoa está inscrita

    // Construtor Vazio
    public Inscricao() {
    }

    // Construtor Completo
    public Inscricao(String nomeParticipante, String email, String tipoIngresso, int idEvento) {
        this.nomeParticipante = nomeParticipante;
        this.email = email;
        this.tipoIngresso = tipoIngresso;
        this.idEvento = idEvento;
    }


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


    // Define o ID do evento
    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }
    // Retorna o ID do evento
    public int getIdEvento() {
        return this.idEvento;
    }
}
