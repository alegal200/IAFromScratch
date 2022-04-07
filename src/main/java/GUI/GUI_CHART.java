package GUI;

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
                    gui_launch.getParamgui().lauchALGO();
                }
            }
        });
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
}
