
package hydrogenatom;
import java.awt.Color;
import java.awt.Dimension;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
public class HydrogenAtom extends ApplicationFrame {
    
    private static final long serialVersionUID = 1L;
    private static final int N_POINTS = 500;
    private static final double BOHR_RADIUS = 0.529;
    
    public HydrogenAtom(int dimension) {
        super("Hydrogen Atom Wavefunction");
        
        XYSeriesCollection dataset = new XYSeriesCollection();
        
        XYSeries probabilityDensity = new XYSeries("Probability Density");
        XYSeries radialWavefunction = new XYSeries("Radial Wavefunction");
        
        double rMax = dimension * BOHR_RADIUS;
        double deltaR = rMax / N_POINTS;
        
        for (int i = 0; i < N_POINTS; i++) {
            double r = i * deltaR;
            double psi = radialWavefunction(r);
            probabilityDensity.add(r, 4 * Math.PI * r * r * psi * psi * deltaR);
            radialWavefunction.add(r, psi);
        }
        
        dataset.addSeries(probabilityDensity);
        dataset.addSeries(radialWavefunction);
        
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Hydrogen Atom Wavefunction", "Radius (Å)", "Probability Density",
                dataset, PlotOrientation.VERTICAL, true, true, false);
        
        XYPlot plot = (XYPlot) chart.getPlot();
        
        plot.getRenderer().setSeriesPaint(0, Color.BLUE);
        plot.getRenderer().setSeriesPaint(1, Color.RED);
        plot.getRenderer().setSeriesStroke(0, new java.awt.BasicStroke(2.0f));
        plot.getRenderer().setSeriesStroke(1, new java.awt.BasicStroke(2.0f));
        
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        setContentPane(chartPanel);
    }
    
    private double radialWavefunction(double r) {
        double a0 = BOHR_RADIUS;
        double rho = 2 * r / a0;
        double psi = Math.exp(-rho / 2) * Math.pow(rho, 1) * (2 / a0 - rho) / (2 * Math.sqrt(2) * a0 * Math.sqrt(Math.pow(a0, -3)));
        return psi;
    }  /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HydrogenAtom demo = new HydrogenAtom(10);
        demo.pack();
        demo.setVisible(true);
    }
    
}
