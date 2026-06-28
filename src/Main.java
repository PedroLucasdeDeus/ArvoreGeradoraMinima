public class Main {
    public static void main(String[] args) {
        // Configurações do experimento
        int quantidadeGrafosPorTamanho = 10;

        // Escala de crescimento
        int[] tamanhosV = {10, 15, 20, 25, 50, 100, 500, 1000, 3000, 3500};

        System.out.println("Iniciando análise de desempenho: Prim vs Kruskal\n");
        System.out.println("Vértices | Tipo           | Tempo Médio Prim (ms) | Tempo Médio Kruskal (ms)");
        System.out.println("------------------------------------------------------------------");

        for (int V : tamanhosV) {
            
            long tempoTotalPrimCompleto = 0;
            long tempoTotalKruskalCompleto = 0;
            long tempoTotalPrimNaoCompleto = 0;
            long tempoTotalKruskalNaoCompleto = 0;

            for (int i = 0; i < quantidadeGrafosPorTamanho; i++) {
                // 1. Testes com Grafos Completos
                Grafo grafoCompleto = GeradorGrafos.gerarCompleto(V);
                tempoTotalPrimCompleto += AnalisadorDeTempo.medirPrim(grafoCompleto);
                tempoTotalKruskalCompleto += AnalisadorDeTempo.medirKruskal(grafoCompleto);

                // 2. Testes com Grafos Não-Completos
                Grafo grafoNaoCompleto = GeradorGrafos.gerarNaoCompleto(V);
                tempoTotalPrimNaoCompleto += AnalisadorDeTempo.medirPrim(grafoNaoCompleto);
                tempoTotalKruskalNaoCompleto += AnalisadorDeTempo.medirKruskal(grafoNaoCompleto);
            }

            // Calculando as médias de tempo em milissegundos
            long mediaPrimComp = (tempoTotalPrimCompleto / quantidadeGrafosPorTamanho) / 1_000_000;
            long mediaKruskalComp = (tempoTotalKruskalCompleto / quantidadeGrafosPorTamanho) / 1_000_000;
            long mediaPrimNaoComp = (tempoTotalPrimNaoCompleto / quantidadeGrafosPorTamanho) / 1_000_000;
            long mediaKruskalNaoComp = (tempoTotalKruskalNaoCompleto / quantidadeGrafosPorTamanho) / 1_000_000;

            // Formatação alinhada para exibição dos resultados em tabela
            System.out.printf("%8d | Completo       | %21d | %24d\n", V, mediaPrimComp, mediaKruskalComp);
            System.out.printf("%8d | Não-Completo   | %21d | %24d\n", V, mediaPrimNaoComp, mediaKruskalNaoComp);
        }
    }
}