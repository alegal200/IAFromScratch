package GUI;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUI_LAUNCH extends  JFrame{
    private JButton VALIDERButton;
    private JList list1;
    private JList list2;
    private JPanel Mainpanel;
    private JComboBox mcombo2;
    private JComboBox mcombo1;
    private JPanel mLabel1;
    private JLabel mLabel2;
    private JTextField mtextfield;
    private JLabel mLabel3;
    private String type ;
    private ParamGui paramGui ;

    public  GUI_LAUNCH(){
        setContentPane(Mainpanel);
        showCombobox(0);
        setTitle("bonjour");
        setSize(450 ,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        paramGui = new ParamGui() ;

        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                type = list1.getSelectedValue().toString() ;
                paramGui.setTypePeceptron(list1.getSelectedValue().toString());
                System.out.println(list1.getSelectedValue().toString());
                advertChange();
            }
        });

        VALIDERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickvalider();
            }
        });
        list2.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                listselectTalbe();
            }
        });
        mcombo2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                typeErrorChange();
            }
        });
        mcombo1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                typeAlgoChange();
            }
        });


        mtextfield.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                nbrClassChange();
            }
        });
    }
    ////////////////////////////////////////////////////////fin constructeur


    private void nbrClassChange() {
        try{
            paramGui.setNbrclass(Integer.parseInt(mtextfield.getText()));
        }catch (Exception e){paramGui.setNbrclass(0);}
    }

    private void typeAlgoChange() {
        paramGui.setAlgo(mcombo1.getSelectedItem().toString());
    }


    private void typeErrorChange() {
        paramGui.setCritereArret(mcombo2.getSelectedItem().toString());
    }


    private void listselectTalbe() {
        try {
            paramGui.setTable(list2.getSelectedValue().toString());
        }catch (Exception e){};

    }

    private void clickvalider() {
        if(paramGui.CanBeValidate()) {
            GUI_CHART gui = new GUI_CHART(this);

            setContentPane(gui.getpanel());
            setSize(750, 500);
            setVisible(false);
            setVisible(true);
        }


    }

    private void advertChange() {
        //

        paramGui.setTable(null);
        paramGui.setAlgo(list1.getSelectedValue().toString());
        switch (type) {
            case "Perceptron simple":
                System.out.println("peceptron simple");
                showCombobox(0);
                selectTables(1);
                paramGui.setAlgo(null);
                paramGui.setCritereArret(null);
                paramGui.setCritereArret(null);
                paramGui.setNbrclass(0);
                // table et
                break;
            case "Perceptron DG":
                System.out.println("dg");
                showCombobox(2);
                selectTables(2);
                paramGui.setAlgo("ADALINE");
                paramGui.setCritereArret("SeuilNombreErreur");
                paramGui.setNbrclass(0);
                // table et
                // 2.9 classif. linea. sep.
                // 2.10 classif. non linea. sep.
                // 2.11 Regression lineaire
                break;
            case "Perceptron ADALINE":
                System.out.println("adaline");
                showCombobox(2);
                selectTables(2);
                paramGui.setAlgo("ADALINE");
                paramGui.setCritereArret("SeuilNombreErreur");
                paramGui.setNbrclass(0);
                // table et
                // 2.9 classif. linea. sep.
                // 2.10 classif. non linea. sep.
                // 2.11 Regression lineaire
                break;
            case "Perceptron mono-couche":
                System.out.println("mono");
                showCombobox(1);
                selectTables(3);
                paramGui.setAlgo("ADALINE");
                paramGui.setCritereArret("SeuilNombreErreur");
                paramGui.setNbrclass(4);
                // 3.1 classes Adal-SeuilNombreErreur
                // 3.1 classes Adal-SeuilErrQuad
                // 3.1 classes DG-SeuilNombreErreur
                // 3.1 classes DG-SeuilErrQuad
                // 3.5 Adal-SeuilNombreErreur
                // 3.5 Adal-SeuilErrQuad
                break;
            default:
                System.out.println("Error");
                break;


        }
    }
    private void showCombobox(int activate) {
        if(activate == 1){
            System.out.println("oui");
            mcombo1.setVisible(true);
            mcombo2.setVisible(true);
            mLabel1.setVisible(true);
            mLabel2.setVisible(true);
            mLabel3.setVisible(true);
            mtextfield.setVisible(true);
        }
        else if(activate == 2){
            mcombo1.setVisible(false);
            mcombo2.setVisible(true);
            mLabel1.setVisible(false);
            mLabel2.setVisible(true);
            mLabel3.setVisible(false);
            mtextfield.setVisible(false);
        }
        else{
            mcombo1.setVisible(false);
            mcombo2.setVisible(false);
            mLabel1.setVisible(false);
            mLabel2.setVisible(false);
            mLabel3.setVisible(false);
            mtextfield.setVisible(false);

        }
       //adal // dg
        //seuil nbr erreur // errquad
    }
    private void selectTables(int table) {
        if(table == 1){
            list2.clearSelection();
            DefaultListModel<String> mymodel = new DefaultListModel<>();
            mymodel.addElement("TABLE ET");
            list2.setModel(mymodel);
        }
        if(table == 2){
            list2.clearSelection();
            DefaultListModel<String> mymodel = new DefaultListModel<>();
            mymodel.addElement("table ET");
            mymodel.addElement("table_2_9.csv");
            mymodel.addElement("table_2_10.csv");
            mymodel.addElement("table_2_11.csv");
            list2.setModel(mymodel);
        }
        if(table == 3){
            list2.clearSelection();
            DefaultListModel<String> mymodel = new DefaultListModel<>();
            mymodel.addElement("table_3_1.csv");
            mymodel.addElement("table_3_5.csv");
            list2.setModel(mymodel);
        }
    }

    public static void main(String args[]) {
        GUI_LAUNCH myFrame = new GUI_LAUNCH();
    }


    public void setDefaultJPanel() {
        setContentPane(Mainpanel);
        showCombobox(0);
        setTitle("bonjour");
        setSize(450 ,300);
    }

    public ParamGui getParamgui(){
        return paramGui ;
    }
}
