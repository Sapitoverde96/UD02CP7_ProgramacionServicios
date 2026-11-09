import java.util.ArrayList;
import java.util.List;

public class Nodo extends Thread {

    /// Lista sobre la que se va a operar
    private final List<Integer> lista;

    /**
     * Constructor de la clase {@code Nodo} que recibe una lista y crea una hebra
     * @param lista lista desordenada
     */
    public Nodo(List<Integer> lista) {
        this.lista = lista;
    }

    /**
     * Esta función añade una lista con un rango fijo
     * @param lista lista
     * @param desde elemento desde el que empieza la lista (Incluido)
     * @param hasta elemento hasta el que termina la lista (Excluido)
     */
    private void añade(List<Integer> lista, int desde, int hasta) {
        lista.addAll(this.lista.subList(desde, hasta));
    }

    /**
     * Esta función fusiona dos sublistas ya ordenadas en una sola lista ordenada más grande
     * @param sublista01 lista que actúa como primera sub-mitad
     * @param sublista02 lista que actúa como segunda sub-mitad
     */
    private void mezcla(List<Integer> sublista01,  List<Integer> sublista02) {
        this.lista.clear();
        int indiceSublista01 = 0;
        int indiceSublista02 = 0;
        while (indiceSublista01 < sublista01.size() && indiceSublista02 < sublista02.size()) {
            if (sublista01.get(indiceSublista01) <= sublista02.get(indiceSublista02)) {
                this.lista.add(sublista01.get(indiceSublista01++));
            } else {
                this.lista.add(sublista02.get(indiceSublista02++));
            }
        }
        while (indiceSublista01 < sublista01.size()) {
            this.lista.add(sublista01.get(indiceSublista01++));
        }
        while (indiceSublista02 < sublista02.size()) {
            this.lista.add(sublista02.get(indiceSublista02++));
        }
    }

    /**
     * Esta función opera sobre una lista desordenada mediante funciones internas para
     * organizar la lista con el tamaño original pero ordenada. Para ello crea dos hebras
     * hijas que operan con cada mitad de la lista original
     */
    @Override
    public void run() {
        if (this.lista.size() <= 1) {
            return;
        }

        int mitad = this.lista.size() / 2;
        List<Integer> mitadLista01 = new ArrayList<>();
        List<Integer> mitadLista02 = new ArrayList<>();
        añade(mitadLista01, 0, mitad);
        añade(mitadLista02, mitad, this.lista.size());

        Nodo nodoMitad01 = new Nodo(mitadLista01);
        Nodo nodoMitad02 = new Nodo(mitadLista02);
        nodoMitad01.start();
        nodoMitad02.start();
        try {
            nodoMitad01.join();
            nodoMitad02.join();
        } catch (InterruptedException excepcion) {
            System.err.printf("Ha ocurrido un error: %s%n", excepcion.getMessage());
        }
        mezcla(mitadLista01, mitadLista02);
    }
}
