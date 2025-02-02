public class ListaCircularComCabeca {
    private No cabeca; // Nó cabeça
    private No cauda;

    public class No {
        int valor;
        No proximo;

        No(int valor) {
            this.valor = valor;
            this.proximo = null;
        }
    }

    public ListaCircularComCabeca() {
        cabeca = new No(-1); // Nó cabeça sem valor útil
        cabeca.proximo = cabeca; // Inicialmente aponta para si mesmo
        cauda = cabeca;
    }

    // Método 1: Contar o número de elementos (excluindo o nó cabeça)
    public int contarElementos() {
        int contagem = 0;
        No atual = cabeca.proximo;

        while (atual != cabeca) {
            contagem++;
            atual = atual.proximo;
        }
        return contagem;
    }

    // Método 2: Inserir um elemento à esquerda (antes do primeiro nó útil)
    public void inserirEsquerda(int valor) {
        No novoNo = new No(valor);
        if (cabeca.proximo == cabeca) { // Lista vazia
            cabeca.proximo = novoNo;
            novoNo.proximo = cabeca;
            cauda = novoNo;
        } else {
            novoNo.proximo = cabeca.proximo;
            cabeca.proximo = novoNo;
        }
    }

    // Método 3: Concatenar duas listas circulares
    public void concatenar(ListaCircularComCabeca lista) {
        if (lista.cabeca.proximo == lista.cabeca) return; // Lista vazia, nada a concatenar

        if (cabeca.proximo == cabeca) { // Se a lista atual estiver vazia
            cabeca.proximo = lista.cabeca.proximo;
            cauda = lista.cauda;
        } else {
            cauda.proximo = lista.cabeca.proximo;
            lista.cauda.proximo = cabeca;
            cauda = lista.cauda;
        }
    }

    // Método 4: Intercalar duas listas ordenadas
    public ListaCircularComCabeca intercalar(ListaCircularComCabeca lista) {
        ListaCircularComCabeca resultado = new ListaCircularComCabeca();
        No a = this.cabeca.proximo;
        No b = lista.cabeca.proximo;

        while (a != this.cabeca && b != lista.cabeca) {
            if (a.valor <= b.valor) {
                resultado.inserirOrdenado(a.valor);
                a = a.proximo;
            } else {
                resultado.inserirOrdenado(b.valor);
                b = b.proximo;
            }
        }

        while (a != this.cabeca) {
            resultado.inserirOrdenado(a.valor);
            a = a.proximo;
        }

        while (b != lista.cabeca) {
            resultado.inserirOrdenado(b.valor);
            b = b.proximo;
        }

        return resultado;
    }

    public void inserirOrdenado(int valor) {
        No novoNo = new No(valor);
        No atual = cabeca;

        while (atual.proximo != cabeca && atual.proximo.valor < valor) {
            atual = atual.proximo;
        }

        novoNo.proximo = atual.proximo;
        atual.proximo = novoNo;

        if (novoNo.proximo == cabeca) {
            cauda = novoNo;
        }
    }

    // Método 5: Fazer uma cópia da lista
    public ListaCircularComCabeca copiar() {
        ListaCircularComCabeca copia = new ListaCircularComCabeca();
        No atual = this.cabeca.proximo;
        while (atual != this.cabeca) {
            copia.inserirEsquerda(atual.valor);
            atual = atual.proximo;
        }
        return copia;
    }

    // Método para acessar a cabeça
    public No getCabeca() {
        return cabeca;
    }

    // Método auxiliar para converter a lista em uma string formatada
    public String listaParaString() {
        if (contarElementos() == 0) return "Lista vazia.";

        StringBuilder sb = new StringBuilder();
        No atual = cabeca.proximo;
        while (atual != cabeca) {
            sb.append(atual.valor).append(" ");
            atual = atual.proximo;
        }
        return sb.toString().trim();
    }
}
