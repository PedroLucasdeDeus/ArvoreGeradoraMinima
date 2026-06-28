import java.util.Random;

public class GeradorGrafos {
    private static final Random rand = new Random();

    public static Grafo gerarCompleto(int v) {
        Grafo grafo = new Grafo(v);
        for (int i = 0; i < v; i++) {
            for (int j = i + 1; j < v; j++) {
                double peso = 1 + rand.nextInt(100); 
                grafo.adicionarAresta(i, j, peso);
            }
        }
        return grafo;
    }

    public static Grafo gerarNaoCompleto(int v) {
        Grafo grafo = new Grafo(v);
        
        // Garante conectividade básica ligando i ao vizinho i-1
        for (int i = 1; i < v; i++) {
            double peso = 1 + rand.nextInt(100);
            grafo.adicionarAresta(i - 1, i, peso);
        }
        
        // Adiciona outras arestas aleatórias com base numa probabilidade (ex: 40%)
        for (int i = 0; i < v; i++) {
            for (int j = i + 2; j < v; j++) {
                if (rand.nextDouble() < 0.4) { 
                    double peso = 1 + rand.nextInt(100);
                    grafo.adicionarAresta(i, j, peso);
                }
            }
        }
        return grafo;
    }
}