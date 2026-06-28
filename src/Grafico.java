import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler;
import java.util.List;

public class Grafico {

    public static void exibirGraficos(
            List<Integer> verticesComp, List<Long> primComp, List<Long> kruskalComp,
            List<Integer> verticesNaoComp, List<Long> primNaoComp, List<Long> kruskalNaoComp
    ) {

        XYChart chartCompleto = new XYChartBuilder()
                .width(800).height(600)
                .title("Desempenho: Grafos COMPLETOS")
                .xAxisTitle("Quantidade de Vértices (N)")
                .yAxisTitle("Tempo Médio (ms)")
                .build();

        chartCompleto.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chartCompleto.getStyler().setMarkerSize(8);
        chartCompleto.getStyler().setXAxisLogarithmic(true);

        if (!verticesComp.isEmpty()) {
            chartCompleto.addSeries("Algoritmo de Prim", verticesComp, primComp);
            chartCompleto.addSeries("Algoritmo de Kruskal", verticesComp, kruskalComp);
        }

        XYChart chartNaoCompleto = new XYChartBuilder()
                .width(800).height(600)
                .title("Desempenho: Grafos NÃO-COMPLETOS")
                .xAxisTitle("Quantidade de Vértices (N)")
                .yAxisTitle("Tempo Médio (ms)")
                .build();

        chartNaoCompleto.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chartNaoCompleto.getStyler().setMarkerSize(8);
        chartNaoCompleto.getStyler().setXAxisLogarithmic(true);
        if (!verticesNaoComp.isEmpty()) {
            chartNaoCompleto.addSeries("Algoritmo de Prim", verticesNaoComp, primNaoComp);
            chartNaoCompleto.addSeries("Algoritmo de Kruskal", verticesNaoComp, kruskalNaoComp);
        }

        System.out.println("\n[Gráficos] A abrir janelas de visualização...");
        new SwingWrapper<>(chartCompleto).displayChart();
        new SwingWrapper<>(chartNaoCompleto).displayChart();
    }
}