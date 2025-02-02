import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        ListaDE lista1 = new ListaDE();
        ListaDE lista2 = new ListaDE();

        String inputLista1 = JOptionPane.showInputDialog("Digite Os Números Da Primeira Lista Separados Por Vírgula (Ex: 1,2,3):");
        if (inputLista1 != null && !inputLista1.trim().isEmpty()) {
            String[] numerosLista1 = inputLista1.split(",");
            for (String num : numerosLista1) {
                try {
                    lista1.adicionar(Integer.parseInt(num.trim()));
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Entrada Inválida: " + num + ". Ignorando Este Valor.");
                }
            }
        }

        String inputLista2 = JOptionPane.showInputDialog("Digite Os Números Da Segunda Lista Separados Por Vírgula (Ex: 4,5,6):");
        if (inputLista2 != null && !inputLista2.trim().isEmpty()) {
            String[] numerosLista2 = inputLista2.split(",");
            for (String num : numerosLista2) {
                try {
                    lista2.adicionar(Integer.parseInt(num.trim()));
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Entrada Inválida: " + num + ". Ignorando Este Valor.");
                }
            }
        }

        while (true) {
            String opcao = JOptionPane.showInputDialog(
                "Escolha uma opção:\n" +
                "1. Concatenar Listas\n" +
                "2. Intercalar Listas Ordenadas\n" +
                "3. Dividir Lista\n" +
                "4. Sair"
            );

            if (opcao == null || opcao.equals("4")) {
                break;
            }

            switch (opcao) {
                case "1":
                    ListaDE listaConcatenada = new ListaDE();
                    listaConcatenada.concatenar(lista1.copiar());
                    listaConcatenada.concatenar(lista2.copiar());
                    JOptionPane.showMessageDialog(null, "Lista Concatenada:\n" + listaParaString(listaConcatenada));
                    if (JOptionPane.showConfirmDialog(null, "Deseja Dividir A Lista Concatenada?") == JOptionPane.YES_OPTION) {
                        ListaDE[] listasDivididas = listaConcatenada.dividir();
                        JOptionPane.showMessageDialog(null, "Lista 1 Após Divisão:\n" + listaParaString(listasDivididas[0]));
                        JOptionPane.showMessageDialog(null, "Lista 2 Após Divisão:\n" + listaParaString(listasDivididas[1]));
                    }
                    break;

                case "2":
                    ListaDE lista1Copia = lista1.copiar();
                    ListaDE lista2Copia = lista2.copiar();
                    lista1Copia.ordenar();
                    lista2Copia.ordenar();
                    ListaDE listaIntercalada = ListaDE.intercalarListasOrdenadas(lista1Copia, lista2Copia);
                    JOptionPane.showMessageDialog(null, "Lista Intercalada Ordenada:\n" + listaParaString(listaIntercalada));
                    if (JOptionPane.showConfirmDialog(null, "Deseja Dividir A Lista Intercalada?") == JOptionPane.YES_OPTION) {
                        ListaDE[] listasDivididas = listaIntercalada.dividir();
                        JOptionPane.showMessageDialog(null, "Lista 1 Após Divisão:\n" + listaParaString(listasDivididas[0]));
                        JOptionPane.showMessageDialog(null, "Lista 2 Após Divisão:\n" + listaParaString(listasDivididas[1]));
                    }
                    break;

                case "3":
                    String escolha = JOptionPane.showInputDialog("Qual Lista Deseja Dividir? (Digite 1 ou 2):");
                    if (escolha != null && (escolha.equals("1") || escolha.equals("2"))) {
                        ListaDE listaParaDividir = escolha.equals("1") ? lista1.copiar() : lista2.copiar();
                        ListaDE[] listasDivididas = listaParaDividir.dividir();
                        JOptionPane.showMessageDialog(null, "Lista 1 Após Divisão:\n" + listaParaString(listasDivididas[0]));
                        JOptionPane.showMessageDialog(null, "Lista 2 Após Divisão:\n" + listaParaString(listasDivididas[1]));
                    } else {
                        JOptionPane.showMessageDialog(null, "Opção Inválida!");
                    }
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opção Inválida!");
                    break;
            }
        }
    }

    private static String listaParaString(ListaDE lista) {
        StringBuilder sb = new StringBuilder();
        No atual = lista.inicio;
        while (atual != null) {
            sb.append(atual.dado).append(" ");
            atual = atual.proximo;
        }
        return sb.toString().trim();
    }
}
