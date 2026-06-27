import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class AGMalgoritmos {
    public double Kruskal(int numVertices, List<Aresta> listaArestas) {
        Collections.sort(listaArestas);
        int[] pais = new int[numVertices];
        for (int i = 0; i < numVertices; ++i) {
            pais[i] = i;
        }
        double pesoTotal = 0.0;
        int arestasIteradas = 0;
        for (Aresta a : listaArestas) {
            if (arestasIteradas == numVertices - 1) break;
            int raizOrigem = encontrarRaiz(pais, a.getOrigem());
            int raizDestino = encontrarRaiz(pais, a.getDestino());

            if (raizOrigem == raizDestino) {
                continue;
            }
            pais[raizOrigem] = raizDestino;
            pesoTotal += a.getPeso();
            arestasIteradas++;
        }
        return pesoTotal;
    }

    public int encontrarRaiz (int[] pais, int vertice) {
        if (vertice == pais[vertice]) {
            return vertice;
        }
        return pais[vertice] = encontrarRaiz(pais, pais[vertice]);
    }
}