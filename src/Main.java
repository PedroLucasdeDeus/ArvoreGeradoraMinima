import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        int quantidadeGrafosPorTamanho = 50;

        int[] tamanhosV = {50, 100, 200, 300, 400, 600, 800, 1000, 1200, 1400, 1600, 1800, 2000, 2225, 2500};

        List<Integer> verticesComp = new ArrayList<>();
        List<Long> primComp = new ArrayList<>();
        List<Long> kruskalComp = new ArrayList<>();

        List<Integer> verticesNaoComp = new ArrayList<>();
        List<Long> primNaoComp = new ArrayList<>();
        List<Long> kruskalNaoComp = new ArrayList<>();

        System.out.println("Iniciando análise de desempenho: Prim vs Kruskal\n");
        System.out.println("Vértices | Tipo           | Tempo Médio Prim (ms) | Tempo Médio Kruskal (ms)");
        System.out.println("------------------------------------------------------------------");

        for (int V : tamanhosV) {

            long tempoTotalPrimCompleto = 0;
            long tempoTotalKruskalCompleto = 0;
            long tempoTotalPrimNaoCompleto = 0;
            long tempoTotalKruskalNaoCompleto = 0;

            for (int i = 0; i < quantidadeGrafosPorTamanho; i++) {

                Grafo grafoCompleto = GeradorGrafos.gerarCompleto(V);
                tempoTotalPrimCompleto += AnalisadorDeTempo.medirPrim(grafoCompleto);
                tempoTotalKruskalCompleto += AnalisadorDeTempo.medirKruskal(grafoCompleto);

                Grafo grafoNaoCompleto = GeradorGrafos.gerarNaoCompleto(V);
                tempoTotalPrimNaoCompleto += AnalisadorDeTempo.medirPrim(grafoNaoCompleto);
                tempoTotalKruskalNaoCompleto += AnalisadorDeTempo.medirKruskal(grafoNaoCompleto);
            }

            long mediaPrimComp = (tempoTotalPrimCompleto / quantidadeGrafosPorTamanho) / 1_000_000;
            long mediaKruskalComp = (tempoTotalKruskalCompleto / quantidadeGrafosPorTamanho) / 1_000_000;
            long mediaPrimNaoComp = (tempoTotalPrimNaoCompleto / quantidadeGrafosPorTamanho) / 1_000_000;
            long mediaKruskalNaoComp = (tempoTotalKruskalNaoCompleto / quantidadeGrafosPorTamanho) / 1_000_000;

            System.out.printf("%8d | Completo       | %21d | %24d\n", V, mediaPrimComp, mediaKruskalComp);
            System.out.printf("%8d | Não-Completo   | %21d | %24d\n", V, mediaPrimNaoComp, mediaKruskalNaoComp);

            verticesComp.add(V);
            primComp.add(mediaPrimComp);
            kruskalComp.add(mediaKruskalComp);

            verticesNaoComp.add(V);
            primNaoComp.add(mediaPrimNaoComp);
            kruskalNaoComp.add(mediaKruskalNaoComp);
        }

        Grafico.exibirGraficos(
                verticesComp, primComp, kruskalComp,
                verticesNaoComp, primNaoComp, kruskalNaoComp
        );
    }
}