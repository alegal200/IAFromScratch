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

    private static final double RangeX = 0.5;

    private XYDataset data1;
    private String table;
    private double[] weights;
    double bigX_temp = 0; //Stock le plus grand x
    double smallX_temp = 0; //Stock le plus petit x

    public Chart(String title,double[][] points,double[][] weights) {
        super(title);
        setTable("");
        setWeights(null);
        data1 = createSampleData1(points);
        getContentPane().add(createContent(weights));
    }


    public Chart(String title, String table, double[] weight) {
        super(title);
        setTable(table);
        setWeights(new double[weight.length]);
        setWeights(Arrays.copyOf(weight, weight.length));
        data1 = createSampleData1();
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

    private JTabbedPane createContent(double[][] weights) {
        JTabbedPane jtabbedpane = new JTabbedPane();
        jtabbedpane.add("Linear", createChartPanel1(weights));
        return jtabbedpane;
    }

    private ChartPanel createChartPanel1() {
        NumberAxis numberaxis = new NumberAxis("X");
        numberaxis.setAutoRangeIncludesZero(false);
        NumberAxis numberaxis1 = new NumberAxis("Y");
        numberaxis1.setAutoRangeIncludesZero(false);
        XYLineAndShapeRenderer xylineandshaperenderer = new XYLineAndShapeRenderer(false, true);
        XYPlot xyplot = new XYPlot(data1, numberaxis, numberaxis1, xylineandshaperenderer);


        double a = 0, b = 0;
        if(getWeights().length==3) //Si equ cart dimension 2
        {
            b =(-1)* (getWeights()[0]/getWeights()[2]);
            a =(-1)* (getWeights()[1]/getWeights()[2]);

        }else if(getWeights().length==2) //Si equ cart dimension 1 (regression)
        {
            a=getWeights()[1];
            b=getWeights()[0];
        }

        System.out.println("b"+b);
        System.out.println("a"+a);

        LineFunction2D linefunction2d = new LineFunction2D(b, a);

        XYDataset xydataset = DatasetUtilities.sampleFunction2D(linefunction2d, smallX_temp-RangeX, bigX_temp+RangeX, 100, "Linear line");
        xyplot.setDataset(1, xydataset);
        XYLineAndShapeRenderer xylineandshaperenderer1 = new XYLineAndShapeRenderer(true, false);
        xylineandshaperenderer1.setSeriesPaint(0, Color.blue);
        xyplot.setRenderer(1, xylineandshaperenderer1);

        JFreeChart jfreechart = new JFreeChart("Graphique", JFreeChart.DEFAULT_TITLE_FONT, xyplot, true);
        ChartPanel chartpanel = new ChartPanel(jfreechart, false);
        return chartpanel;
    }

    private ChartPanel createChartPanel1(double[][] weights) {
        NumberAxis numberaxis = new NumberAxis("X");
        numberaxis.setAutoRangeIncludesZero(false);
        NumberAxis numberaxis1 = new NumberAxis("Y");
        numberaxis1.setAutoRangeIncludesZero(false);
        XYLineAndShapeRenderer xylineandshaperenderer = new XYLineAndShapeRenderer(false, true);
        XYPlot xyplot = new XYPlot(data1, numberaxis, numberaxis1, xylineandshaperenderer);


        for(int i=0;i<weights.length;i++)
        {
            double a = 0, b = 0;
            if(weights[i].length==3) //Si equ cart dimension 2
            {
                b =(-1)* (weights[i][0]/weights[i][2]);
                a =(-1)* (weights[i][1]/weights[i][2]);

            }else if(weights[i].length==2) //Si equ cart dimension 1
            {
                a=weights[i][1];
                b=weights[i][0];
            }

            System.out.println("b"+b);
            System.out.println("a"+a);

            LineFunction2D linefunction2d = new LineFunction2D(b, a);

            XYDataset xydataset = DatasetUtilities.sampleFunction2D(linefunction2d, smallX_temp-RangeX, bigX_temp+RangeX, 100, "Linear line");
            xyplot.setDataset(1+i, xydataset);
            XYLineAndShapeRenderer xylineandshaperenderer1 = new XYLineAndShapeRenderer(true, false);
            xylineandshaperenderer1.setSeriesPaint(0, Color.blue);
            xyplot.setRenderer(1+i, xylineandshaperenderer1);
        }



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
        String filePath = System.getProperty("user.dir") + "\\src\\main\\java\\CSVReader\\CSV\\" + getTable();
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(fileReader);

        //Affiche les point de la table

        try {
            int currLine = 0;
            while ((line = reader.readLine()) != null) {
                String[] token = line.split(delimiter);

                if (currLine == 0) {//Uniquement pour la premier iteration
                    bigX_temp = Double.parseDouble(token[0]);
                    smallX_temp = Double.parseDouble(token[0]);
                }
                xyseries.add(Double.parseDouble(token[0]), Double.parseDouble(token[1]));


                //On cherche le plus petit et plus grand x
                if (Double.parseDouble(token[0]) < smallX_temp) {
                    smallX_temp = Double.parseDouble(token[0]);
                }
                if (Double.parseDouble(token[0]) > bigX_temp) {
                    bigX_temp = Double.parseDouble(token[0]);
                }


                currLine++;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


        XYSeriesCollection xyseriescollection = new XYSeriesCollection(xyseries);
        return xyseriescollection;
    }

    private XYDataset createSampleData1(double[][]points) {

        XYSeries xyseries = new XYSeries("Series 1");

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

        //Affiche les point recu
        for(int i=0;i< points.length ;i++)
        {

            if (i == 0) {//Uniquement pour la premier iteration
                bigX_temp = points[i][0];
                smallX_temp = points[i][0];
            }

            xyseries.add(points[i][0],points[i][1]);

            //On cherche le plus petit et plus grand x
            if (points[i][0] < smallX_temp) {
                smallX_temp = points[i][0];
            }
            if (points[i][0] > bigX_temp) {
                bigX_temp = points[i][0];
            }


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




