package utils;

import controller.GerenciadorDeEventos;
import java.io.*;


/** Classe PersistenciaArquivo
 * Responsável exclusivamente para salvar e carregar os dados do HD
 */

public class PersistenciaArquivo {

    //Nome do arquivo onde os dados serão salvos na pasta do projeto
    private String nomeArquivo = "dados_sistema.bin";

    //  --- Metodo para SALVAR --- //
    public void salvarDados(GerenciadorDeEventos gerenciador) {

        // O bloco try-catch vai servir para tratar erros de disco (HD cheio, sem permissão, etc)
        try {
            // Cria o fluxo de saída para um arquivo
            FileOutputStream fileOut = new FileOutputStream(nomeArquivo);

            // Cria o objeto que transforma classes Java em bytes
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            // Escreve o objeto 'gerenciador' inteiro (com todas as listas dentro)
            objectOut.writeObject(gerenciador);

            //  Fecha os fluxos para liberar memória e salvar o arquivo
            objectOut.close();
            fileOut.close();

            System.out.println(">> Dados salvos com sucesso em: " + nomeArquivo);

        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }

    }

    // --- Metodo para CARREGAR (Ler do disco) ---
    public GerenciadorDeEventos carregarDados() {
        GerenciadorDeEventos gerenciador = null;

        try {
            // Tenta abrir o arquivo para leitura
            FileInputStream fileIn = new FileInputStream(nomeArquivo);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            //  Lê os bytes e converte (cast) de volta para o nosso objeto GerenciadorDeEventos
            gerenciador = (GerenciadorDeEventos) objectIn.readObject();

            //  Fecha tudo
            objectIn.close();
            fileIn.close();

            System.out.println("Dados carregados com sucesso.");

        } catch (FileNotFoundException e) {
            // Se cair aqui, é porque é a primeira vez que rodamos o programa e o arquivo ainda não existe.
            System.out.println("Criando base de dados vazia.");
            gerenciador = new GerenciadorDeEventos();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
            // Em caso de erro, retorna um novo para o programa não travar
            gerenciador = new GerenciadorDeEventos();
        }

        return gerenciador;
    }
}
