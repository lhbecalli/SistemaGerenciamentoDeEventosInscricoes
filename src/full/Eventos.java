package full;

public class Eventos {
    private String local;
    private String artista;
    private String horario;
    private String dia;
    private int capacidadeTotalCamarote;
    private int capacidadeTotalPista;

    //Construtor Vazio
    public Eventos(){

    }

    // Construtor Completo
    public Eventos(String local, String artista, String horario, String dia,
                   int capacidadeTotalCamarote, int capacidadeTotalPista) {
        this.local = local;
        this.artista = artista;
        this.horario = horario;
        this.dia = dia;
        this.capacidadeTotalCamarote = capacidadeTotalCamarote;
        this.capacidadeTotalPista = capacidadeTotalPista;
    }


  // Getters e Setters
    // Retorna o local do evento
    public String getLocal() {
        return this.local;
    }
    // Define o local do evento
    public void setLocal(String local) {
        this.local = local;
    }


    // Retorna o artista
    public String getArtista() {
        return this.artista;
    }
    // Define o artista
    public void setArtista(String artista) {
        this.artista = artista;
    }


    // Retorna o horário
    public String getHorario() {
        return this.horario;
    }
    // Define o horário
    public void setHorario(String horario) {
        this.horario = horario;
    }


    // Retorna o dia
    public String getDia() {
        return this.dia;
    }
    // Define o dia
    public void setDia(String dia) {
        this.dia = dia;
    }


    // Retorna a capacidade total do camarote
    public int getCapacidadeTotalCamarote() {
        return this.capacidadeTotalCamarote;
    }
    // Define a capacidade do camarote
    public void setCapacidadeTotalCamarote(int capacidadeTotalCamarote) {
        this.capacidadeTotalCamarote = capacidadeTotalCamarote;
    }


    // Retorna a capacidade total da pista
    public int getCapacidadeTotalPista() {
        return this.capacidadeTotalPista;
    }
    // Define a capacidade da pista
    public void setCapacidadeTotalPista(int capacidadeTotalPista) {
        this.capacidadeTotalPista = capacidadeTotalPista;
    }


    //Função de cadastrar o Evento
    public void cadastrarEvento(
            String local,
            String artista,
            String horario,
            String dia,
            int capacidadeTotalPista,
            int capacidadeTotalCamarote
    ) {
        this.local = local;
        this.artista = artista;
        this.horario = horario;
        this.dia = dia;
        this.capacidadeTotalPista = capacidadeTotalPista;
        this.capacidadeTotalCamarote = capacidadeTotalCamarote;
    }
}


