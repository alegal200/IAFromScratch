package JfreeChartPack;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.function.LineFunction2D;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;


public class Chart extends ApplicationFrame {


    private XYDataset data1;
    private String table;
    private double[] weights;

    public Chart(String title,String table,double[] weight) {
        super(title);
        data1 = createSampleData1();
        setTable(table);
        setWeights(new double[weight.length]);
        setWeights(Arrays.copyOf(weight, weight.length));
        getContentPane().add(createContent());
    }
    public Chart(String title) {
        super(title);
        data1 = createSampleData1();
        getContentPane().add(createContent());
    }

    private JTabbedPane createContent() {
        JTabbedPane jtabbedpane = new JTabbedPane();
        jtabbedpane.add("Linear", createChartPanel1());
        return jtabbedpane;
    }

    private ChartPanel createChartPanel1() {
        NumberAxis numberaxis = new NumberAxis("X");
        numberaxis.setAutoRangeIncludesZero(false);
        NumberAxis numberaxis1 = new NumberAxis("Y");
        numberaxis1.setAutoRangeIncludesZero(false);
        XYLineAndShapeRenderer xylineandshaperenderer = new XYLineAndShapeRenderer(false, true);
        XYPlot xyplot = new XYPlot(data1, numberaxis, numberaxis1, xylineandshaperenderer);
        LineFunction2D linefunction2d = new LineFunction2D(3, -3);
        XYDataset xydataset = DatasetUtilities.sampleFunction2D(linefunction2d, 0, 15, 100, "Linear line");
        xyplot.setDataset(1, xydataset);
        XYLineAndShapeRenderer xylineandshaperenderer1 = new XYLineAndShapeRenderer(true, false);
        xylineandshaperenderer1.setSeriesPaint(0, Color.blue);
        xyplot.setRenderer(1, xylineandshaperenderer1);

        JFreeChart jfreechart = new JFreeChart("Graphique", JFreeChart.DEFAULT_TITLE_FONT, xyplot, true);
        ChartPanel chartpanel = new ChartPanel(jfreechart, false);
        return chartpanel;
    }


    private XYDataset createSampleData1() {

        XYSeries xyseries = new XYSeries("Series 1");
        //Delimiter
        String line = "";
        final String delimiter = ",";
        /*
        xyseries.add(2D, 56.270000000000003D);
        xyseries.add(3D, 41.32D);
        xyseries.add(4D, 31.449999999999999D);
        xyseries.add(5D, 30.050000000000001D);
        xyseries.add(6D, 24.690000000000001D);
        xyseries.add(7D, 19.780000000000001D);
        xyseries.add(8D, 20.940000000000001D);
        xyseries.add(9D, 16.73D);
        xyseries.add(10D, 14.210000000000001D);
        xyseries.add(11D, 12.44D);
         */
        String filePath = System.getProperty("user.dir") + "\\src\\main\\java\\CSVReader\\CSV\\" + "table_2_9.csv";
        System.out.println("getTbale"+getTable());
        System.out.println(filePath);
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(fileReader);

        try {

            int currLine = 0;
            while ((line = reader.readLine()) != null) {
                String[] token = line.split(delimiter);
                for(int i=0; i<getWeights().length-1 ;i++) //-1 car on ne veut que les points sans l'entre fictive
                {
                    xyseries.add(Double.parseDouble(token[i]),Double.parseDouble(token[i+1]));
                }

                currLine++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



        XYSeriesCollection xyseriescollection = new XYSeriesCollection(xyseries);
        return xyseriescollection;
    }


    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public double[] getWeights() {
        return weights;
    }

    public void setWeights(double[] weights) {
        this.weights = weights;
    }
}




