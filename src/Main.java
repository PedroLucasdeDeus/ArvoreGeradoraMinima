import java.util.Random;

public class Main {

    // Instância única para execução dos algoritmos de teste
    private static final AGMalgoritmos algoritmos = new AGMalgoritmos();

    public static void main(String[] args) {
        // Configurações do experimento
        int quantidadeGrafosPorTamanho = 10;
        int[] tamanhosV = {10, 15, 20, 25, 50, 100, 500, 1000, 3000}; // Escala de crescimento dos testes

        System.out.println("Iniciando análise de desempenho: Prim vs Kruskal\n");
        System.out.println("Vértices | Tipo         | Tempo Médio Prim (ns) | Tempo Médio Kruskal (ns)");
        System.out.println("------------------------------------------------------------------");

        for (int V : tamanhosV) {
            
            long tempoTotalPrimCompleto = 0;
            long tempoTotalKruskalCompleto = 0;
            long tempoTotalPrimNaoCompleto = 0;
            long tempoTotalKruskalNaoCompleto = 0;

            for (int i = 0; i < quantidadeGrafosPorTamanho; i++) {
                // 1. Testes com Grafos Completos
                Grafo grafoCompleto = gerarGrafoCompleto(V);
                
                tempoTotalPrimCompleto += medirTempoPrim(grafoCompleto);
                tempoTotalKruskalCompleto += medirTempoKruskal(grafoCompleto);

                // 2. Testes com Grafos Não-Completos
                Grafo grafoNaoCompleto = gerarGrafoNaoCompleto(V);
                
                tempoTotalPrimNaoCompleto += medirTempoPrim(grafoNaoCompleto);
                tempoTotalKruskalNaoCompleto += medirTempoKruskal(grafoNaoCompleto);
            }

            // Calculando as médias de tempo de CPU
            long mediaPrimComp = tempoTotalPrimCompleto / quantidadeGrafosPorTamanho;
            long mediaKruskalComp = tempoTotalKruskalCompleto / quantidadeGrafosPorTamanho;
            long mediaPrimNaoComp = tempoTotalPrimNaoCompleto / quantidadeGrafosPorTamanho;
            long mediaKruskalNaoComp = tempoTotalKruskalNaoCompleto / quantidadeGrafosPorTamanho;

            // Formatação alinhada para exibição dos resultados em tabela
            System.out.printf("%8d | Completo     | %21d | %24d\n", V, mediaPrimComp, mediaKruskalComp);
            System.out.printf("%8d | Não-Completo | %21d | %24d\n", V, mediaPrimNaoComp, mediaKruskalNaoComp);
        }
    }

    private static Grafo gerarGrafoCompleto(int v) {
        Grafo grafo = new Grafo(v);
        Random rand = new Random();
        
        for (int i = 0; i < v; i++) {
            for (int j = i + 1; j < v; j++) {
                double peso = 1 + rand.nextInt(100); 
                grafo.adicionarAresta(i, j, peso);
            }
        }
        return grafo;
    }

    private static Grafo gerarGrafoNaoCompleto(int v) {
        Grafo grafo = new Grafo(v);
        Random rand = new Random();
        
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

    private static long medirTempoPrim(Grafo grafo) {
        long inicio = System.nanoTime();
        
        // Executa a função passando o número de vértices e a lista de adjacência
        algoritmos.prim(grafo.getNumVertices(), grafo.getListaAdjacencia());

        long fim = System.nanoTime();
        return (fim - inicio);
    }

    private static long medirTempoKruskal(Grafo grafo) {
        long inicio = System.nanoTime();
        
        // Executa a função passando o número de vértices e a lista linear de todas as arestas
        algoritmos.kruskal(grafo.getNumVertices(), grafo.getTodasArestas());

        long fim = System.nanoTime();
        return (fim - inicio);
    }
}
