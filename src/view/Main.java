package view;

import controller.GerenciadorDeEventos;
import model.Cliente;
import model.Eventos;
import model.Inscricao;
import utils.PersistenciaArquivo;

import java.util.Scanner;

/**
 * Classe Main (Interface com o Usuário)
 * Responsável por exibir o menu e capturar os dados do teclado com validações
 */
public class Main {

    // Scanner global para ler o teclado
    private static Scanner scanner = new Scanner(System.in);

    // Ferramentas de controle e persistência
    private static GerenciadorDeEventos gerenciador;
    private static PersistenciaArquivo persistencia;

    public static void main(String[] args) {
        // Inicializa a persistência
        persistencia = new PersistenciaArquivo();

        // Carrega os dados salvos anteriormente
        System.out.println("Iniciando sistema...");
        gerenciador = persistencia.carregarDados();

        int opcao = -1;

        // Loop do Menu
        do {
            exibirMenu();
            // Lê a opção tentando evitar erros de digitação
            opcao = lerInteiro("Digite sua opção: ");

            // Consumir a quebra de linha pendente do scanner
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarEvento();
                    break;
                case 2:
                    cadastrarCliente();
                    break;
                case 3:
                    realizarInscricao();
                    break;
                case 4:
                    listarEventos();
                    break;
                case 5:
                    listarClientes();
                    break;
                case 6:
                    listarInscricoes();
                    break;
                case 0:
                    System.out.println("Salvando dados e saindo...");
                    persistencia.salvarDados(gerenciador);
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

            System.out.println("\nPressione ENTER para continuar...");
            scanner.nextLine();
        } while (opcao != 0);

        scanner.close();
    }

    // --- Métodos do Sistema ---

    private static void exibirMenu() {
        System.out.println("\n=== SISTEMA DE GESTÃO DE EVENTOS ===");
        System.out.println("1. Cadastrar Evento");
        System.out.println("2. Cadastrar Cliente no sistema");
        System.out.println("3. Cadastrar Venda (Comprar Ingresso)");
        System.out.println("--------------------------------");
        System.out.println("4. Listar Eventos");
        System.out.println("5. Listar Clientes");
        System.out.println("6. Listar Ingressos Vendidos");
        System.out.println("--------------------------------");
        System.out.println("0. SAIR E SALVAR");
    }

    private static void cadastrarEvento() {
        System.out.println("\n--- Novo Evento ---");

        String artista = lerTextoPadrao("Nome do Artista/Evento: ");
        String local = lerLocal("Local: ");
        String dia = lerData("Data (DD/MM/AAAA): ");
        String horario = lerHorario("Horário (HH:mm): ");

        int capPista = lerInteiroPositivo("Capacidade Pista: ");
        int capCamarote = lerInteiroPositivo("Capacidade Camarote: ");

        Eventos novoEvento = Eventos.cadastrarEvento(local, artista, horario, dia, capCamarote, capPista);
        gerenciador.adicionarEvento(novoEvento);

        System.out.println("Evento cadastrado com sucesso! ID: " + novoEvento.getId());
    }

    private static void cadastrarCliente() {
        System.out.println("\n--- Novo Cliente ---");

        String nome = lerNome("Nome Completo: ");
        String cpf = lerCPF("CPF (apenas números ou com pontos): ");

        if (gerenciador.buscarClientePorCpf(cpf) != null) {
            System.out.println("Erro: Já existe um cliente com este CPF!");
            return;
        }

        String email = lerEmail("Email: ");
        String telefone = lerTelefone("Telefone (DDD + Numero): ");
        int idade = lerInteiroPositivo("Idade: ");

        Cliente novoCliente = Cliente.cadastrarCliente(nome, idade, email, telefone, cpf);
        gerenciador.adicionarCliente(novoCliente);

        System.out.println("Cliente cadastrado com sucesso!");
    }

