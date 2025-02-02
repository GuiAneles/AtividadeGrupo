public class ListaCircular {
    private No cabeca;
    private No cauda;

    public class No {
        int valor;
        No proximo;

        No(int valor) {
            this.valor = valor;
        }
    }

    public ListaCircular() {
        cabeca = null;
        cauda = null;
    }

    public int contarElementos() {
        if (cabeca == null) return 0;
        int contagem = 1;
        No atual = cabeca.proximo;
        while (atual != cabeca) {
            contagem++;
            atual = atual.proximo;
        }
        return contagem;
    }

    public void inserirEsquerda(int valor) {
        No novoNo = new No(valor);
        if (cabeca == null) {
            cabeca = novoNo;
            cauda = novoNo;
            novoNo.proximo = novoNo;
        } else {
            novoNo.proximo = cabeca;
            cauda.proximo = novoNo;
            cabeca = novoNo;
        }
    }

    public void concatenar(ListaCircular lista) {
        if (this.cabeca == null) {
            this.cabeca = lista.cabeca;
            this.cauda = lista.cauda;
        } else if (lista.cabeca != null) {
            this.cauda.proximo = lista.cabeca;
            lista.cauda.proximo = this.cabeca;
            this.cauda = lista.cauda;
        }
    }

    public ListaCircular intercalar(ListaCircular lista) {
        ListaCircular resultado = new ListaCircular();
        No a = this.cabeca;
        No b = lista.cabeca;

        if (a == null) return lista.copiar();
        if (b == null) return this.copiar();

        do {
            resultado.inserirOrdenado(a.valor);
            a = a.proximo;
        } while (a != this.cabeca);

        do {
            resultado.inserirOrdenado(b.valor);
            b = b.proximo;
        } while (b != lista.cabeca);

        return resultado;
    }

    public void inserirOrdenado(int valor) {
        No novoNo = new No(valor);
        if (cabeca == null) {
            cabeca = novoNo;
            cauda = novoNo;
            novoNo.proximo = novoNo;
        } else if (valor < cabeca.valor) {
            novoNo.proximo = cabeca;
            cauda.proximo = novoNo;
            cabeca = novoNo;
        } else {
            No atual = cabeca;
            while (atual.proximo != cabeca && atual.proximo.valor < valor) {
                atual = atual.proximo;
            }
            novoNo.proximo = atual.proximo;
            atual.proximo = novoNo;
            if (atual == cauda) {
                cauda = novoNo;
            }
        }
    }

    public ListaCircular copiar() {
        ListaCircular copia = new ListaCircular();
        if (this.cabeca == null) return copia;
        No atual = this.cabeca;
        do {
            copia.inserirOrdenado(atual.valor);
            atual = atual.proximo;
        } while (atual != this.cabeca);
        return copia;
    }

    public No getCabeca() {
        return cabeca;
    }
}
