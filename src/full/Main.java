package full;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        // Cria o gerenciador de eventos
        GerenciadorDeEventos gerenciador = new GerenciadorDeEventos();


        // Criar alguns eventos
        Eventos evento1 = new Eventos("Arena Vitória", "Imagine Dragons", "20:00", "2025-12-01", 50, 100);
        Eventos evento2 = new Eventos("Teatro Municipal", "Coldplay", "21:30", "2025-12-05", 30, 70);

        // Adiciona eventos ao gerenciador
        gerenciador.adicionarEvento(evento1);
        gerenciador.adicionarEvento(evento2);


        // Criar alguns clientes
        Cliente cliente1 = Cliente.cadastrarCliente("Luiz", 19, "luiz@gmail.com", "(27)99776-2457", "123.456.789-00");
        Cliente cliente2 = Cliente.cadastrarCliente("Ana", 22, "ana@gmail.com", "(27)98888-1234", "987.654.321-00");

        // Adiciona clientes ao gerenciador
        gerenciador.adicionarCliente(cliente1);
        gerenciador.adicionarCliente(cliente2);


        // Criar inscrições
        Inscricao inscricao1 = new Inscricao(cliente1.getNome(), cliente1.getEmail(), "Pista", 1);
        Inscricao inscricao2 = new Inscricao(cliente2.getNome(), cliente2.getEmail(), "Camarote", 2);

        // Adiciona inscrições ao gerenciador
        gerenciador.adicionarInscricao(inscricao1);
        gerenciador.adicionarInscricao(inscricao2);


        // Listar eventos
        System.out.println("=== Eventos Cadastrados ===");
        for (Eventos e : gerenciador.listarEventos()) {
            System.out.println("Artista: " + e.getArtista() + " | Local: " + e.getLocal());
        }


        // Buscar evento por artista
        String buscaArtista = "Coldplay";
        Eventos encontrado = gerenciador.buscarEventoPorArtista(buscaArtista);
        if (encontrado != null) {
            System.out.println("\nEvento encontrado para " + buscaArtista + ": " + encontrado.getLocal() + " às " + encontrado.getHorario());
        } else {
            System.out.println("\nEvento para " + buscaArtista + " não encontrado.");
        }


        // Listar clientes
        System.out.println("\n=== Clientes Cadastrados ===");
        for (Cliente c : gerenciador.listarClientes()) {
            System.out.println("Nome: " + c.getNome() + " | Email: " + c.getEmail());
        }


        // Listar inscrições
        System.out.println("\n=== Inscrições Cadastradas ===");
        for (Inscricao i : gerenciador.listarInscricoes()) {
            System.out.println("Participante: " + i.getNomeParticipante() + " | Tipo Ingresso: " + i.getTipoIngresso());
        }
    }
}
