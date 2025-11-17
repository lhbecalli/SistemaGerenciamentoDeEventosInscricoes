package full;


/**Classe Eventos
 *
 * Representa um evento com informações como local, artista, horário, dia e capacidade de público
 * Contém métodos para acessar e modificar seus atributos, além de um metodo estático para criar novos eventos
 */

public class Eventos {
    private String local;
    private String artista;
    private String horario;
    private String dia;
    private int capacidadeTotalCamarote;
    private int capacidadeTotalPista;

    // Construtor vazio
    public Eventos() { }

    // Construtor completo
    public Eventos(String local, String artista, String horario, String dia,
                   int capacidadeTotalCamarote, int capacidadeTotalPista) {
        this.local = local;
        this.artista = artista;
        this.horario = horario;
        this.dia = dia;
        this.capacidadeTotalCamarote = capacidadeTotalCamarote;
        this.capacidadeTotalPista = capacidadeTotalPista;
    }

    // Define o local
    public void setLocal(String local) {
        this.local = local;
    }
    // Retorna o local
    public String getLocal() {
        return local;
    }

    // Define o artista
    public void setArtista(String artista) {
        this.artista = artista;
    }
    // Retorna o artista
    public String getArtista() {
        return artista;
    }

    // Define o horário
    public void setHorario(String horario) {
        this.horario = horario;
    }
    // Retorna o horário
    public String getHorario() {
        return horario;
    }

    // Define o dia
    public void setDia(String dia) {
        this.dia = dia;
    }
    // Retorna o dia
    public String getDia() {
        return dia;
    }

    // Define a capacidade do camarote
    public void setCapacidadeTotalCamarote(int capacidadeTotalCamarote) {
        this.capacidadeTotalCamarote = capacidadeTotalCamarote;
    }
    // Retorna a capacidade do camarote
    public int getCapacidadeTotalCamarote() {
        return capacidadeTotalCamarote;
    }

    // Define a capacidade da pista
    public void setCapacidadeTotalPista(int capacidadeTotalPista) {
        this.capacidadeTotalPista = capacidadeTotalPista;
    }
    // Retorna a capacidade da pista
    public int getCapacidadeTotalPista() {
        return capacidadeTotalPista;
    }

    // Metodo para criar um novo evento
    public static Eventos cadastrarEvento(String local, String artista, String horario, String dia,
    int capacidadeTotalCamarote, int capacidadeTotalPista) {
        return new Eventos(local, artista, horario, dia, capacidadeTotalCamarote, capacidadeTotalPista);
    }
}