    private static void realizarInscricao() {
        System.out.println("\n--- Nova Venda de Ingresso ---");

        // 1. Busca o cliente
        String cpf = lerCPF("Digite o CPF do Cliente: ");
        Cliente cliente = gerenciador.buscarClientePorCpf(cpf);

        if (cliente == null) {
            System.out.println("Cliente não encontrado. Cadastre-o primeiro.");
            return;
        }
        System.out.println(">> Cliente selecionado: " + cliente.getNome());

        // 2. Busca o evento
        System.out.print("Digite o ID do Evento: ");
        int idEvento = -1;
        try {
            idEvento = scanner.nextInt();
        } catch (java.util.InputMismatchException e) {
            // Se digitar letra no ID, o catch pega, mas a validação abaixo resolve
        }
        scanner.nextLine(); // Consumir quebra de linha

        Eventos evento = gerenciador.buscarEventoPorId(idEvento);

        if (evento == null) {
            System.out.println("Evento não encontrado com ID " + idEvento);
            return;
        }
        System.out.println(">> Evento selecionado: " + evento.getArtista());

        // 3. Define tipo com validação estrita (Só aceita Pista ou Camarote)
        String tipo = lerTipoIngresso("Tipo de Ingresso (Pista/Camarote): ");

        // --- NOVO: VALIDAÇÃO DE ESTOQUE (LOTAÇÃO) ---
        // 1. Pergunta ao gerenciador quantos já foram vendidos
        int vendidos = gerenciador.contarIngressosVendidos(evento.getId(), tipo);
        
        // 2. Descobre a capacidade total baseada na escolha do usuário
        int capacidadeTotal = tipo.equalsIgnoreCase("Pista") ? evento.getCapacidadeTotalPista() : evento.getCapacidadeTotalCamarote();
        
        // 3. Calcula o saldo
        int disponiveis = capacidadeTotal - vendidos;

        System.out.println(">> Status: " + vendidos + " vendidos de " + capacidadeTotal + " totais.");
        System.out.println(">> Ingressos disponíveis para " + tipo + ": " + disponiveis);

        // 4. BLOQUEIA se não tiver vaga
        if (disponiveis <= 0) {
            System.out.println("❌ ERRO: Ingressos esgotados para o setor " + tipo + "!");
            return; // Cancela a venda e volta para o menu
        }

        // 5. Cria inscrição (Se passou pelo if acima, é porque tem vaga)
        Inscricao novaInscricao = new Inscricao(cliente.getNome(), cliente.getEmail(), tipo, evento.getId());
        gerenciador.adicionarInscricao(novaInscricao);

        System.out.println("✅ Venda realizada com sucesso!");
        System.out.println("🎫 CÓDIGO DO INGRESSO: " + novaInscricao.getCodigoIngresso());
    }

    private static void listarEventos() {
        System.out.println("\n--- Lista de Eventos ---");
        if (gerenciador.listarEventos().isEmpty()) {
            System.out.println("Nenhum evento cadastrado.");
        } else {
            for (Eventos e : gerenciador.listarEventos()) {
                System.out.println(e);
            }
        }
    }

    private static void listarClientes() {
        System.out.println("\n--- Lista de Clientes ---");
        if (gerenciador.listarClientes().isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            for (Cliente c : gerenciador.listarClientes()) {
                System.out.println(c);
            }
        }
    }

    private static void listarInscricoes() {
        System.out.println("\n--- Lista de Inscrições ---");
        if (gerenciador.listarInscricoes().isEmpty()) {
            System.out.println("Nenhuma inscrição realizada.");
        } else {
            for (Inscricao i : gerenciador.listarInscricoes()) {
                System.out.println(i);
            }
        }
    }


    // --- MÉTODOS DE VALIDAÇÃO (TRATAMENTO DE ERROS) ---

    private static String lerTextoPadrao(String mensagem) {
        while (true) {
            String entrada = lerTexto(mensagem); 
            if (entrada.length() < 2) {
                System.out.println(" Erro: O texto é muito curto!");
                continue;
            }
            if (entrada.matches("^[\\p{L}0-9\\s\\.,\\-']+$")) {
                return entrada;
            }
            System.out.println(" Erro: Use apenas letras, números e pontuação básica (sem símbolos como @#$%).");
        }
    }

