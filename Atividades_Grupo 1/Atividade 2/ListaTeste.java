public class ListaTeste {
    private No cabeca;

    public class No {
        int valor;
        No proximo;
        No anterior;

        No(int valor) {
            this.valor = valor;
        }
    }

    public ListaTeste() {
        cabeca = new No(0); 
        cabeca.proximo = cabeca;
        cabeca.anterior = cabeca;
    }

    public No buscar(int valor) {
        No atual = cabeca.proximo;
        while (atual != cabeca) {
            if (atual.valor == valor) {
                return atual;
            }
            atual = atual.proximo;
        }
        return null;
    }

    public void inserir(int valor) {
        No novoNo = new No(valor);
        No ultimo = cabeca.anterior;
        novoNo.proximo = cabeca;
        novoNo.anterior = ultimo;
        ultimo.proximo = novoNo;
        cabeca.anterior = novoNo;
    }

    public boolean eliminar(int valor) {
        No atual = cabeca.proximo;
        while (atual != cabeca) {
            if (atual.valor == valor) {
                No anterior = atual.anterior;
                No proximo = atual.proximo;
                anterior.proximo = proximo;
                proximo.anterior = anterior;
                return true;
            }
            atual = atual.proximo;
        }
        return false;
    }

    public No getCabeca() {
        return cabeca;
    }

    public No getInicio() {
        return cabeca.proximo;
    }
}