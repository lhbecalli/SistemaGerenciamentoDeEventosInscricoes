package model;

import java.io.Serializable;
/**Classe Eventos
 *
 * Representa um evento com informações como local, artista, horário, dia e capacidade de público
 * Contém métodos para acessar e modificar seus atributos, além de um metodo estático para criar novos eventos
 */

public class Eventos implements Serializable {

    private static final long serialVersionUID = 1L;

    // Atributos para controle do ID automático
    private static int contador = 1;
    private int id;

    private String local;
    private String artista;
    private String horario;
    private String dia;
    private int capacidadeTotalCamarote;
    private int capacidadeTotalPista;

    // Construtor vazio
    public Eventos() {
    }

    // Construtor completo
    public Eventos(String local, String artista, String horario, String dia,
                   int capacidadeTotalCamarote, int capacidadeTotalPista) {

        this.id = contador++; /* Aqui é onde vai gerar o ID único (1, 2, 3...) */
        this.local = local;
        this.artista = artista;
        this.horario = horario;
        this.dia = dia;
        this.capacidadeTotalCamarote = capacidadeTotalCamarote;
        this.capacidadeTotalPista = capacidadeTotalPista;
    }

    // --- Métodos Getters e Setters ---
    public int getId() {
        return id;
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


    // O @Override avisa o compilador que estamos substituindo o comportamento padrão do Java
    @Override
    public String toString() {
        // Retorna uma String formatada.
        // Sem isso, se dermos um System.out.println(evento), sairia algo feio como "model.Eventos@2f92e0f4".
        // Com isso, saem os dados legíveis.
        return "=== Evento Nº " + id + " ===\n" +
                "Artista: " + artista + "\n" +
                "Local: " + local + " | Dia: " + dia + " | Horário: " + horario + "\n" +
                "Capacidade Pista: " + capacidadeTotalPista + " | Camarote: " + capacidadeTotalCamarote + "\n";
    }


    // Metodo equals
    // O metodo equals vai servir para comparar se dois objetos são "a mesma coisa".
    @Override
    public boolean equals(Object obj) {
        // Verificação de Memória: Se "este" (this) objeto for exatamente o mesmo endereço de memória que o recebido (obj)
        if (this == obj) return true;

        //  Verificação de Nulo e Tipo:
        // Se o objeto comparado for nulo OU se eles forem de classes diferentes (ex: comparar Evento com Cliente)
        // Então retorna falso na hora
        if (obj == null || getClass() != obj.getClass()) return false;

        // Casting (Conversão):
        // Como o parâmetro vem como genérico 'Object', precisamos avisar o Java: "Trate esse obj como Eventos"
        // para podermos acessar o atributo '.id' dele.
        Eventos outro = (Eventos) obj;

        //  Comparação Real:
        // Definimos que dois eventos são iguais se o ID deles for igual.
        // Ex: Se eu criar dois objetos na memória diferentes, mas ambos tiverem ID 5, para o sistema eles são o mesmo evento.
        return this.id == outro.id;
    }
}
