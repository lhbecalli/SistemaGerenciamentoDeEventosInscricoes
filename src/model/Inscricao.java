package model;

import java.io.Serializable;

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

    // --- Getters e Setters ---

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

    // --- toString ---
    // Sobrescreve o metodo padrão para mostrar os dados da inscrição de forma legível no console

    @Override
    public String toString(){
        return "=== Inscrição ===\n" +
                "Participante: " + nomeParticipante + " (" + email + ")\n" +
                "Evento ID: " + idEvento + " | Tipo: " + tipoIngresso + "\n";
    }

    // --- equals ---
    // Lógica de Negócio: Uma inscrição é igual a outra se for a mesma pessoa (email)
    // tentando se inscrever no mesmo evento (idEvento).
    @Override
    public boolean equals(Object obj){
        //  Verifica memória
        if(this == obj) return true;
        //  Verifica nulo ou classe diferente
        if (obj == null || getClass() != obj.getClass()) return false;

        // Conversão (Casting)
        Inscricao outra = (Inscricao) obj;

        // Comparação Composta:
        // Verifica se o ID do evento é igual
        boolean mesmoEvento = (this.idEvento == outra.idEvento);
        // Verifica se o email é igual (tratando null para evitar erros)
        boolean mesmaPessoa = (this.email != null && this.email.equals(outra.email));

        // Só retorna true se ambos forem verdadeiros (Mesma pessoa no mesmo evento)
        return mesmoEvento && mesmaPessoa;
    }
}