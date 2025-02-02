import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        ListaCircularDupla lista = new ListaCircularDupla();
        int opcao = -1;

        do {
            String input = JOptionPane.showInputDialog(
                "Escolha Uma Opção:\n" +
                "1. Inserir Elementos À Esquerda Da Cabeça Da Lista\n" +
                "2. Contar O Número De Elementos Na Lista\n" +
                "3. Fazer Uma Cópia Da Lista\n" +
                "4. Concatenar Duas Listas Circulares\n" +
                "5. Intercalar Duas Listas Ordenadas\n" +
                "7. Sair"
            );

            if (input == null || input.isEmpty()) {
                continue;
            }

            try {
                opcao = Integer.parseInt(input.trim());

                switch (opcao) {
                    case 1:
                        inserirElementos(lista);
                        break;
                    case 2:
                        JOptionPane.showMessageDialog(null, "Número De Elementos Na Lista: " + lista.contarElementos());
                        break;
                    case 3:
                        ListaCircularDupla copia = lista.copiar();
                        JOptionPane.showMessageDialog(null, "Cópia Da Lista:\n" + listaParaString(copia));
                        break;
                    case 4:
                        concatenarListas(lista);
                        break;
                    case 5:
                        intercalarListas(lista);
                        break;
                    case 7:
                        JOptionPane.showMessageDialog(null, "Saindo Do Programa.");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opção Inválida. Tente Novamente.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Opção Inválida. Tente Novamente.");
                opcao = -1;
            }

        } while (opcao != 7);
    }

    private static void inserirElementos(ListaCircularDupla lista) {
        String input = JOptionPane.showInputDialog("Digite Os Valores Para Inserção Separados Por Vírgula (Ex: 1,2,3):");
        if (input != null && !input.trim().isEmpty()) {
            String[] valores = input.split(",");
            for (String valor : valores) {
                try {
                    lista.inserirEsquerda(Integer.parseInt(valor.trim()));
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Valor Inválido: " + valor + ". Ignorando Este Valor.");
                }
            }
            JOptionPane.showMessageDialog(null, "Lista Após Inserção:\n" + listaParaString(lista));
        }
    }

    private static void concatenarListas(ListaCircularDupla lista) {
        ListaCircularDupla lista2 = new ListaCircularDupla();
        inserirElementos(lista2);
        lista.concatenar(lista2);
        JOptionPane.showMessageDialog(null, "Lista Após Concatenação:\n" + listaParaString(lista));
    }

    private static void intercalarListas(ListaCircularDupla lista) {
        ListaCircularDupla lista3 = new ListaCircularDupla();
        inserirElementos(lista3);
        ListaCircularDupla listaIntercalada = lista.intercalar(lista3);
        JOptionPane.showMessageDialog(null, "Lista Intercalada:\n" + listaParaString(listaIntercalada));
    }

    private static String listaParaString(ListaCircularDupla lista) {
        if (lista.contarElementos() == 0) {
            return "Lista Vazia.";
        }
        StringBuilder sb = new StringBuilder();
        ListaCircularDupla.No atual = lista.getCabeca();
        do {
            sb.append(atual.valor).append(" ");
            atual = atual.proximo;
        } while (atual != lista.getCabeca());
        return sb.toString().trim();
    }
}
