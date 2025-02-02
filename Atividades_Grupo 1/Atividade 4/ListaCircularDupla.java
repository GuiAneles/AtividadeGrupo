public class ListaCircularDupla {
    private No cabeca;
    private No cauda;

    // Alterado de "private" para "public"
    public class No {
        int valor;
        No proximo;
        No anterior;

        No(int valor) {
            this.valor = valor;
        }
    }

    public ListaCircularDupla() {
        cabeca = null;
        cauda = null;
    }

    public int contarElementos() {
        if (cabeca == null) {
            return 0;
        }
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
            cabeca.proximo = cabeca;
            cabeca.anterior = cabeca;
        } else {
            novoNo.proximo = cabeca;
            novoNo.anterior = cauda;
            cauda.proximo = novoNo;
            cabeca.anterior = novoNo;
            cabeca = novoNo;
        }
    }

    public void concatenar(ListaCircularDupla lista) {
        if (this.cabeca == null) {
            this.cabeca = lista.cabeca;
            this.cauda = lista.cauda;
        } else if (lista.cabeca != null) {
            this.cauda.proximo = lista.cabeca;
            lista.cabeca.anterior = this.cauda;
            lista.cauda.proximo = this.cabeca;
            this.cabeca.anterior = lista.cauda;
            this.cauda = lista.cauda;
        }
    }

    public ListaCircularDupla intercalar(ListaCircularDupla lista) {
        ListaCircularDupla resultado = new ListaCircularDupla();
        No a = this.cabeca;
        No b = lista.cabeca;

        if (a == null) return lista;
        if (b == null) return this;

        do {
            if (a.valor <= b.valor) {
                resultado.inserirOrdenado(a.valor);
                a = a.proximo;
            } else {
                resultado.inserirOrdenado(b.valor);
                b = b.proximo;
            }
        } while (a != this.cabeca && b != lista.cabeca);

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
        if (cabeca == null || valor <= cabeca.valor) {
            inserirEsquerda(valor);
            return;
        }
        No atual = cabeca;
        while (atual.proximo != cabeca && atual.proximo.valor < valor) {
            atual = atual.proximo;
        }
        novoNo.proximo = atual.proximo;
        novoNo.anterior = atual;
        atual.proximo.anterior = novoNo;
        atual.proximo = novoNo;

        if (novoNo.proximo == cabeca) {
            cauda = novoNo;
        }
    }

    public ListaCircularDupla copiar() {
        ListaCircularDupla copia = new ListaCircularDupla();
        if (this.cabeca == null) {
            return copia;
        }
        No atual = this.cabeca;
        do {
            copia.inserirEsquerda(atual.valor);
            atual = atual.proximo;
        } while (atual != this.cabeca);
        return copia;
    }

    public No getCabeca() {
        return cabeca;
    }
}
