import java.util.*;

public class AGMalgoritmos {
    public double kruskal(int numVertices, List<Aresta> listaArestas) {
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

    public double prim (int numVertices, List<List<Aresta>> listaAdj) {
        double pesoTotal = 0.0;
        int arestas = 0;
        boolean[] percorrido = new boolean[numVertices];
        Queue<Aresta> fila = new PriorityQueue<>();
        percorrido[0] = true;
        fila.addAll(listaAdj.getFirst());
        while (arestas < numVertices - 1 && !fila.isEmpty()) {
            Aresta menorAresta = fila.poll();
            if (percorrido[menorAresta.getDestino()] && percorrido[menorAresta.getOrigem()]) continue;
            int proximoVertice;
            if (percorrido[menorAresta.getDestino()]) {
                proximoVertice = menorAresta.getOrigem();
            }
            else {
                proximoVertice = menorAresta.getDestino();
            }
            percorrido[proximoVertice] = true;
            pesoTotal += menorAresta.getPeso();
            arestas++;
            fila.addAll(listaAdj.get(proximoVertice));
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