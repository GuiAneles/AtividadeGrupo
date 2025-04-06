import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class SelectionSort {

    private int[] numeros;
    private long comparacoes;
    private long trocas;
    private long duracaoMillis;

    public SelectionSort(int[] n) {
        this.numeros = n;
        this.comparacoes = 0;
        this.trocas = 0;
    }

    private void trocar(int[] vetor, int i, int j) {
        int temp = vetor[i];
        vetor[i] = vetor[j];
        vetor[j] = temp;
        trocas++;
    }

    public void ordenar() {
        int tamanho = numeros.length;
        long inicio = System.nanoTime();

        for (int i = 0; i < tamanho - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < tamanho; j++) {
                comparacoes++;
                if (numeros[j] < numeros[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                trocar(numeros, i, minIndex);
            }
        }

        long fim = System.nanoTime();
        duracaoMillis = TimeUnit.NANOSECONDS.toMillis(fim - inicio);

        System.out.println("Tempo de execucao: " + formatarTempo(duracaoMillis));
        System.out.println("Comparacoes: " + comparacoes);
        System.out.println("Trocas: " + trocas);
    }

    private String formatarTempo(long millis) {
        long horas = millis / (1000 * 60 * 60);
        long minutos = (millis / (1000 * 60)) % 60;
        long segundos = (millis / 1000) % 60;
        long milissegundos = millis % 1000;
        return String.format("%02d:%02d:%02d:%03d", horas, minutos, segundos, milissegundos);
    }

    public void salvarResultadoJson(String caminho) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho))) {
            writer.write("{\n");
            writer.write("  \"tempo_execucao\": \"" + formatarTempo(duracaoMillis) + "\",\n");
            writer.write("  \"comparacoes\": " + comparacoes + ",\n");
            writer.write("  \"trocas\": " + trocas + ",\n");
            writer.write("  \"vetor_ordenado\": [");

            for (int i = 0; i < numeros.length; i++) {
                writer.write(String.valueOf(numeros[i]));
                if (i < numeros.length - 1) writer.write(", ");
            }

            writer.write("]\n}");
            writer.flush();
        } catch (IOException e) {
            System.out.println("Erro ao salvar resultado JSON: " + e.getMessage());
        }
    }

    public static int[] lerArquivoJsonManual(String caminho) {
        try {
            String conteudo = new String(Files.readAllBytes(Paths.get(caminho)));
            conteudo = conteudo.replaceAll("\\s", "");
            int inicio = conteudo.indexOf("[");
            int fim = conteudo.indexOf("]");

            if (inicio == -1 || fim == -1 || fim <= inicio) {
                return new int[0];
            }

            String arrayStr = conteudo.substring(inicio + 1, fim);
            String[] partes = arrayStr.split(",");

            int[] numeros = new int[partes.length];
            for (int i = 0; i < partes.length; i++) {
                numeros[i] = Integer.parseInt(partes[i]);
            }

            return numeros;
        } catch (IOException | NumberFormatException | StringIndexOutOfBoundsException e) {
            return new int[0];
        }
    }

    public static void main(String[] args) {
        String entrada = "Trabalho em Dupla\\Base de Dados\\100MilDados.json";
        String saida = "Trabalho em Dupla\\SelectionSort\\Saida - SelectionSort.json";

        int[] vetor = lerArquivoJsonManual(entrada);
        if (vetor.length == 0) {
            System.out.println("Erro de leitura do Json");
            return;
        }

        SelectionSort ordenacao = new SelectionSort(vetor);
        ordenacao.ordenar();
        ordenacao.salvarResultadoJson(saida);
    }
}
