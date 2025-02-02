class No {
    int dado;
    No anterior;
    No proximo;

    public No(int dado) {
        this.dado = dado;
        this.anterior = null;
        this.proximo = null;
    }
}

class ListaDE {
    public No inicio;
    private No fim;

    public ListaDE() {
        this.inicio = null;
        this.fim = null;
    }

    public void adicionar(int dado) {
        No novoNo = new No(dado);
        if (inicio == null) {
            inicio = fim = novoNo;
        } else {
            fim.proximo = novoNo;
            novoNo.anterior = fim;
            fim = novoNo;
        }
    }

    public void concatenar(ListaDE lista) {
        if (lista.inicio == null) {
            return;
        }
        if (this.inicio == null) {
            this.inicio = lista.inicio;
            this.fim = lista.fim;
        } else {
            this.fim.proximo = lista.inicio;
            lista.inicio.anterior = this.fim;
            this.fim = lista.fim;
        }
    }

    public ListaDE[] dividir() {
        ListaDE[] resultado = new ListaDE[2];
        resultado[0] = new ListaDE();
        resultado[1] = new ListaDE();

        No lento = inicio;
        No rapido = inicio;

        while (rapido != null && rapido.proximo != null) {
            resultado[0].adicionar(lento.dado);
            lento = lento.proximo;
            rapido = rapido.proximo.proximo;
        }

        while (lento != null) {
            resultado[1].adicionar(lento.dado);
            lento = lento.proximo;
        }

        return resultado;
    }

    public static ListaDE intercalarListasOrdenadas(ListaDE lista1, ListaDE lista2) {
        ListaDE listaIntercalada = new ListaDE();
        No atual1 = lista1.inicio;
        No atual2 = lista2.inicio;

        while (atual1 != null && atual2 != null) {
            if (atual1.dado < atual2.dado) {
                listaIntercalada.adicionar(atual1.dado);
                atual1 = atual1.proximo;
            } else {
                listaIntercalada.adicionar(atual2.dado);
                atual2 = atual2.proximo;
            }
        }

        while (atual1 != null) {
            listaIntercalada.adicionar(atual1.dado);
            atual1 = atual1.proximo;
        }

        while (atual2 != null) {
            listaIntercalada.adicionar(atual2.dado);
            atual2 = atual2.proximo;
        }

        return listaIntercalada;
    }

    public void ordenar() {
        if (inicio == null || inicio.proximo == null) {
            return;
        }

        boolean trocou;
        do {
            trocou = false;
            No atual = inicio;
            No anterior = null;

            while (atual.proximo != null) {
                if (atual.dado > atual.proximo.dado) {
                    No proximo = atual.proximo;
                    atual.proximo = proximo.proximo;
                    if (proximo.proximo != null) {
                        proximo.proximo.anterior = atual;
                    }
                    proximo.proximo = atual;
                    atual.anterior = proximo;

                    if (anterior == null) {
                        inicio = proximo;
                    } else {
                        anterior.proximo = proximo;
                    }
                    proximo.anterior = anterior;

                    trocou = true;
                } else {
                    anterior = atual;
                    atual = atual.proximo;
                }
            }
        } while (trocou);
    }

    public ListaDE copiar() {
        ListaDE copia = new ListaDE();
        No atual = this.inicio;
        while (atual != null) {
            copia.adicionar(atual.dado);
            atual = atual.proximo;
        }
        return copia;
    }
}
