/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package histograma;

/**
 *
 * @author Maku*/
import fotoshop.Logica.Imagen;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import static javax.swing.Action.NAME;
import static javax.swing.Action.SELECTED_KEY;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import static javax.swing.WindowConstants.HIDE_ON_CLOSE;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.statistics.HistogramDataset;

/**
 * @see https://stackoverflow.com/q/40537278/230513
 * @see https://stackoverflow.com/q/11870416/230513
 * @see https://stackoverflow.com/a/28519356/230513
 */
public class dobleHistograma {

    private static final int BINS = 256;
    private BufferedImage image, image2;
    private HistogramDataset dataset;
    private XYBarRenderer renderer;
    int media = 0;
    

    private BufferedImage setImage(Imagen im, int tipo)  //0 = original,  1 =  modificado
        {            
            int[][][] rgbGuardar = (tipo == 0) ? im.getArgb() : im.getModificado();
            BufferedImage img = new BufferedImage(im.getAncho(), im.getAlto(), BufferedImage.TYPE_INT_RGB);
            for(int x = 0; x < im.getAncho(); x++)
                {
                    for(int y = 0; y < im.getAlto(); y++)
                       {
                           //System.out.println("Guardando rgb " + rgbGuardar[x][y][1] + " - " +  rgbGuardar[x][y][2] + " - " +rgbGuardar[x][y][3]);
                           img.setRGB(x, y, (new Color(rgbGuardar[x][y][1],rgbGuardar[x][y][2],rgbGuardar[x][y][3])).getRGB());
                       }
                }
            return img;
        }
    


    private ChartPanel createChartPanel() {
        // dataset
        dataset = new HistogramDataset();
        Raster raster = image.getRaster();
        final int w = image.getWidth();
        final int h = image.getHeight();
        double[] r = new double[w * h];
        r = raster.getSamples(0, 0, w, h, 0, r);
        dataset.addSeries("Rojo", r, BINS);
        r = raster.getSamples(0, 0, w, h, 1, r);
        dataset.addSeries("Verde", r, BINS);
        r = raster.getSamples(0, 0, w, h, 2, r);
        dataset.addSeries("Azul", r, BINS);
 
        // chart
        JFreeChart chart = ChartFactory.createHistogram("Histograma original", "Valor",
            "Cantidad", dataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        renderer = (XYBarRenderer) plot.getRenderer();
        renderer.setBarPainter(new StandardXYBarPainter());
        // translucent red, green & blue
        Paint[] paintArray = {
            new Color(0x80ff0000, true),
            new Color(0x8000ff00, true),
            new Color(0x800000ff, true)
        };
        plot.setDrawingSupplier(new DefaultDrawingSupplier(
            paintArray,
            DefaultDrawingSupplier.DEFAULT_FILL_PAINT_SEQUENCE,
            DefaultDrawingSupplier.DEFAULT_OUTLINE_PAINT_SEQUENCE,
            DefaultDrawingSupplier.DEFAULT_STROKE_SEQUENCE,
            DefaultDrawingSupplier.DEFAULT_OUTLINE_STROKE_SEQUENCE,
            DefaultDrawingSupplier.DEFAULT_SHAPE_SEQUENCE));
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    
    
        private ChartPanel createChartPanel2() {
        // dataset
        dataset = new HistogramDataset();
        Raster raster = image2.getRaster();
        final int w = image2.getWidth();
        final int h = image2.getHeight();
        double[] r = new double[w * h];
        r = raster.getSamples(0, 0, w, h, 0, r);
        dataset.addSeries("Rojo", r, BINS);
        r = raster.getSamples(0, 0, w, h, 1, r);
        dataset.addSeries("Verde", r, BINS);
        r = raster.getSamples(0, 0, w, h, 2, r);
        dataset.addSeries("Azul", r, BINS);
 
        // chart
        JFreeChart chart = ChartFactory.createHistogram("Histograma ajustado", "Valor",
            "Cantidad", dataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        renderer = (XYBarRenderer) plot.getRenderer();
        renderer.setBarPainter(new StandardXYBarPainter());
        // translucent red, green & blue
        Paint[] paintArray = {
            new Color(0x80ff0000, true),
            new Color(0x8000ff00, true),
            new Color(0x800000ff, true)
        };
        plot.setDrawingSupplier(new DefaultDrawingSupplier(
            paintArray,
            DefaultDrawingSupplier.DEFAULT_FILL_PAINT_SEQUENCE,
            DefaultDrawingSupplier.DEFAULT_OUTLINE_PAINT_SEQUENCE,
            DefaultDrawingSupplier.DEFAULT_STROKE_SEQUENCE,
            DefaultDrawingSupplier.DEFAULT_OUTLINE_STROKE_SEQUENCE,
            DefaultDrawingSupplier.DEFAULT_SHAPE_SEQUENCE));
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }
 
    private class VisibleAction extends AbstractAction {

        private final int i;

        public VisibleAction(int i) {
            this.i = i;
            this.putValue(NAME, (String) dataset.getSeriesKey(i));
            this.putValue(SELECTED_KEY, true);
            renderer.setSeriesVisible(i, true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            renderer.setSeriesVisible(i, !renderer.getSeriesVisible(i));
        }
    }

    public void display(Imagen img) {
        image = setImage(img, 0);
        image2 = setImage(img, 1);
        JFrame f = new JFrame("Histograma sensual");
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);        
        f.getContentPane().add(createChartPanel(), BorderLayout.WEST);        
        f.getContentPane().add(createChartPanel2(), BorderLayout.EAST);                             
        f.getContentPane().setBackground(Color.white);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

}