    private static String lerTexto(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine();
            if (!entrada.trim().isEmpty()) {
                return entrada.trim();
            }
            System.out.println(" Erro: Este campo não pode ficar vazio!");
        }
    }

    private static String lerLocal(String mensagem) {
        while (true) {
            String entrada = lerTexto(mensagem);
            if (entrada.matches("^[0-9\\s]+$")) {
                System.out.println(" Erro: O local não pode ser apenas números! Informe o nome do lugar.");
                continue;
            }
            return entrada;
        }
    }

    private static String lerNome(String mensagem) {
        while (true) {
            String entrada = lerTexto(mensagem);
            if (entrada.length() < 3) {
                System.out.println(" Erro: Nome muito curto!");
                continue;
            }
            if (entrada.matches("^[\\p{L}\\s]+$")) {
                return entrada;
            }
            System.out.println("Erro: Nome de pessoa não pode conter números ou símbolos!");
        }
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return scanner.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println(" Erro: Digite apenas números inteiros!");
                scanner.nextLine(); 
            }
        }
    }

    private static int lerInteiroPositivo(String mensagem) {
        while (true) {
            int valor = lerInteiro(mensagem);
            if (valor > 0) return valor;
            System.out.println(" Erro: O valor deve ser maior que zero!");
        }
    }

    private static String lerCPF(String mensagem) {
        while (true) {
            String entrada = lerTexto(mensagem);
            String apenasNumeros = entrada.replaceAll("[^0-9]", "");
            if (apenasNumeros.length() == 11) {
                return apenasNumeros;
            }
            System.out.println(" Erro: CPF inválido! Deve conter exatamente 11 números.");
        }
    }

    private static String lerEmail(String mensagem) {
        while (true) {
            String entrada = lerTexto(mensagem);
            if (entrada.contains("@") && entrada.contains(".")) {
                return entrada;
            }
            System.out.println("Erro: Email inválido!");
        }
    }

    private static String lerData(String mensagem) {
        while (true) {
            String entrada = lerTexto(mensagem);
            if (entrada.length() != 10) {
                System.out.println(" Erro: Data inválida! Use o formato DD/MM/AAAA.");
                continue;
            }
            try {
                String[] partes = entrada.split("/");
                if (partes.length != 3) {
                    System.out.println(" Erro: Use barras '/' para separar dia, mês e ano.");
                    continue;
                }
                int dia = Integer.parseInt(partes[0]);
                int mes = Integer.parseInt(partes[1]);
                int ano = Integer.parseInt(partes[2]);
                if (dia >= 1 && dia <= 31 && mes >= 1 && mes <= 12 && ano >= 2025) {
                    return entrada;
                }
                System.out.println(" Erro: Data impossível (Dia > 31, Mês > 12 ou Ano antigo).");
            } catch (NumberFormatException e) {
                System.out.println(" Erro: Data inválida! Use apenas números e barras.");
            }
        }
    }

    private static String lerHorario(String mensagem) {
        while (true) {
            String entrada = lerTexto(mensagem);
            if (entrada.length() != 5) {
                System.out.println(" Erro: Horário inválido! Use o formato HH:mm.");
                continue;
            }
            try {
                String[] partes = entrada.split(":");
                if (partes.length != 2) {
                    System.out.println(" Erro: Use dois pontos ':' para separar hora e minuto.");
                    continue;
                }
                int hora = Integer.parseInt(partes[0]);
                int minuto = Integer.parseInt(partes[1]);
                if (hora >= 0 && hora <= 23 && minuto >= 0 && minuto <= 59) {
                    return entrada;
                }
                System.out.println(" Erro: Hora (00-23) ou minuto (00-59) inválidos.");
            } catch (NumberFormatException e) {
                System.out.println(" Erro: Digite apenas números no horário.");
            }
        }
    }

    private static String lerTelefone(String mensagem) {
        while (true) {
            String entrada = lerTexto(mensagem);
            String apenasNumeros = entrada.replaceAll("[^0-9]", "");
            if (apenasNumeros.length() >= 10 && apenasNumeros.length() <= 11) {
                return apenasNumeros;
            }
            System.out.println(" Erro: Telefone inválido! Digite DDD + Número.");
        }
    }

    private static String lerTipoIngresso(String mensagem) {
        while (true) {
            String entrada = lerTexto(mensagem);
            if (entrada.equalsIgnoreCase("Pista") || entrada.equalsIgnoreCase("Camarote")) {
                return entrada.substring(0, 1).toUpperCase() + entrada.substring(1).toLowerCase();
            }
            System.out.println(" Erro: Tipo inválido! Escolha entre 'Pista' ou 'Camarote'.");
        }
    }
}