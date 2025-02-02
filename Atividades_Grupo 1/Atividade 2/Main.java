import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        ListaTeste lista = new ListaTeste();

        while (true) {
            String opcao = JOptionPane.showInputDialog(
                "Escolha uma opção:\n" +
                "1. Inserir valor\n" +
                "2. Buscar valor\n" +
                "3. Eliminar valor\n" +
                "4. Exibir lista\n" +
                "5. Sair"
            );

            if (opcao == null || opcao.equals("5")) {
                break; // Sai do loop e encerra o programa
            }

            switch (opcao) {
                case "1":
                    // Inserir valor
                    String valorInserir = JOptionPane.showInputDialog("Digite o valor a ser inserido:");
                    if (valorInserir != null && !valorInserir.trim().isEmpty()) {
                        try {
                            int valor = Integer.parseInt(valorInserir.trim());
                            lista.inserir(valor);
                            JOptionPane.showMessageDialog(null, "Valor " + valor + " inserido com sucesso.");
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Valor inválido. Por favor, digite um número.");
                        }
                    }
                    break;

                case "2":
                    // Buscar valor
                    String valorBusca = JOptionPane.showInputDialog("Digite o valor a ser buscado:");
                    if (valorBusca != null && !valorBusca.trim().isEmpty()) {
                        try {
                            int valor = Integer.parseInt(valorBusca.trim());
                            String resultado = lista.buscar(valor) != null ? "Encontrado" : "Não encontrado";
                            JOptionPane.showMessageDialog(null, "Buscando elemento com valor " + valor + ": " + resultado);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Valor inválido. Por favor, digite um número.");
                        }
                    }
                    break;

                case "3":
                    // Eliminar valor
                    String valorEliminacao = JOptionPane.showInputDialog("Digite o valor a ser eliminado:");
                    if (valorEliminacao != null && !valorEliminacao.trim().isEmpty()) {
                        try {
                            int valor = Integer.parseInt(valorEliminacao.trim());
                            if (lista.eliminar(valor)) {
                                JOptionPane.showMessageDialog(null, "Valor " + valor + " eliminado com sucesso.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Valor " + valor + " não encontrado na lista.");
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Valor inválido. Por favor, digite um número.");
                        }
                    }
                    break;

                case "4":
                    // Exibir lista
                    JOptionPane.showMessageDialog(null, "Lista atual:\n" + listaParaString(lista));
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida! Escolha uma opção de 1 a 5.");
                    break;
            }
        }
    }

    private static String listaParaString(ListaTeste lista) {
        StringBuilder sb = new StringBuilder();
        ListaTeste.No atual = lista.getInicio();
        while (atual != lista.getCabeca()) {
            sb.append(atual.valor).append(" ");
            atual = atual.proximo;
        }
        return sb.toString().trim();
    }
}