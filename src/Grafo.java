import java.util.ArrayList;
import java.util.List;

public class Grafo {
    private int numVertices;
    private List<Aresta> todasArestas;
    private List<List<Aresta>> listaAdjacencia;

    public Grafo(int numVertices) {
        this.numVertices = numVertices;
        this.todasArestas = new ArrayList<>();
        this.listaAdjacencia = new ArrayList<>(numVertices);
        
        for (int i = 0; i < numVertices; i++) {
            listaAdjacencia.add(new ArrayList<>());
        }
    }

    public void adicionarAresta(int origem, int destino, double peso) {
        Aresta arestaIda = new Aresta(origem, destino, peso);
        // Como é para Árvore Geradora Mínima, o grafo é não-direcionado
        Aresta arestaVolta = new Aresta(destino, origem, peso);

        todasArestas.add(arestaIda);
        
        listaAdjacencia.get(origem).add(arestaIda);
        listaAdjacencia.get(destino).add(arestaVolta);
    }

    // Getters e Setters
    public int getNumVertices() {
        return numVertices;
    }

    public void setNumVertices(int numVertices) {
        this.numVertices = numVertices;
    }

    public List<Aresta> getTodasArestas() {
        return todasArestas;
    }

    public void setTodasArestas(List<Aresta> todasArestas) {
        this.todasArestas = todasArestas;
    }

    public List<List<Aresta>> getListaAdjacencia() {
        return listaAdjacencia;
    }

    public void setListaAdjacencia(List<List<Aresta>> listaAdjacencia) {
        this.listaAdjacencia = listaAdjacencia;
    }
}
