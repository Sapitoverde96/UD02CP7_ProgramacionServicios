import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/// Genera un número aleatorio
Random generarAleatorio = new Random();

/// Determina el tamaño total que va a poseer la lista a ordenar
public int tamañoLista = generarAleatorio.nextInt(5, 100 + 1);

void main() {
    List<Integer> listaDesordenada = new ArrayList<>();
    for (int indiceLista = 0; indiceLista < tamañoLista; indiceLista++) {
        listaDesordenada.add(generarAleatorio.nextInt(1, 100 + 1));
    }

    List<Integer> listaOrdenada = new ArrayList<>(listaDesordenada);
    Nodo nodoPadre = new Nodo(listaOrdenada);
    nodoPadre.start();
    try {
        nodoPadre.join();
    } catch (InterruptedException excepcion) {
        System.err.printf("Ha ocurrido un error: %s%n", excepcion.getMessage());
    }

    System.out.printf("=== Ordenación por mezcla concurrente ===%n");
    System.out.printf("Lista aleatoria de 5 a 100 números entre 1 y 100.%n%n");
    System.out.printf("Lista original (%d elementos):%n%s%n%n", listaDesordenada.size(), listaDesordenada);
    System.out.printf("Lista ordenada:%n%s%n", listaOrdenada);
}