package GUI;

import JfreeChartPack.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.Rotation;


import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI_CHART {
    private JPanel panel1;
    private JButton GOButton;
    private JTextField textField1;
    private JButton backButton;
    private JTextPane LOGTextPane;
    private JLabel labelDesc;
    private JTextField textField3;
    private JTextField textField4;
    private JPanel chartpanel;
    private JTextField textField2;
    private GUI_LAUNCH gui_launch;

    public GUI_CHART(GUI_LAUNCH gui_lach) {
        gui_launch = gui_lach ;
        gui_launch.getParamgui().setChartPanel(chartpanel);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // click back
                gui_launch.setDefaultJPanel();



            }
        });
        textField1.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                erreurChange();
            }
        });
        textField3.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                learingRateChange();
            }
        });
        textField4.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                maxIterationChange();
            }
        });
        GOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if( gui_launch.getParamgui().CanBeLaunch() ){
                    System.out.println(" lancement de l algo");

                    //Si c'est un monocouche, le graphique se lance dans le code de la classe et donc pas besoin de l'afficher ici
                    if(!(gui_launch.getParamgui().getTypePeceptron().equalsIgnoreCase("Perceptron mono-couche" )))
                    {
                        double[] weight = gui_launch.getParamgui().lauchALGO();

                        Chart chart = new Chart("Graphique",gui_launch.getParamgui().getTable(),weight);
                        chart.pack();
                        RefineryUtilities.centerFrameOnScreen(chart);
                        chart.setVisible(true);
                    }else{
                        gui_launch.getParamgui().lauchALGO();
                    }



                }
            }
        });
        textField4.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                nbrClassChange();
            }
        });
    }

    private void nbrClassChange() {
        try {
            gui_launch.getParamgui().setNbrclass(Integer.parseInt(textField2.getText()));
        }catch (Exception e ){gui_launch.getParamgui().setNbrclass(0);}
    }

    private void maxIterationChange() {
        try {
            gui_launch.getParamgui().setMaxiteration(Integer.parseInt(textField4.getText()));
        }catch (Exception e ){gui_launch.getParamgui().setMaxiteration(0);}
    }

    private void learingRateChange() {
        try{
            gui_launch.getParamgui().setLearningrate(Double.parseDouble(textField3.getText()));
        }catch (Exception e){gui_launch.getParamgui().setLearningrate(0.0);}
    }

    private void erreurChange() {
        try{
            gui_launch.getParamgui().setValerreurdouble(Double.parseDouble(textField1.getText()));
            gui_launch.getParamgui().setValerreurint(Integer.parseInt(textField1.getText()));
        }catch (Exception e) {
            try {
                gui_launch.getParamgui().setValerreurdouble(0.0);
                gui_launch.getParamgui().setValerreurint(Integer.parseInt(textField1.getText()));
            } catch (Exception ee) {
                gui_launch.getParamgui().setValerreurint(0);
                gui_launch.getParamgui().setValerreurdouble(0.0);
            }
        }
    }

    public Container getpanel() {
        return panel1 ;
    }

    private PieDataset createDataset() {
        DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("Linux", 29);
        result.setValue("Mac", 20);
        result.setValue("Windows", 51);
        return result;

    }

    private JFreeChart createChart(PieDataset dataset, String title) {

        JFreeChart chart = ChartFactory.createPieChart3D(
                title, // chart title
                dataset, // data
                true, // include legend
                true,
                false
        );

        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        return chart;

    }


}
