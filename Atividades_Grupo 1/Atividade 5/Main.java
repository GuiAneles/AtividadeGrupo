import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        ListaCircularComCabeca lista = new ListaCircularComCabeca();

        int opcao;
        do {
            String menu = """
                Escolha Uma Opção:
                1 - Inserir Elemento À Esquerda
                2 - Contar Elementos
                3 - Fazer Cópia Da Lista
                4 - Concatenar Outra Lista
                5 - Intercalar Com Outra Lista Ordenada
                6 - Exibir Lista
                7 - Sair
            """;

            try {
                opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));

                switch (opcao) {
                    case 1 -> {
                        int valor = Integer.parseInt(JOptionPane.showInputDialog("Digite O Valor A Ser Inserido:"));
                        lista.inserirEsquerda(valor);
                    }
                    case 2 -> JOptionPane.showMessageDialog(null, "Número De Elementos: " + lista.contarElementos());
                    case 3 -> {
                        ListaCircularComCabeca copia = lista.copiar();
                        JOptionPane.showMessageDialog(null, "Cópia Da Lista:\n" + copia.listaParaString());
                    }
                    case 4 -> {
                        ListaCircularComCabeca lista2 = new ListaCircularComCabeca();
                        String input = JOptionPane.showInputDialog("Digite Os Valores Da Segunda Lista Separados Por Vírgula:");
                        if (input != null && !input.trim().isEmpty()) {
                            for (String valor : input.split(",")) {
                                lista2.inserirEsquerda(Integer.parseInt(valor.trim()));
                            }
                        }
                        lista.concatenar(lista2);
                        JOptionPane.showMessageDialog(null, "Lista Após Concatenação:\n" + lista.listaParaString());
                    }
                    case 5 -> {
                        ListaCircularComCabeca lista3 = new ListaCircularComCabeca();
                        String input = JOptionPane.showInputDialog("Digite Os Valores Da Outra Lista Ordenada Separados Por Vírgula:");
                        if (input != null && !input.trim().isEmpty()) {
                            for (String valor : input.split(",")) {
                                lista3.inserirOrdenado(Integer.parseInt(valor.trim()));
                            }
                        }
                        ListaCircularComCabeca intercalada = lista.intercalar(lista3);
                        JOptionPane.showMessageDialog(null, "Lista Intercalada:\n" + intercalada.listaParaString());
                    }
                    case 6 -> JOptionPane.showMessageDialog(null, "Lista Atual:\n" + lista.listaParaString());
                    case 7 -> JOptionPane.showMessageDialog(null, "Saindo...");
                    default -> JOptionPane.showMessageDialog(null, "Opção Inválida!");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada Inválida! Digite Um Número.");
                opcao = 0;
            }
        } while (opcao != 7);
    }
}
