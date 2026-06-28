public class AnalisadorDeTempo {
    private static final AGMalgoritmos algoritmos = new AGMalgoritmos();

    public static long medirPrim(Grafo grafo) {
        long inicio = System.nanoTime();
        algoritmos.prim(grafo.getNumVertices(), grafo.getListaAdjacencia());
        long fim = System.nanoTime();
        return (fim - inicio);
    }

    public static long medirKruskal(Grafo grafo) {
        long inicio = System.nanoTime();
        algoritmos.kruskal(grafo.getNumVertices(), grafo.getTodasArestas());
        long fim = System.nanoTime();
        return (fim - inicio);
    }
}
